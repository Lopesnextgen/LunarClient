import org.objectweb.asm.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.zip.*;

/**
 * Strips Lunar's per-version member suffixes ($v1_7/$v1_8/$v1_12) from
 * annotation string values (Mixin @Inject method=, @At target=, etc.) and
 * rewrites known simple-name aliases in those strings.
 *
 * Usage: StripSuffix <in.jar> <out.jar> [memberMap.tsv]
 */
public class StripSuffix {
    static final Pattern VERSION = Pattern.compile("\\$v1_(?:7|8|12)\\b");
    static Map<String, String> MEMBER = new HashMap<>();

    public static void main(String[] args) throws Exception {
        if (args.length > 2) {
            for (String line : Files.readAllLines(Paths.get(args[2]))) {
                if (line.isBlank() || line.startsWith("#")) continue;
                String[] p = line.split("\t");
                if (p.length >= 2) MEMBER.put(p[0], p[1]);
            }
        }
        final int[] n = {0};
        try (ZipFile zip = new ZipFile(args[0]);
             ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(args[1]))) {
            Enumeration<? extends ZipEntry> en = zip.entries();
            while (en.hasMoreElements()) {
                ZipEntry e = en.nextElement();
                byte[] data = zip.getInputStream(e).readAllBytes();
                if (e.getName().endsWith(".class")) {
                    try {
                        ClassReader cr = new ClassReader(data);
                        ClassWriter cw = new ClassWriter(0);
                        ClassVisitor cv = new ClassVisitor(Opcodes.ASM9, cw) {
                            @Override
                            public MethodVisitor visitMethod(int a, String nm, String d, String s, String[] ex) {
                                MethodVisitor mv = super.visitMethod(a, nm, d, s, ex);
                                if (mv == null) return null;
                                return new MethodVisitor(Opcodes.ASM9, mv) {
                                    @Override
                                    public AnnotationVisitor visitAnnotation(String dd, boolean vis) {
                                        return wrap(super.visitAnnotation(dd, vis));
                                    }
                                };
                            }
                            @Override
                            public FieldVisitor visitField(int a, String nm, String d, String s, Object v) {
                                FieldVisitor fv = super.visitField(a, nm, d, s, v);
                                if (fv == null) return null;
                                return new FieldVisitor(Opcodes.ASM9, fv) {
                                    @Override
                                    public AnnotationVisitor visitAnnotation(String dd, boolean vis) {
                                        return wrap(super.visitAnnotation(dd, vis));
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
        System.err.println("strings rewritten: " + n[0]);
    }

    static AnnotationVisitor wrap(AnnotationVisitor av) {
        if (av == null) return null;
        return new AnnotationVisitor(Opcodes.ASM9, av) {
            @Override
            public void visit(String name, Object value) {
                if (value instanceof String) value = fix((String) value);
                super.visit(name, value);
            }
            @Override
            public AnnotationVisitor visitAnnotation(String name, String d) {
                return wrap(super.visitAnnotation(name, d));
            }
            @Override
            public AnnotationVisitor visitArray(String name) {
                return wrap(super.visitArray(name));
            }
        };
    }

    static String fix(String s) {
        if (s.indexOf('$') < 0) return s;
        // Only touch member-ish strings: no spaces (not arbitrary constants),
        // and either a descriptor target or a plain method name.
        Matcher m = VERSION.matcher(s);
        if (!m.find()) return s;
        String r = m.replaceAll("");
        for (Map.Entry<String, String> e : MEMBER.entrySet()) {
            r = r.replace(e.getKey(), e.getValue());
        }
        return r;
    }
}
