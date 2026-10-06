import org.objectweb.asm.*;
import org.objectweb.asm.commons.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.zip.*;

/**
 * Rewrites class-name string constants (dotted or slashed) anywhere in the
 * constant pool using a rename map.  Covers reflection, service-like lookups,
 * and names embedded in annotations.
 *
 * Usage: StringClassRemap <in.jar> <map.tsv> <out.jar>
 */
public class StringClassRemap {
    static Map<String, String> MAP = new HashMap<>();

    public static void main(String[] args) throws Exception {
        for (String line : Files.readAllLines(Paths.get(args[1]))) {
            if (line.isBlank() || line.startsWith("#")) continue;
            String[] p = line.split("\t");
            if (p.length >= 2) {
                MAP.put(p[0], p[1]);
                MAP.put(p[0].replace('/', '.'), p[1].replace('/', '.'));
            }
        }
        System.err.println("map entries: " + MAP.size());
        final int[] n = {0};
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
                        Remapper remapper = new Remapper() {
                            public String map(String internalName) {
                                String r = MAP.get(internalName);
                                return r != null ? r : internalName;
                            }
                            public Object mapValue(Object value) {
                                if (value instanceof String) {
                                    String s = value.toString();
                                    String r = MAP.get(s);
                                    if (r != null) { n[0]++; return r; }
                                    // also try dotted names
                                    String d = s.replace('/', '.');
                                    r = MAP.get(d);
                                    if (r != null) { n[0]++; return r.replace('/', '.'); }
                                }
                                return super.mapValue(value);
                            }
                        };
                        cr.accept(new ClassRemapper(cw, remapper), 0);
                        data = cw.toByteArray();
                    } catch (Exception ex) { }
                }
                zout.putNextEntry(new ZipEntry(e.getName()));
                zout.write(data);
                zout.closeEntry();
            }
        }
        System.err.println("string constants rewritten: " + n[0]);
    }
}
