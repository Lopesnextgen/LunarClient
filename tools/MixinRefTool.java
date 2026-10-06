import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.zip.*;

/**
 * Fixes all class references inside Mixin annotations:
 *   @Mixin(value=..., targets="...")
 *   @At(target="Lowner;name(desc)ret")
 *   @Redirect/@Inject/@WrapOperation method=..., target=...
 * References to classes that do not exist on the given classpath are rewritten
 * through the alias map.
 *
 * Usage:
 *   MixinRefTool <in.jar> scan  <out.tsv> <classpathRoots>
 *   MixinRefTool <in.jar> fix   <out.jar> <aliases.tsv> <classpathRoots>
 */
public class MixinRefTool {
    static final Pattern DESC = Pattern.compile("L([A-Za-z0-9_/$]+);");

    public static void main(String[] args) throws Exception {
        String jar = args[0], mode = args[1];
        Set<String> known = loadClasspath(args[mode.equals("scan") ? 3 : 4]);
        if (mode.equals("scan")) {
            List<String> rows = new ArrayList<>();
            eachClass(jar, (owner, cr) -> {
                cr.accept(new ClassVisitor(Opcodes.ASM9) {
                    public AnnotationVisitor visitAnnotation(String d, boolean vis) {
                        return new AnnotationVisitor(Opcodes.ASM9) {
                            public void visit(String n, Object v) { chk(v); }
                            public AnnotationVisitor visitArray(String n) {
                                return new AnnotationVisitor(Opcodes.ASM9) {
                                    public void visit(String n2, Object v) { chk(v); }
                                };
                            }
                            void chk(Object v) {
                                if (v instanceof String) {
                                    for (String c : refs((String) v)) {
                                        if (c.startsWith("net/") && !known.contains(c))
                                            rows.add(owner + "\t" + c);
                                    }
                                } else if (v instanceof Type) {
                                    String c = ((Type) v).getInternalName();
                                    if (c.startsWith("net/") && !known.contains(c))
                                        rows.add(owner + "\t" + c);
                                }
                            }
                        };
                    }
                }, 0);
            });
            Files.write(Paths.get(args[2]), rows);
            System.err.println("rows: " + rows.size());
            return;
        }
        Map<String, String> aliases = new HashMap<>();
        for (String line : Files.readAllLines(Paths.get(args[3]))) {
            if (line.isBlank() || line.startsWith("#")) continue;
            String[] p = line.split("\t");
            if (p.length >= 2) aliases.put(p[0], p[1]);
        }
        final long[] stats = new long[3];
        try (ZipFile zip = new ZipFile(jar);
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
                                AnnotationVisitor av = super.visitAnnotation(d, vis);
                                return new AnnotationVisitor(Opcodes.ASM9, av) {
                                    public void visit(String n, Object v) {
                                        if (v instanceof String) v = sub((String) v);
                                        else if (v instanceof Type) {
                                            Type t = (Type) v;
                                            String c = t.getInternalName();
                                            if (c.startsWith("net/")) {
                                                String r = aliases.get(c);
                                                if (r != null && !known.contains(c)) {
                                                    stats[0]++;
                                                    v = Type.getObjectType(r);
                                                } else if (!known.contains(c)) stats[2]++;
                                            }
                                        }
                                        super.visit(n, v);
                                    }
                                    public AnnotationVisitor visitArray(String n) {
                                        return new AnnotationVisitor(Opcodes.ASM9, super.visitArray(n)) {
                                            public void visit(String n2, Object v) {
                                                if (v instanceof String) v = sub((String) v);
                                                else if (v instanceof Type) {
                                                    Type t = (Type) v;
                                                    String c = t.getInternalName();
                                                    if (c.startsWith("net/")) {
                                                        String r = aliases.get(c);
                                                        if (r != null && !known.contains(c)) {
                                                            stats[0]++;
                                                            v = Type.getObjectType(r);
                                                        } else if (!known.contains(c)) stats[2]++;
                                                    }
                                                }
                                                super.visit(n2, v);
                                            }
                                        };
                                    }
                                    String sub(String s) {
                                        Matcher m = DESC.matcher(s);
                                        StringBuffer sb = new StringBuffer();
                                        while (m.find()) {
                                            String c = m.group(1);
                                            String r = c.startsWith("net/") ? aliases.get(c) : null;
                                            if (r != null && !known.contains(c)) {
                                                stats[0]++;
                                                m.appendReplacement(sb, "L" + r + ";");
                                            } else {
                                                if (c.startsWith("net/") && !known.contains(c)) stats[2]++;
                                                m.appendReplacement(sb, Matcher.quoteReplacement(m.group(0)));
                                            }
                                        }
                                        m.appendTail(sb);
                                        return sb.toString();
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
        System.err.println("rewritten refs: " + stats[0] + " unresolved: " + stats[2]);
    }

    static List<String> refs(String s) {
        List<String> out = new ArrayList<>();
        Matcher m = DESC.matcher(s);
        while (m.find()) out.add(m.group(1));
        return out;
    }

    interface CC { void accept(String name, ClassReader cr) throws Exception; }

    static void eachClass(String jar, CC cc) throws Exception {
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

    static Set<String> loadClasspath(String spec) throws IOException {
        Set<String> known = new HashSet<>();
        for (String p : spec.split(File.pathSeparator)) {
            Path root = Paths.get(p);
            if (Files.isDirectory(root)) {
                Files.walk(root).forEach(x -> {
                    String s = x.toString();
                    if (s.endsWith(".class"))
                        known.add(root.relativize(x).toString().replace('\\', '/').replaceAll("\\.class$", ""));
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
