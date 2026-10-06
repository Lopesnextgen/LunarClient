import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.commons.ClassRemapper;
import org.objectweb.asm.commons.Remapper;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

/**
 * Renames classes inside a jar and rewrites all references (superclass,
 * interfaces, descriptors, annotations, code).  Map is a TSV of
 * old/internal/name<TAB>new/internal/name.
 *
 * Usage: ClassRenamer <in.jar> <map.tsv> <out.jar> [--only package/prefix]
 */
public class ClassRenamer {
    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<>();
        String only = null;
        for (int i = 1; i < args.length; i++) {
            if (args[i].equals("--only") && i + 1 < args.length) only = args[++i];
        }
        for (String line : Files.readAllLines(Paths.get(args[1]))) {
            if (line.isBlank() || line.startsWith("#")) continue;
            String[] p = line.split("\t");
            if (p.length >= 2) map.put(p[0], p[1]);
        }
        System.err.println("rename entries: " + map.size());
        try (ZipFile zip = new ZipFile(args[0]);
             ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(args[2]))) {
            Enumeration<? extends ZipEntry> en = zip.entries();
            while (en.hasMoreElements()) {
                ZipEntry e = en.nextElement();
                String name = e.getName();
                byte[] data = zip.getInputStream(e).readAllBytes();
                if (name.endsWith(".class")) {
                    try {
                        ClassReader cr = new ClassReader(data);
                        ClassWriter cw = new ClassWriter(0);
                        ClassRemapper remapper = new ClassRemapper(cw,
                                new Remapper() {
                                    public String map(String internalName) {
                                        String r = map.get(internalName);
                                        return r != null ? r : internalName;
                                    }
                                });
                        cr.accept(remapper, 0);
                        data = cw.toByteArray();
                        String cls = name.substring(0, name.length() - 6);
                        // rename the entry itself when mapped
                        String target = map.get(cls);
                        if (target == null) {
                            // also try inner-class suffix mapping
                            int d = cls.indexOf('$');
                            if (d > 0) {
                                String outer = cls.substring(0, d);
                                String to = map.get(outer);
                                if (to != null) target = to + cls.substring(d);
                            }
                        }
                        if (target != null) name = target + ".class";
                    } catch (Exception ex) {
                        System.err.println("skip " + name + ": " + ex);
                    }
                }
                zout.putNextEntry(new ZipEntry(name));
                zout.write(data);
                zout.closeEntry();
            }
        }
        System.err.println("wrote " + args[2]);
    }
}
