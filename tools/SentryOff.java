import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Builds a shadow jar that neutralises Sentry telemetry.
 *
 * The game classloaders can load io.sentry from several jars on the launch
 * classpath; this jar is prepended so its no-op classes always win. Capture
 * entry points and the HTTP transport return defaults, so nothing can leave
 * the machine.
 *
 * Usage: java SentryOff <in.jar> <out.jar>
 */
public final class SentryOff {

    private static final Set<String> TARGETS = new HashSet<>(Arrays.asList(
            "io/sentry/Sentry.class",
            "io/sentry/SentryClient.class",
            "io/sentry/Hub.class",
            "io/sentry/transport/AsyncHttpTransport.class",
            "io/sentry/transport/AsyncHttpTransport$EnvelopeSender.class",
            "io/sentry/transport/HttpConnection.class",
            "io/sentry/transport/TransportManager.class"
    ));

    private static boolean isAction(String name) {
        return name.startsWith("capture") || name.equals("send") || name.equals("flush")
                || name.equals("close") || name.equals("submit") || name.startsWith("handle")
                || name.equals("run") || name.equals("enqueue");
    }

    private static void neuter(MethodNode mn) {
        Type ret = Type.getReturnType(mn.desc);
        InsnList list = new InsnList();
        switch (ret.getSort()) {
            case Type.VOID:
                list.add(new InsnNode(Opcodes.RETURN));
                break;
            case Type.BOOLEAN:
            case Type.BYTE:
            case Type.CHAR:
            case Type.SHORT:
            case Type.INT:
                list.add(new InsnNode(Opcodes.ICONST_0));
                list.add(new InsnNode(Opcodes.IRETURN));
                break;
            case Type.LONG:
                list.add(new InsnNode(Opcodes.LCONST_0));
                list.add(new InsnNode(Opcodes.LRETURN));
                break;
            case Type.FLOAT:
                list.add(new InsnNode(Opcodes.FCONST_0));
                list.add(new InsnNode(Opcodes.FRETURN));
                break;
            case Type.DOUBLE:
                list.add(new InsnNode(Opcodes.DCONST_0));
                list.add(new InsnNode(Opcodes.DRETURN));
                break;
            default:
                list.add(new InsnNode(Opcodes.ACONST_NULL));
                list.add(new InsnNode(Opcodes.ARETURN));
        }
        mn.instructions = list;
        mn.tryCatchBlocks = new ArrayList<>();
        mn.localVariables = new ArrayList<>();
        for (AbstractInsnNode insn : list) {
            // nothing
        }
    }

    public static void main(String[] args) throws Exception {
        String in = args[0], out = args[1];
        int patchedClasses = 0, patchedMethods = 0;
        try (ZipFile zip = new ZipFile(in);
             ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(out))) {
            Enumeration<? extends ZipEntry> entries = zip.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                byte[] bytes;
                try (InputStream is = zip.getInputStream(entry)) {
                    bytes = is.readAllBytes();
                }
                if (TARGETS.contains(entry.getName()) && entry.getName().endsWith(".class")) {
                    ClassNode cn = new ClassNode();
                    new ClassReader(bytes).accept(cn, 0);
                    boolean changed = false;
                    for (MethodNode mn : cn.methods) {
                        if (mn.name.startsWith("<")) {
                            continue;
                        }
                        if (isAction(mn.name)) {
                            neuter(mn);
                            patchedMethods++;
                            changed = true;
                        }
                    }
                    if (changed) {
                        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
                        cn.accept(cw);
                        bytes = cw.toByteArray();
                        patchedClasses++;
                        System.out.println("neutered " + entry.getName());
                        zos.putNextEntry(new ZipEntry(entry.getName()));
                        zos.write(bytes);
                        zos.closeEntry();
                    }
                }
            }
        }
        System.out.println("patched classes=" + patchedClasses + " methods=" + patchedMethods + " -> " + out);
    }
}
