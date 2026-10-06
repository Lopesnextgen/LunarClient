import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

import com.google.gson.*;
import com.google.gson.stream.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.zip.*;

/**
 * Filters a mixin config to only 1.8.9-compatible mixins.
 *
 * For each mixin listed in the config:
 *   - resolve the class in the jar (package + name)
 *   - read its @Mixin target
 *   - for each injection annotation, strip $v1_x suffixes from member names and
 *     verify the member exists in the target class (from a compiled class root)
 *   - drop the mixin when any member (or the target) is missing
 *
 * Usage: MixinFilter <jar> <in.json> <out.json> <mcpClassRoot>
 */
public class MixinFilter {
    static Map<String, Set<String>> MEMBERS = new HashMap<>();
    static final Pattern SUFFIX = Pattern.compile("\\$v1_(?:7|8|12)");
    static final Pattern MEMBER_IN_TARGET = Pattern.compile("L([A-Za-z0-9_/$]+);([A-Za-z0-9_$<>]+)");

    public static void main(String[] args) throws Exception {
        String jarPath = args[0], inJson = args[1], outJson = args[2], root = args[3];
        loadMembers(root);
        System.err.println("target classes indexed: " + MEMBERS.size());

        JsonObject cfg;
        try (Reader r = new FileReader(inJson)) {
            cfg = new JsonParser().parse(r).getAsJsonObject();
        }
        String pkg = cfg.has("package") ? cfg.get("package").getAsString() : "";
        JsonArray mixins = cfg.getAsJsonArray("mixins");
        JsonArray kept = new JsonArray();
        int dropped = 0;
        try (ZipFile zip = new ZipFile(jarPath)) {
            for (JsonElement el : mixins) {
                String m = el.getAsString();
                String cls = pkg.replace('.', '/') + '/' + m.replace('.', '$');
                String reason = check(zip, cls);
                if (reason == null) {
                    kept.add(new JsonPrimitive(m));
                } else {
                    dropped++;
                    System.err.println("drop " + m + " (" + reason + ")");
                }
            }
        }
        cfg.add("mixins", kept);
        cfg.addProperty("required", false);
        if (cfg.has("injectors") && cfg.get("injectors").isJsonObject()) {
            cfg.getAsJsonObject("injectors").addProperty("defaultRequire", 0);
        } else {
            JsonObject inj = new JsonObject();
            inj.addProperty("defaultRequire", 0);
            cfg.add("injectors", inj);
        }
        try (Writer w = new FileWriter(outJson)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(cfg, w);
        }
        System.err.println("kept " + kept.size() + " dropped " + dropped);
    }

