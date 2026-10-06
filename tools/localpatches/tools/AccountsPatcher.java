import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Build-time bytecode patch for Lunar's "accounts" UI service.
 *
 * A source override is impossible: the runtime jar has a class/package name
 * clash in this namespace, so javac rejects the package outright. ASM lets us
 * add the alt-manager callbacks to the exact same runtime class instead.
 *
 * Added methods (all static, annotated with @CallbackJS so the WebOSR
 * FunctionBus exposes them to the React UI):
 *   listAccounts()             -> AccountOps.listJson()
 *   addOfflineAccount(String)  -> LocalAccounts.addOffline() + async reload
 *   startPremiumAccount()      -> MicrosoftAuth.startDeviceLogin()
 *   getPremiumStatus()         -> MicrosoftAuth.statusJson()
 *
 * selectAccount(String) is rewritten to select locally (AccountOps.selectLocal)
 * instead of asking Lunar's launcher to refresh the token over IPC.
 *
 * Usage: AccountsPatcher <lunar.jar> <output-classes-dir>
 */
public final class AccountsPatcher {

    private static final String TARGET =
            "com/moonsworth/lunar/client/HIRIHCROOIRIORCCOIRRCRHOHCCRRO/HHRROIIHRRICIIHIIHICRHHRHOHHOO/"
                    + "ORICHRORRORHORHOIHCRHOORCRRHOI/HORHROIOIOICIRHIOCOICHHHIHCIIO/"
                    + "HHRROIIHRRICIIHIIHICRHHRHOHHOO/HORHROIOIOICIRHIOCOICHHHIHCIIO.class";
    private static final String IPC_CLIENT =
            "com/moonsworth/lunar/client/IIORCIOOIHRRRICOHIRCIHOOCCOHRO/HORHROIOIOICIRHIOCOICHHHIHCIIO.class";

    public static void main(String[] args) throws Exception {
        Path lunarJar = Paths.get(args[0]);
        Path outputDir = Paths.get(args[1]);
        byte[] original;
        try (ZipFile zip = new ZipFile(lunarJar.toFile())) {
            ZipEntry entry = zip.getEntry(TARGET);
            if (entry == null) {
                throw new IllegalStateException("target class not found in " + lunarJar + ": " + TARGET);
            }
            try (InputStream in = zip.getInputStream(entry)) {
                original = readAll(in);
            }
        }

        ClassNode node = new ClassNode();
        new ClassReader(original).accept(node, 0);

        int replaced = 0;
        for (MethodNode method : node.methods) {
            if (method.name.equals("OIOHRRHRCOCCOIRRHOCHIORHORRHHO")
                    && method.desc.equals("(Ljava/lang/String;)Ljava/lang/String;")) {
                method.instructions.clear();
                method.tryCatchBlocks.clear();
                method.localVariables = null;
                MethodVisitor code = method;
                code.visitVarInsn(Opcodes.ALOAD, 0);
                code.visitMethodInsn(Opcodes.INVOKESTATIC,
                        "com/moonsworth/lunar/altmanager/AccountOps", "selectLocal",
                        "(Ljava/lang/String;)Ljava/lang/String;", false);
                code.visitInsn(Opcodes.ARETURN);
                replaced++;
            }
        }
        if (replaced != 1) {
            throw new IllegalStateException("expected exactly one selectAccount method, replaced=" + replaced);
        }

        addStringCallback(node, "listAccounts",
                "com/moonsworth/lunar/altmanager/AccountOps", "listJson", "()Ljava/lang/String;");
        addStringCallback(node, "getGameContext",
                "com/moonsworth/lunar/altmanager/AccountOps", "gameContextJson", "()Ljava/lang/String;");
        addOfflineAccount(node);
        addStringCallback(node, "getPremiumStatus",
                "com/moonsworth/lunar/altmanager/MicrosoftAuth", "statusJson", "()Ljava/lang/String;");
        addVoidCallback(node, "startPremiumAccount",
                "com/moonsworth/lunar/altmanager/MicrosoftAuth", "startDeviceLogin", "()V");

        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        node.accept(writer);

        Path out = outputDir.resolve(TARGET);
        Files.createDirectories(out.getParent());
        Files.write(out, writer.toByteArray());
        System.out.println("[accounts-patcher] patched " + TARGET
                + " (+4 callbacks, selectAccount rewritten)");

        patchIpcClient(lunarJar, outputDir);
    }

