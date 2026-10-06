import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.commons.ClassRemapper;
import org.objectweb.asm.commons.Remapper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Lunar Client mapping applier.
 *
 * Reads the TSV tables exported by tools/export_mappings.py and rewrites a
 * jar's classes, renaming Minecraft references from the Lunar "inflight"
 * namespace (name$v1_x, func_xxx, field_xxx, X_v1_x) to MCP names.
 */
public final class KinRemapper extends Remapper {
    private static final Map<String, String> classMap = new HashMap<>();
    private static final Map<String, String> renameMap = new HashMap<>();
    private static final Map<String, String> packageRenames = new HashMap<>();
    private static final Map<String, String> memberExact = new HashMap<>();
    private static final Map<String, String> memberByName = new HashMap<>();
    private static final Map<String, String> methodExact = new HashMap<>();
    private static final Map<String, String> fieldExact = new HashMap<>();
    private static final Map<String, String> methodGlobal = new HashMap<>();
    private static final Map<String, Integer> methodGlobalDup = new HashMap<>();
    private static final Map<String, String> fieldGlobal = new HashMap<>();
    private static final Map<String, Integer> fieldGlobalDup = new HashMap<>();
    private static final Map<String, String> srgMethods = new HashMap<>();
    private static final Map<String, String> srgFields = new HashMap<>();
    private static final Set<String> mcOwners = new HashSet<>();
    private static final Set<String> methodStripBases = new HashSet<>();
    private static final Set<String> fieldStripBases = new HashSet<>();

    private static final Set<String> renamedClassSet = new HashSet<>();
    private static long renamedMethods;
    private static long renamedFields;
    private static long leftV1;

    private static String mKey(String owner, String name, String desc) {
        return owner + '\0' + name + '\0' + desc;
    }

    private static String fKey(String owner, String name) {
        return owner + '\0' + name;
    }

    private static void addGlobal(Map<String, String> map, Map<String, Integer> dup,
                                  String key, String target) {
        String old = map.get(key);
        if (old == null) {
            map.put(key, target);
        } else if (!old.equals(target)) {
            dup.put(key, 1);
        }
    }

    private static void load(String dir) throws Exception {
        // classes.tsv
        Path classPath = Paths.get(dir, "classes.tsv");
        if (Files.exists(classPath)) {
            try (BufferedReader r = Files.newBufferedReader(classPath, StandardCharsets.UTF_8)) {
                String line;
                while ((line = r.readLine()) != null) {
                    String[] p = line.split("\t", -1);
                    if (p.length == 2) {
                        classMap.put(p[0], p[1]);
                    }
                }
            }
        }
        // members.tsv
        Path memberPath = Paths.get(dir, "members.tsv");
        if (Files.exists(memberPath)) {
            try (BufferedReader r = Files.newBufferedReader(memberPath, StandardCharsets.UTF_8)) {
                String line;
                while ((line = r.readLine()) != null) {
                    String[] p = line.split("\t", -1);
                    if (p.length != 5) {
                        continue;
                    }
                    String owner = p[0];
                    String kind = p[1];
                    String name = p[2];
                    String desc = p[3];
                    String target = p[4];
                    mcOwners.add(owner);
                    if (kind.equals("F")) {
                        fieldExact.putIfAbsent(fKey(owner, name), target);
                        addGlobal(fieldGlobal, fieldGlobalDup, name, target);
                        int v = name.indexOf("$v1_");
                        if (v > 0 && target.equals(name.substring(0, v))) {
                            fieldStripBases.add(name.substring(0, v));
                        }
                    } else {
                        methodExact.putIfAbsent(mKey(owner, name, desc), target);
                        addGlobal(methodGlobal, methodGlobalDup, name + '\0' + desc, target);
                        int v = name.indexOf("$v1_");
                        if (v > 0 && target.equals(name.substring(0, v))) {
                            methodStripBases.add(name.substring(0, v));
                        }
                    }
                }
            }
        }
        // SRG tables
        loadSrg(Paths.get(dir, "srg-methods.tsv"), srgMethods);
        loadSrg(Paths.get(dir, "srg-fields.tsv"), srgFields);
        // drop ambiguous global entries
        fieldGlobalDup.keySet().forEach(fieldGlobal::remove);
        methodGlobalDup.keySet().forEach(methodGlobal::remove);
    }

