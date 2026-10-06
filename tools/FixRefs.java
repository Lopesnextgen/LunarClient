import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

/**
 * Scans class files for broken net/minecraft references and mixin @Mixin
 * annotations, optionally rewriting them via a TSV map.
 *
 * Usage:
 *   java FixRefs <jar> scan <out.tsv>
 *   java FixRefs <jar> apply <map.tsv> <out.jar>
 */
public class FixRefs {

    public static void main(String[] args) throws Exception {
        String jarPath = args[0];
        String mode = args[1];
        if (mode.equals("scan")) {
            scan(jarPath, args[2]);
        } else if (mode.equals("apply")) {
            apply(jarPath, args[2], args[3]);
        }
    }

    static class Collector extends ClassVisitor {
        String className;
        Set<String> descRefs = new TreeSet<>();
        List<String> mixinTargets = new ArrayList<>();

        Collector() { super(Opcodes.ASM9); }

        @Override
        public void visit(int version, int access, String name, String sig, String sup, String[] ifaces) {
            className = name;
        }

        @Override
        public FieldVisitor visitField(int a, String n, String d, String s, Object v) {
            collectDesc(d);
            return null;
        }

        @Override
        public MethodVisitor visitMethod(int a, String n, String d, String s, String[] e) {
            collectDesc(d);
            return null;
        }

        @Override
        public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
            if (descriptor.equals("Lorg/spongepowered/asm/mixin/Mixin;")) {
                return new AnnotationVisitor(Opcodes.ASM9) {
                    @Override
                    public void visit(String name, Object value) {
                        if (name.equals("value")) {
                            if (value instanceof Type) mixinTargets.add(((Type) value).getInternalName());
                            else if (value instanceof Type[]) {
                                for (Type t : (Type[]) value) mixinTargets.add(t.getInternalName());
                            }
                        }
                    }
                    @Override
                    public AnnotationVisitor visitArray(String name) {
                        if (!name.equals("value")) return null;
                        return new AnnotationVisitor(Opcodes.ASM9) {
                            @Override
                            public void visit(String n2, Object value) {
                                if (value instanceof Type) mixinTargets.add(((Type) value).getInternalName());
                            }
                        };
                    }
                };
            }
            return null;
        }

        void collectDesc(String d) {
            if (d == null) return;
            int i = 0;
            while (i < d.length()) {
                if (d.charAt(i) == 'L') {
                    int j = d.indexOf(';', i);
                    if (j < 0) break;
                    String c = d.substring(i + 1, j);
                    if (c.startsWith("net/minecraft/")) descRefs.add(c);
                    i = j + 1;
                } else i++;
            }
        }
    }

    static Map<String, String> readMap(String path) throws IOException {
        Map<String, String> m = new HashMap<>();
        for (String line : Files.readAllLines(Paths.get(path))) {
            if (line.isBlank() || line.startsWith("#")) continue;
            String[] p = line.split("\t");
            if (p.length >= 2) m.put(p[0], p[1]);
        }
        return m;
    }

    static void scan(String jarPath, String out) throws Exception {
        Set<String> existing = new HashSet<>();
        // existing classes from target/classes (passed via system property)
        String targetDir = System.getProperty("targetClasses", "target/classes");
        if (Files.isDirectory(Paths.get(targetDir))) {
            Files.walk(Paths.get(targetDir)).forEach(p -> {
                String s = p.toString();
                if (s.endsWith(".class")) {
                    String rel = Paths.get(targetDir).relativize(p).toString();
                    existing.add(rel.substring(0, rel.length() - 6));
                }
            });
        }
        System.err.println("existing classes: " + existing.size());
        List<String> rows = new ArrayList<>();
        try (ZipFile zip = new ZipFile(jarPath)) {
            Enumeration<? extends ZipEntry> en = zip.entries();
            while (en.hasMoreElements()) {
                ZipEntry e = en.nextElement();
                if (!e.getName().endsWith(".class")) continue;
                try (InputStream in = zip.getInputStream(e)) {
                    Collector c = new Collector();
                    new ClassReader(in).accept(c, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
                    for (String ref : c.descRefs) {
                        if (!existing.contains(ref)) {
                            rows.add(c.className + "\tDESC\t" + ref);
                        }
                    }
                    for (String t : c.mixinTargets) {
                        if (t.startsWith("net/minecraft/") && !existing.contains(t)) {
                            rows.add(c.className + "\tMIXIN\t" + t);
                        }
                    }
                } catch (Exception ex) { }
            }
        }
        Files.write(Paths.get(out), rows);
        System.err.println("wrote " + rows.size() + " rows to " + out);
    }

    static void apply(String jarPath, String mapPath, String outJar) throws Exception {
        Map<String, String> map = readMap(mapPath);
        System.err.println("rename entries: " + map.size());
        try (ZipFile zip = new ZipFile(jarPath);
             ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(outJar))) {
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
                            public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
                                if (descriptor.equals("Lorg/spongepowered/asm/mixin/Mixin;")) {
                                    return new AnnotationVisitor(Opcodes.ASM9, super.visitAnnotation(descriptor, visible)) {
                                        @Override
                                        public void visit(String name, Object value) {
                                            super.visit(name, renameType(value, map));
                                        }
                                        @Override
                                        public AnnotationVisitor visitArray(String name) {
                                            AnnotationVisitor av = super.visitArray(name);
                                            if (av == null) return null;
                                            return new AnnotationVisitor(Opcodes.ASM9, av) {
                                                @Override
                                                public void visit(String n2, Object value) {
                                                    super.visit(n2, renameType(value, map));
                                                }
                                            };
                                        }
                                    };
                                }
                                return super.visitAnnotation(descriptor, visible);
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
        System.err.println("wrote " + outJar);
    }

    static Object renameType(Object value, Map<String, String> map) {
        if (value instanceof Type) {
            Type t = (Type) value;
            String n = t.getInternalName();
            String r = map.get(n);
            if (r != null) return Type.getObjectType(r);
        }
        return value;
    }
}