    /**
     * Hooks the game IPC client's onOpen so that, as soon as the game is
     * connected to the launcher, we trigger the client's Authenticator ->
     * AssetServer connection. Without this the UI stays on "Connecting...".
     */
    private static void patchIpcClient(Path lunarJar, Path outputDir) throws Exception {
        byte[] original;
        try (ZipFile zip = new ZipFile(lunarJar.toFile())) {
            ZipEntry entry = zip.getEntry(IPC_CLIENT);
            if (entry == null) {
                throw new IllegalStateException("IPC client class not found: " + IPC_CLIENT);
            }
            try (InputStream in = zip.getInputStream(entry)) {
                original = readAll(in);
            }
        }
        ClassNode node = new ClassNode();
        new ClassReader(original).accept(node, 0);
        int patched = 0;
        for (MethodNode method : node.methods) {
            if (method.name.equals("onOpen")
                    && method.desc.equals("(Lorg/java_websocket/handshake/ServerHandshake;)V")) {
                InsnList instructions = new InsnList();
                instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                        "com/moonsworth/lunar/altmanager/AccountOps", "triggerAssetConnect", "()V", false));
                method.instructions.insert(instructions);
                patched++;
            }
        }
        if (patched != 1) {
            throw new IllegalStateException("expected exactly one IPC onOpen, patched=" + patched);
        }
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        node.accept(writer);
        Path out = outputDir.resolve(IPC_CLIENT);
        Files.createDirectories(out.getParent());
        Files.write(out, writer.toByteArray());
        System.out.println("[accounts-patcher] patched IPC onOpen -> AccountOps.triggerAssetConnect");
    }

    private static void addStringCallback(ClassNode node, String callbackName,
                                          String owner, String method, String descriptor) {
        MethodNode methodNode = new MethodNode(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
                callbackName, descriptor, null, null);
        methodNode.visitCode();
        methodNode.visitMethodInsn(Opcodes.INVOKESTATIC, owner, method, descriptor, false);
        methodNode.visitInsn(descriptor.endsWith(")V") ? Opcodes.RETURN : Opcodes.ARETURN);
        methodNode.visitMaxs(0, 0);
        annotate(methodNode, callbackName);
        methodNode.visitEnd();
        node.methods.add(methodNode);
    }

    private static void addVoidCallback(ClassNode node, String callbackName,
                                        String owner, String method, String descriptor) {
        addStringCallback(node, callbackName, owner, method, descriptor);
    }

    private static void addOfflineAccount(ClassNode node) {
        MethodNode method = new MethodNode(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
                "addOfflineAccount", "(Ljava/lang/String;)Ljava/lang/String;", null, null);
        method.visitCode();
        method.visitVarInsn(Opcodes.ALOAD, 0);
        method.visitMethodInsn(Opcodes.INVOKESTATIC,
                "com/moonsworth/lunar/altmanager/LocalAccounts", "addOffline",
                "(Ljava/lang/String;)Ljava/lang/String;", false);
        method.visitVarInsn(Opcodes.ASTORE, 1);
        method.visitMethodInsn(Opcodes.INVOKESTATIC,
                "com/moonsworth/lunar/altmanager/AccountOps", "reloadAsync", "()V", false);
        method.visitVarInsn(Opcodes.ALOAD, 1);
        method.visitInsn(Opcodes.ARETURN);
        method.visitMaxs(0, 0);
        annotate(method, "addOfflineAccount");
        method.visitEnd();
        node.methods.add(method);
    }

    private static void annotate(MethodNode method, String value) {
        AnnotationVisitor annotation = method.visitAnnotation(
                "Lcom/moonsworth/webosr/javascript/CallbackJS;", true);
        annotation.visit("value", value);
        annotation.visitEnd();
    }

    private static String camel(String value) {
        return value;
    }

    private static byte[] readAll(InputStream in) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        return out.toByteArray();
    }

    private AccountsPatcher() {
    }
}
