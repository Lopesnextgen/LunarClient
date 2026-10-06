import org.objectweb.asm.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

/**
 * Scans every class in a jar for @Mixin annotations whose target does not
 * resolve to an existing class on the given classpath roots, and rewrites them
 * using a hand-maintained alias map (lunar target -> mcp target).
 *
 * Usage: FixMixinTargets <in.jar> <out.jar> <aliases.tsv> <classpathDir...>
 */
public class FixMixinTargets {
    public static void main(String[] args) throws Exception {
        Map<String, String> aliases = new HashMap<>();
        for (String line : Files.readAllLines(Paths.get(args[2]))) {
            if (line.isBlank() || line.startsWith("#")) continue;
            String[] p = line.split("\t");
            if (p.length >= 2) aliases.put(p[0], p[1]);
        }
        Set<String> classes = new HashSet<>();
        // also index classes from jars/dirs on the classpath roots
        for (int i = 3; i < args.length; i++) {
            Path root = Paths.get(args[i]);
            if (Files.isDirectory(root)) {
                Files.walk(root).forEach(p -> {
                    String s = p.toString();
                    if (s.endsWith(".class")) {
                        classes.add(root.relativize(p).toString().replace('\\', '/')
                                .replaceAll("\\.class$", ""));
                    }
                });
            } else if (Files.isRegularFile(root)) {
                try (ZipFile z = new ZipFile(root.toFile())) {
                    z.stream().forEach(e -> {
                        if (e.getName().endsWith(".class"))
                            classes.add(e.getName().replaceAll("\\.class$", ""));
                    });
                }
            }
        }
        System.err.println("resolvable classes: " + classes.size() + " aliases: " + aliases.size());

        final int[] fixed = {0}, broken = {0};
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
                            public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
                                AnnotationVisitor av = super.visitAnnotation(descriptor, visible);
                                if (!descriptor.equals("Lorg/spongepowered/asm/mixin/Mixin;") || av == null)
                                    return av;
                                return new AnnotationVisitor(Opcodes.ASM9, av) {
                                    @Override
                                    public void visit(String name, Object value) {
                                        if (name.equals("value") && value instanceof Type) {
                                            value = rewrite((Type) value);
                                        }
                                        super.visit(name, value);
                                    }
                                    @Override
                                    public AnnotationVisitor visitArray(String name) {
                                        return new AnnotationVisitor(Opcodes.ASM9, super.visitArray(name)) {
                                            @Override
                                            public void visit(String n2, Object value) {
                                                if (value instanceof Type) value = rewrite((Type) value);
                                                super.visit(n2, value);
                                            }
                                        };
                                    }
                                };
                            }

                            private Object rewrite(Type t) {
                                String n = t.getInternalName();
                                if (n.startsWith("targets") || n.startsWith("net/minecraft/")) {
                                    if (!classes.contains(n)) {
                                        String r = aliases.get(n);
                                        if (r != null) {
                                            fixed[0]++;
                                            System.err.println("mixin target " + n + " -> " + r);
                                            return Type.getObjectType(r);
                                        }
                                        broken[0]++;
                                        System.err.println("UNRESOLVED mixin target " + n);
                                    }
                                }
                                return t;
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
        System.err.println("fixed: " + fixed[0] + " unresolved: " + broken[0]);
    }
}
