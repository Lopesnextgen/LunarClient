import org.objectweb.asm.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

/**
 * Replaces exact string tokens (including inside larger strings) and type
 * references in class constant pools.
 *
 * Usage: TokenReplace <in.jar> <pairs.tsv> <out.jar>
 */
public class TokenReplace {
    static List<String[]> PAIRS = new ArrayList<>();
    static int count = 0;

    public static void main(String[] args) throws Exception {
        for (String line : Files.readAllLines(Paths.get(args[1]))) {
            if (line.isBlank() || line.startsWith("#")) continue;
            String[] p = line.split("\t");
            if (p.length >= 2) PAIRS.add(new String[]{p[0], p[1]});
        }
        System.err.println("pairs: " + PAIRS.size());
        try (ZipFile zip = new ZipFile(args[0]);
             ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(args[2]))) {
            Enumeration<? extends ZipEntry> en = zip.entries();
            while (en.hasMoreElements()) {
                ZipEntry e = en.nextElement();
                byte[] data = zip.getInputStream(e).readAllBytes();
                if (e.getName().endsWith(".class")) {
                    try {
                        ClassReader cr = new ClassReader(data);
                        ClassWriter cw = new ClassWriter(0);
                        ClassVisitor cv = new ClassVisitor(Opcodes.ASM9, cw) {
                            public AnnotationVisitor visitAnnotation(String d, boolean vis) {
                                return fixAnn(super.visitAnnotation(d, vis));
                            }
                            public MethodVisitor visitMethod(int a, String nm, String d, String s, String[] ex) {
                                MethodVisitor mv = super.visitMethod(a, nm, d, s, ex);
                                if (mv == null) return null;
                                return new MethodVisitor(Opcodes.ASM9, mv) {
                                    public AnnotationVisitor visitAnnotation(String dd, boolean vis) {
                                        return fixAnn(super.visitAnnotation(dd, vis));
                                    }
                                    public void visitLdcInsn(Object value) {
                                        if (value instanceof String) value = fixStr((String) value);
                                        super.visitLdcInsn(value);
                                    }
                                };
                            }
                        };
                        cr.accept(cv, 0);
                        data = cw.toByteArray();
                    } catch (Exception ex) { }
                }
                zout.putNextEntry(new ZipEntry(e.getName()));
                zout.write(data);
                zout.closeEntry();
            }
        }
        System.err.println("tokens rewritten: " + count);
    }

    static String fixStr(String s) {
        String out = s;
        for (String[] p : PAIRS) {
            if (out.contains(p[0])) {
                out = out.replace(p[0], p[1]);
                count++;
            }
        }
        return out;
    }

    static AnnotationVisitor fixAnn(AnnotationVisitor av) {
        if (av == null) return null;
        return new AnnotationVisitor(Opcodes.ASM9, av) {
            public void visit(String name, Object value) {
                if (value instanceof String) value = fixStr((String) value);
                super.visit(name, value);
            }
            public AnnotationVisitor visitAnnotation(String name, String d) {
                return fixAnn(super.visitAnnotation(name, d));
            }
            public AnnotationVisitor visitArray(String name) {
                return fixAnn(super.visitArray(name));
            }
        };
    }
}
