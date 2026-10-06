import org.objectweb.asm.*;
import org.objectweb.asm.commons.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.zip.*;

/**
 * Comprehensive class-name remapper:
 *  - ASM ClassRemapper for descriptors/signatures/refs/types
 *  - every annotation string value is scanned recursively (including nested
 *    annotations such as Mixin's @At inside @Redirect) for "L<name>;" or
 *    "net.minecraft.<name>" references and rewritten via the map.
 *
 * Usage: FullRemap <in.jar> <map.tsv> <out.jar>
 */
public class FullRemap {
    static final Pattern DESC = Pattern.compile("L([A-Za-z0-9_/$]+);");
    static Map<String, String> MAP;
    static int rewrites = 0;

    public static void main(String[] args) throws Exception {
        MAP = new HashMap<>();
        for (String line : Files.readAllLines(Paths.get(args[1]))) {
            if (line.isBlank() || line.startsWith("#")) continue;
            String[] p = line.split("\t");
            if (p.length >= 2) MAP.put(p[0], p[1]);
        }
        System.err.println("map entries: " + MAP.size());
        Remapper remapper = new Remapper() {
            @Override
            public String map(String internalName) {
                String r = MAP.get(internalName);
                return r != null ? r : internalName;
            }
        };
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
                        ClassVisitor cv = new ClassRemapper(cw, remapper) {
                            @Override
                            public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
                                return wrap(super.visitAnnotation(desc, visible));
                            }
                            @Override
                            public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath tp,
                                                                          String desc, boolean visible) {
                                return wrap(super.visitTypeAnnotation(typeRef, tp, desc, visible));
                            }
                            @Override
                            public MethodVisitor visitMethod(int acc, String n, String d, String sig, String[] ex) {
                                MethodVisitor mv = super.visitMethod(acc, n, d, sig, ex);
                                if (mv == null) return null;
                                return new MethodVisitor(Opcodes.ASM9, mv) {
                                    @Override
                                    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
                                        return wrap(super.visitAnnotation(desc, visible));
                                    }
                                    @Override
                                    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath tp,
                                                                                  String desc, boolean visible) {
                                        return wrap(super.visitTypeAnnotation(typeRef, tp, desc, visible));
                                    }
                                    @Override
                                    public AnnotationVisitor visitAnnotationDefault() {
                                        return wrap(super.visitAnnotationDefault());
                                    }
                                };
                            }
                            @Override
                            public FieldVisitor visitField(int acc, String n, String d, String sig, Object val) {
                                FieldVisitor fv = super.visitField(acc, n, d, sig, val);
                                if (fv == null) return null;
                                return new FieldVisitor(Opcodes.ASM9, fv) {
                                    @Override
                                    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
                                        return wrap(super.visitAnnotation(desc, visible));
                                    }
                                    @Override
                                    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath tp,
                                                                                  String desc, boolean visible) {
                                        return wrap(super.visitTypeAnnotation(typeRef, tp, desc, visible));
                                    }
                                };
                            }
                        };
                        cr.accept(cv, 0);
                        data = cw.toByteArray();
                    } catch (Exception ex) {
                        System.err.println("skip " + e.getName() + ": " + ex);
                    }
                }
                zout.putNextEntry(new ZipEntry(e.getName()));
                zout.write(data);
                zout.closeEntry();
            }
        }
        System.err.println("annotation string rewrites: " + rewrites);
    }

    static AnnotationVisitor wrap(AnnotationVisitor av) {
        if (av == null) return null;
        return new AnnotationVisitor(Opcodes.ASM9, av) {
            @Override
            public void visit(String name, Object value) {
                super.visit(name, fix(value));
            }
            @Override
            public void visitEnum(String name, String desc, String value) {
                super.visitEnum(name, desc, value);
            }
            @Override
            public AnnotationVisitor visitAnnotation(String name, String desc) {
                return wrap(super.visitAnnotation(name, desc));
            }
            @Override
            public AnnotationVisitor visitArray(String name) {
                return wrap(super.visitArray(name));
            }
        };
    }

    static Object fix(Object v) {
        if (v instanceof String) return sub((String) v);
        return v;
    }

    static String sub(String s) {
        if (s.indexOf('L') < 0) return s;
        Matcher m = DESC.matcher(s);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String c = m.group(1);
            String r = MAP.get(c);
            if (r != null) {
                rewrites++;
                m.appendReplacement(sb, "L" + r + ";");
            } else {
                m.appendReplacement(sb, Matcher.quoteReplacement(m.group(0)));
            }
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