    private static void loadRenames(String path) throws Exception {
        try (BufferedReader r = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] p = line.split("\t", -1);
                if (p.length == 2) {
                    renameMap.put(p[0], p[1]);
                }
            }
        }
        System.err.println("loaded " + renameMap.size() + " explicit class renames");
    }

    private static void loadPackageRenames(String path) throws Exception {
        try (BufferedReader r = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] p = line.split("\t", -1);
                if (p.length == 2 && !p[0].isEmpty()) {
                    packageRenames.put(p[0], p[1]);
                }
            }
        }
        System.err.println("loaded " + packageRenames.size() + " package renames");
    }

    private static void loadMemberRenames(String path) throws Exception {
        try (BufferedReader r = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] p = line.split("\t", -1);
                if (p.length != 5) {
                    continue;
                }
                String owner = p[0];
                String kind = p[1];
                String name = p[2];
                String desc = p[3];
                String target = p[4];
                memberExact.put(owner + '\0' + kind + '\0' + name + '\0' + desc, target);
                memberByName.putIfAbsent(owner + '\0' + kind + '\0' + name, target);
            }
        }
        System.err.println("loaded " + memberExact.size() + " explicit member renames");
    }

    private static String mapPackage(String internalName) {
        if (packageRenames.isEmpty()) {
            return internalName;
        }
        int slash = internalName.lastIndexOf('/');
        if (slash < 0) {
            return internalName;
        }
        String pkg = internalName.substring(0, slash);
        String simple = internalName.substring(slash);
        // walk up the package path: nearest (longest) ancestor mapping wins
        String suffix = "";
        String cur = pkg;
        while (true) {
            String mapped = packageRenames.get(cur);
            if (mapped != null) {
                return mapped + suffix + simple;
            }
            int i = cur.lastIndexOf('/');
            if (i < 0) {
                return internalName;
            }
            suffix = cur.substring(i) + suffix;
            cur = cur.substring(0, i);
        }
    }

    private static void loadSrg(Path path, Map<String, String> out) throws Exception {
        if (!Files.exists(path)) {
            return;
        }
        try (BufferedReader r = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = r.readLine()) != null) {
                int tab = line.indexOf('\t');
                if (tab > 0) {
                    out.put(line.substring(0, tab), line.substring(tab + 1));
                }
            }
        }
    }

    private static boolean isMcName(String name) {
        return name.indexOf("$v1_") >= 0 || name.startsWith("func_") || name.startsWith("field_");
    }

    @Override
    public String map(String internalName) {
        String mapped = classMap.get(internalName);
        if (mapped == null) {
            mapped = internalName;
        }
        String renamed = renameMap.get(mapped);
        if (renamed == null) {
            // propagate an outer-class rename to inner classes that have no
            // explicit mapping (e.g. anonymous classes that were inlined by
            // the decompiler and therefore absent from the renames file)
            int idx = mapped.lastIndexOf('$');
            while (idx > 0) {
                String outerRenamed = renameMap.get(mapped.substring(0, idx));
                if (outerRenamed != null) {
                    renamed = outerRenamed + mapped.substring(idx);
                    break;
                }
                idx = mapped.lastIndexOf('$', idx - 1);
            }
        }
        if (renamed != null) {
            renamedClassSet.add(mapped);
            return mapPackage(renamed);
        }
        if (!mapped.equals(internalName)) {
            renamedClassSet.add(internalName);
        }
        return mapPackage(mapped);
    }

    @Override
    public String mapMethodName(String owner, String name, String desc) {
        String o = map(owner);
        String explicit = memberExact.get(owner + "\0M\0" + name + "\0" + desc);
        if (explicit == null) {
            explicit = memberExact.get(o + "\0M\0" + name + "\0" + desc);
        }
        if (explicit != null) {
            renamedMethods++;
            return explicit;
        }
        String byName = memberByName.get(owner + "\0M\0" + name);
        if (byName == null) {
            byName = memberByName.get(o + "\0M\0" + name);
        }
        if (byName != null) {
            renamedMethods++;
            return byName;
        }
        String d = mapDesc(desc);
        String target = methodExact.get(mKey(o, name, d));
        if (target == null) {
            target = methodExact.get(mKey(o, name, desc));
        }
        if (target == null && (mcOwners.contains(o) || isMcName(name))) {
            target = methodGlobal.get(name + '\0' + d);
            if (target == null) {
                target = methodGlobal.get(name + '\0' + desc);
            }
        }
        if (target == null && !name.startsWith("bridge$") && name.indexOf("$v1_") > 0) {
            String base = name.substring(0, name.indexOf("$v1_"));
            if (methodStripBases.contains(base)) {
                target = methodExact.get(mKey(o, base, d));
                if (target == null) {
                    target = methodGlobal.get(base + '\0' + d);
                }
                if (target == null) {
                    target = base;
                }
            }
        }
        if (target == null && name.startsWith("func_")) {
            target = srgMethods.get(name);
        }
        if (target == null) {
            return name;
        }
        if (!target.equals(name)) {
            renamedMethods++;
        }
        return target;
    }

    @Override
    public String mapFieldName(String owner, String name, String desc) {
        String o = map(owner);
        String explicit = memberExact.get(owner + "\0F\0" + name + "\0" + desc);
        if (explicit == null) {
            explicit = memberExact.get(o + "\0F\0" + name + "\0" + desc);
        }
        if (explicit != null) {
            renamedFields++;
            return explicit;
        }
        String byName = memberByName.get(owner + "\0F\0" + name);
        if (byName == null) {
            byName = memberByName.get(o + "\0F\0" + name);
        }
        if (byName != null) {
            renamedFields++;
            return byName;
        }
        String target = fieldExact.get(fKey(o, name));
        if (target == null && (mcOwners.contains(o) || isMcName(name))) {
            target = fieldGlobal.get(name);
        }
        if (target == null && !name.startsWith("bridge$") && name.indexOf("$v1_") > 0) {
            String base = name.substring(0, name.indexOf("$v1_"));
            if (fieldStripBases.contains(base)) {
                target = fieldExact.get(fKey(o, base));
                if (target == null) {
                    target = fieldGlobal.get(base);
                }
            }
        }
        if (target == null && name.startsWith("field_")) {
            target = srgFields.get(name);
        }
        if (target == null) {
            return name;
        }
        if (!target.equals(name)) {
            renamedFields++;
        }
        return target;
    }

    @Override
    public String mapRecordComponentName(String owner, String name, String descriptor) {
        return mapFieldName(owner, name, descriptor);
    }

    private static void remapJar(Path in, Path out) throws Exception {
        try (ZipInputStream zin = new ZipInputStream(Files.newInputStream(in));
             ZipOutputStream zout = new ZipOutputStream(Files.newOutputStream(out))) {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                String name = entry.getName();
                if (name.startsWith("META-INF/")
                        && (name.endsWith(".SF") || name.endsWith(".RSA") || name.endsWith(".DSA"))) {
                    continue;
                }
                if (name.endsWith(".class") && !name.equals("module-info.class")) {
                    byte[] data = zin.readAllBytes();
                    String entryName = name;
                    try {
                        ClassReader cr = new ClassReader(data);
                        ClassWriter cw = new ClassWriter(0);
                        KinRemapper kr = new KinRemapper();
                        ClassRemapper remapper = new ClassRemapper(cw, kr);
                        cr.accept(remapper, 0);
                        data = cw.toByteArray();
                        String newName = kr.map(cr.getClassName()) + ".class";
                        if (!newName.equals(name)) {
                            entryName = newName;
                        }
                    } catch (Throwable t) {
                        System.err.println("WARN failed to remap " + name + ": " + t);
                    }
                    ZipEntry e = new ZipEntry(entryName);
                    zout.putNextEntry(e);
                    zout.write(data);
                    zout.closeEntry();
                } else {
                    ZipEntry e = new ZipEntry(name);
                    zout.putNextEntry(e);
                    zin.transferTo(zout);
                    zout.closeEntry();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 3 || args.length > 6) {
            System.err.println("usage: KinRemapper <mappingsDir> <input.jar> <output.jar> "
                    + "[class-renames.tsv] [member-renames.tsv] [package-renames.tsv]");
            System.exit(2);
        }
        long t0 = System.currentTimeMillis();
        load(args[0]);
        if (args.length >= 4 && !args[3].equals("-")) {
            loadRenames(args[3]);
        }
        if (args.length >= 5 && !args[4].equals("-")) {
            loadMemberRenames(args[4]);
        }
        if (args.length >= 6 && !args[5].equals("-")) {
            loadPackageRenames(args[5]);
        }
        System.err.printf("loaded: %d class mappings, %d explicit renames, %d member renames, "
                        + "%d package renames, %d owners%n",
                classMap.size(), renameMap.size(), memberExact.size(),
                packageRenames.size(), mcOwners.size());
        remapJar(Paths.get(args[1]), Paths.get(args[2]));
        System.err.printf("remapped in %.1fs: classes=%d methods=%d fields=%d%n",
                (System.currentTimeMillis() - t0) / 1000.0,
                renamedClassSet.size(), renamedMethods, renamedFields);
    }
}