    /** Returns null when compatible, else a reason string. */
    static String check(ZipFile zip, String cls) {
        ZipEntry e = zip.getEntry(cls + ".class");
        if (e == null) return "class missing";
        try (InputStream in = zip.getInputStream(e)) {
            ClassNode cn = new ClassNode();
            new ClassReader(in).accept(cn, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
            List<String> targets = new ArrayList<>();
            List<AnnotationNode> annos = new ArrayList<>();
            if (cn.visibleAnnotations != null) annos.addAll(cn.visibleAnnotations);
            if (cn.invisibleAnnotations != null) annos.addAll(cn.invisibleAnnotations);
            for (AnnotationNode an : annos) {
                if (!an.desc.equals("Lorg/spongepowered/asm/mixin/Mixin;")) continue;
                if (an.values == null) continue;
                for (int i = 0; i < an.values.size(); i += 2) {
                    Object v = an.values.get(i + 1);
                    if (an.values.get(i).equals("value")) {
                        if (v instanceof Type) targets.add(((Type) v).getInternalName());
                        else if (v instanceof List)
                            for (Object o : (List<?>) v) if (o instanceof Type)
                                targets.add(((Type) o).getInternalName());
                    } else if (an.values.get(i).equals("targets") && v instanceof List) {
                        for (Object o : (List<?>) v) if (o instanceof String) targets.add((String) o);
                    }
                }
            }
            if (targets.isEmpty()) return "no target";
            for (String t : targets) {
                // strip version suffix from target name too (Foo_v1_7 -> Foo)
                String tt = t.contains("_v1_") ? t.substring(0, t.indexOf("_v1_")) : t;
                if (!MEMBERS.containsKey(tt)) {
                    if (!MEMBERS.containsKey(t)) return "target missing " + t;
                }
            }
            // check method annotations
            for (MethodNode mn : cn.methods) {
                List<AnnotationNode> as = new ArrayList<>();
                if (mn.visibleAnnotations != null) as.addAll(mn.visibleAnnotations);
                if (mn.invisibleAnnotations != null) as.addAll(mn.invisibleAnnotations);
                for (AnnotationNode an : as) {
                    if (!an.desc.startsWith("Lorg/spongepowered/asm/mixin/injection/") &&
                        !an.desc.contains("WrapOperation") &&
                        !an.desc.startsWith("Lcom/llamalad7/")) continue;
                    if (an.values == null) continue;
                    for (int i = 0; i < an.values.size(); i += 2) {
                        String k = (String) an.values.get(i);
                        Object v = an.values.get(i + 1);
                        if (k.equals("method") && v instanceof String) {
                            String mem = SUFFIX.matcher((String) v).replaceAll("");
                            mem = mem.split("\\(")[0];
                            if (!memberIn(targets, mem)) return "method missing " + mem;
                        } else if (k.equals("target") && v instanceof String) {
                            Matcher mm = MEMBER_IN_TARGET.matcher((String) v);
                            while (mm.find()) {
                                String tc = mm.group(1);
                                String mem = SUFFIX.matcher(mm.group(2)).replaceAll("");
                                if (!memberIn(Collections.singletonList(tc), mem)) return "at target missing " + tc + "." + mem;
                            }
                        } else if (v instanceof AnnotationNode) {
                            AnnotationNode sub = (AnnotationNode) v;
                            if (sub.values != null) {
                                for (int j = 0; j < sub.values.size(); j += 2) {
                                    Object sv = sub.values.get(j + 1);
                                    if (sub.values.get(j).equals("target") && sv instanceof String) {
                                        Matcher mm = MEMBER_IN_TARGET.matcher((String) sv);
                                        while (mm.find()) {
                                            String tc = mm.group(1);
                                            String mem = SUFFIX.matcher(mm.group(2)).replaceAll("");
                                            if (!memberIn(Collections.singletonList(tc), mem))
                                                return "at target missing " + tc + "." + mem;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return null;
        } catch (Exception ex) {
            return "parse error " + ex;
        }
    }

    static boolean memberIn(List<String> targets, String member) {
        for (String t : targets) {
            String tt = t.contains("_v1_") ? t.substring(0, t.indexOf("_v1_")) : t;
            Set<String> set = MEMBERS.get(tt);
            if (set != null && set.contains(member)) return true;
            set = MEMBERS.get(t);
            if (set != null && set.contains(member)) return true;
        }
        return false;
    }

    static void loadMembers(String root) throws IOException {
        Files.walk(Paths.get(root)).forEach(p -> {
            String s = p.toString();
            if (!s.endsWith(".class")) return;
            String cls = Paths.get(root).relativize(p).toString().replace('\\', '/').replaceAll("\\.class$", "");
            if (!cls.startsWith("net/minecraft/")) return;
            try (InputStream in = Files.newInputStream(p)) {
                Set<String> members = new HashSet<>();
                new ClassReader(in).accept(new ClassVisitor(Opcodes.ASM9) {
                    public MethodVisitor visitMethod(int a, String n, String d, String s2, String[] e) {
                        members.add(n); return null;
                    }
                    public FieldVisitor visitField(int a, String n, String d, String s2, Object v) {
                        members.add(n); return null;
                    }
                }, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
                MEMBERS.put(cls, members);
            } catch (Exception ex) { }
        });
    }
}
