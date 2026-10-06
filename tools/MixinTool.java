import org.objectweb.asm.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

/**
 * Scans or fixes @Mixin annotation targets in a jar that do not resolve on a
 * given classpath.
 *
 * Usage:
 *   ScanMixins <jar> <out.tsv> <classpathDir...>
 *   FixMixins  <jar> <out.jar> <aliases.tsv> <classpathDir...>
 */
public class MixinTool {

    public static void main(String[] args) throws Exception {
        String jar = args[0];
        if (args[1].equals("scan")) {
            Set<String> known = loadClasspath(args[3]);
            List<String> rows = new ArrayList<>();
            eachClass(jar, (name, cr) -> {
                cr.accept(new ClassVisitor(Opcodes.ASM9) {
                    public AnnotationVisitor visitAnnotation(String desc, boolean vis) {
                        if (!desc.equals("Lorg/spongepowered/asm/mixin/Mixin;")) return null;
                        return new AnnotationVisitor(Opcodes.ASM9) {
                            public void visit(String n, Object v) { check(n, v); }
                            public AnnotationVisitor visitArray(String n) {
                                return new AnnotationVisitor(Opcodes.ASM9) {
                                    public void visit(String n2, Object v) { check(n2, v); }
                                };
                            }
                            void check(String n, Object v) {
                                if (!(v instanceof Type)) return;
                                String t = ((Type) v).getInternalName();
                                if (t.startsWith("net/minecraft/") && !known.contains(t)) {
                                    rows.add(name + "\t" + t);
                                }
                            }
                        };
                    }
                }, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
            });
            Files.write(Paths.get(args[2]), rows);
            System.err.println("broken targets: " + rows.size());
        } else {
            Map<String, String> aliases = new HashMap<>();
            for (String line : Files.readAllLines(Paths.get(args[2]))) {
                if (line.isBlank() || line.startsWith("#")) continue;
                String[] p = line.split("\t");
                if (p.length >= 2) aliases.put(p[0], p[1]);
            }
            Set<String> known = loadClasspath(args[3]);
            final int[] fixed = {0};
            final int[] unresolved = {0};
            try (ZipFile zip = new ZipFile(jar);
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
                                public AnnotationVisitor visitAnnotation(String desc, boolean vis) {
                                    AnnotationVisitor av = super.visitAnnotation(desc, vis);
                                    if (!desc.equals("Lorg/spongepowered/asm/mixin/Mixin;") || av == null)
                                        return av;
                                    return new AnnotationVisitor(Opcodes.ASM9, av) {
                                        public void visit(String n, Object v) {
                                            if (v instanceof Type) v = rewrite((Type) v);
                                            super.visit(n, v);
                                        }
                                        public AnnotationVisitor visitArray(String n) {
                                            return new AnnotationVisitor(Opcodes.ASM9, super.visitArray(n)) {
                                                public void visit(String n2, Object v) {
                                                    if (v instanceof Type) v = rewrite((Type) v);
                                                    super.visit(n2, v);
                                                }
                                            };
                                        }
                                        private Object rewrite(Type t) {
                                            String i = t.getInternalName();
                                            if (!i.startsWith("net/minecraft/") || known.contains(i)) return t;
                                            String r = aliases.get(i);
                                            if (r != null) {
                                                fixed[0]++;
                                                return Type.getObjectType(r);
                                            }
                                            unresolved[0]++;
                                            System.err.println("UNRESOLVED " + e.getName() + " -> " + i);
                                            return t;
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
            System.err.println("fixed: " + fixed[0] + " unresolved: " + unresolved[0]);
        }
    }

    interface ClassConsumer {
        void accept(String name, ClassReader cr) throws Exception;
    }

    static void eachClass(String jar, ClassConsumer cc) throws Exception {
        try (ZipFile zip = new ZipFile(jar)) {
            Enumeration<? extends ZipEntry> en = zip.entries();
            while (en.hasMoreElements()) {
                ZipEntry e = en.nextElement();
                if (!e.getName().endsWith(".class")) continue;
                try (InputStream in = zip.getInputStream(e)) {
                    cc.accept(e.getName(), new ClassReader(in));
                } catch (Exception ex) { }
            }
        }
    }

    static Set<String> loadClasspath(String pathSpec) throws IOException {
        Set<String> known = new HashSet<>();
        for (String p : pathSpec.split(File.pathSeparator)) {
            Path root = Paths.get(p);
            if (Files.isDirectory(root)) {
                Files.walk(root).forEach(x -> {
                    String s = x.toString();
                    if (s.endsWith(".class")) {
                        known.add(root.relativize(x).toString().replace('\\', '/')
                                .replaceAll("\\.class$", ""));
                    }
                });
            } else if (Files.isRegularFile(root)) {
                try (ZipFile z = new ZipFile(root.toFile())) {
                    z.stream().forEach(x -> {
                        if (x.getName().endsWith(".class"))
                            known.add(x.getName().replaceAll("\\.class$", ""));
                    });
                }
            }
        }
        return known;
    }
}
