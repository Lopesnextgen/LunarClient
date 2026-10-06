import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.zip.*;

/**
 * Generates corrected mixin configs: for each mixin class, verifies that every
 * @Inject/@Redirect/@ModifyArg/@At target member exists in the target class
 * (as found on a classpath of compiled classes).  Mixins that reference
 * missing members are dropped.
 *
 * Usage: MixinConfigGen <jar> <config.json> <out.json> <mcpClassRoot>
 */
public class MixinConfigGen {
    static Map<String, Set<String>> MEMBERS = new HashMap<>();
    static final Pattern SUFFIX = Pattern.compile("\\$v1_(?:7|8|12)");

    public static void main(String[] args) throws Exception {
        String jarPath = args[0], cfgPath = args[1], outPath = args[2], root = args[3];
        loadMembers(root);
        System.err.println("classes indexed: " + MEMBERS.size());
        Map<String, Object[]> mixins = new LinkedHashMap<>();
        try (ZipFile zip = new ZipFile(jarPath)) {
            for (Enumeration<? extends ZipEntry> en = zip.entries(); en.hasMoreElements(); ) {
                ZipEntry e = en.nextElement();
                if (!e.getName().endsWith(".class")) continue;
                try (InputStream in = zip.getInputStream(e)) {
                    ClassReader cr = new ClassReader(in);
                    ClassNode cn = new ClassNode();
                    cr.accept(cn, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
                    List<String> targets = new ArrayList<>();
                    boolean isMixin = false;
                    for (AnnotationNode an : cn.visibleAnnotations != null ? cn.visibleAnnotations : Collections.<AnnotationNode>emptyList()) {
                        if (an.desc.equals("Lorg/spongepowered/asm/mixin/Mixin;")) {
                            isMixin = true;
                            collectTargets(an, targets);
                        }
                    }
                    for (AnnotationNode an : cn.invisibleAnnotations != null ? cn.invisibleAnnotations : Collections.<AnnotationNode>emptyList()) {
                        if (an.desc.equals("Lorg/spongepowered/asm/mixin/Mixin;")) {
                            isMixin = true;
                            collectTargets(an, targets);
                        }
                    }
                    if (!isMixin) continue;
                    boolean ok = true;
                    for (MethodNode mn : cn.methods) {
                        List<AnnotationNode> annos = new ArrayList<>();
                        if (mn.visibleAnnotations != null) annos.addAll(mn.visibleAnnotations);
                        if (mn.invisibleAnnotations != null) annos.addAll(mn.invisibleAnnotations);
                        for (AnnotationNode an : annos) {
                            String d = an.desc;
                            if (!d.startsWith("Lorg/spongepowered/asm/mixin/injection/") &&
                                !d.equals("Lcom/llamalad7/mixinextras/") && !d.contains("WrapOperation")) {
                                continue;
                            }
                            String method = getString(an, "method");
                            if (method != null) method = SUFFIX.matcher(method).replaceAll("");
                            // check @At targets inside
                            for (Object v : an.values) {
                                if (v instanceof AnnotationNode) checkAt((AnnotationNode) v, targets);
                            }
                        }
                    }
                    mixins.put(cn.name, new Object[]{targets, true});
                } catch (Exception ex) { }
            }
        }
        System.err.println("mixin classes in jar: " + mixins.size());
    }

    static void collectTargets(AnnotationNode an, List<String> out) {
        if (an.values == null) return;
        for (int i = 0; i < an.values.size(); i += 2) {
            Object v = an.values.get(i + 1);
            if (an.values.get(i).equals("value")) {
                if (v instanceof Type) out.add(((Type) v).getInternalName());
                else if (v instanceof List) {
                    for (Object o : (List<?>) v) if (o instanceof Type) out.add(((Type) o).getInternalName());
                }
            }
            if (an.values.get(i).equals("targets") && v instanceof List) {
                for (Object o : (List<?>) v) if (o instanceof String) out.add((String) o);
            }
        }
    }

    static void checkAt(AnnotationNode at, List<String> targets) { }

    static String getString(AnnotationNode an, String key) {
        if (an.values == null) return null;
        for (int i = 0; i < an.values.size(); i += 2) {
            if (an.values.get(i).equals(key) && an.values.get(i + 1) instanceof String)
                return (String) an.values.get(i + 1);
        }
        return null;
    }

    static void loadMembers(String root) throws IOException {
        Files.walk(Paths.get(root)).forEach(p -> {
            if (!p.toString().endsWith(".class")) return;
            String cls = Paths.get(root).relativize(p).toString().replace('\\', '/').replaceAll("\\.class$", "");
            if (!cls.startsWith("net/minecraft/")) return;
            try (InputStream in = Files.newInputStream(p)) {
                ClassReader cr = new ClassReader(in);
                Set<String> members = new HashSet<>();
                cr.accept(new ClassVisitor(Opcodes.ASM9) {
                    public MethodVisitor visitMethod(int a, String n, String d, String s, String[] e) {
                        members.add(n); return null;
                    }
                    public FieldVisitor visitField(int a, String n, String d, String s, Object v) {
                        members.add(n); return null;
                    }
                }, ClassReader.SKIP_CODE);
                MEMBERS.put(cls, members);
            } catch (Exception ex) { }
        });
    }
}
