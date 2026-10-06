import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Patches com/moonsworth/lunar/genesis/ICRHORIIHOHROHOHOCOOHOOCOORRHO#isExcluded so that
 * net.minecraft.* and net.minecraftforge.* are never remapped by Ichor.
 *
 * The workspace ships MCP-named game classes; Ichor's runtime remapping
 * targets the obfuscated official game jar and corrupts some classes.
 * Excluding the game packages makes the classloader load our classes directly.
 *
 * Usage: PatchExclude <in.jar> <out.jar>
 */
public final class PatchExclude {
    public static void main(String[] args) throws Exception {
        try (ZipFile zip = new ZipFile(args[0]);
             ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(args[1]))) {
            Enumeration<? extends ZipEntry> entries = zip.entries();
            boolean patched = false;
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                byte[] bytes;
                try (InputStream is = zip.getInputStream(entry)) {
                    bytes = is.readAllBytes();
                }
                if (entry.getName().equals("com/moonsworth/lunar/genesis/ICRHORIIHOHROHOHOCOOHOOCOORRHO.class")) {
                    ClassNode cn = new ClassNode();
                    new ClassReader(bytes).accept(cn, 0);
                    String setField = null;
                    for (FieldNode fn : cn.fields) {
                        if (fn.desc.equals("Ljava/util/Set;")) {
                            setField = fn.name;
                            break;
                        }
                    }
                    for (MethodNode mn : cn.methods) {
                        if (mn.name.equals("isExcluded") && mn.desc.equals("(Ljava/lang/String;)Z")) {
                            InsnList il = new InsnList();
                            LabelNode isTrue = new LabelNode();
                            LabelNode isOriginal = new LabelNode();
                            // if (name.startsWith("net.minecraft.")) return true;
                            il.add(new VarInsnNode(Opcodes.ALOAD, 1));
                            il.add(new LdcInsnNode("net.minecraft."));
                            il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/String",
                                    "startsWith", "(Ljava/lang/String;)Z", false));
                            il.add(new JumpInsnNode(Opcodes.IFNE, isTrue));
                            // if (name.startsWith("net.minecraftforge.")) return true;
                            il.add(new VarInsnNode(Opcodes.ALOAD, 1));
                            il.add(new LdcInsnNode("net.minecraftforge."));
                            il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/String",
                                    "startsWith", "(Ljava/lang/String;)Z", false));
                            il.add(new JumpInsnNode(Opcodes.IFEQ, isOriginal));
                            il.add(isTrue);
                            il.add(new InsnNode(Opcodes.ICONST_1));
                            il.add(new InsnNode(Opcodes.IRETURN));
                            // return this.<set>.contains(name.replace('/', '.'));
                            il.add(isOriginal);
                            il.add(new VarInsnNode(Opcodes.ALOAD, 0));
                            il.add(new FieldInsnNode(Opcodes.GETFIELD, cn.name, setField, "Ljava/util/Set;"));
                            il.add(new VarInsnNode(Opcodes.ALOAD, 1));
                            il.add(new LdcInsnNode("/"));
                            il.add(new LdcInsnNode("."));
                            il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/String",
                                    "replace", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;", false));
                            il.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, "java/util/Set",
                                    "contains", "(Ljava/lang/Object;)Z", true));
                            il.add(new InsnNode(Opcodes.IRETURN));
                            mn.instructions = il;
                            mn.tryCatchBlocks = new java.util.ArrayList<>();
                            mn.localVariables = new java.util.ArrayList<>();
                            mn.maxStack = 4;
                            mn.maxLocals = 2;
                            patched = true;
                            System.out.println("patched isExcluded (set field " + setField + ")");
                        }
                    }
                    ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES) {
                        @Override
                        protected String getCommonSuperClass(String a, String b) {
                            return "java/lang/Object";
                        }
                    };
                    cn.accept(cw);
                    bytes = cw.toByteArray();
                }
                zos.putNextEntry(new ZipEntry(entry.getName()));
                zos.write(bytes);
                zos.closeEntry();
            }
            System.out.println("patched=" + patched);
        }
    }
}
