import org.objectweb.asm.*;
import org.objectweb.asm.commons.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

/**
 * Rewrites all net/minecraft references in a jar using a TSV map
 * (old/internal/name<TAB>new/internal/name).  Uses ASM's Remapper, which
 * handles descriptors, annotations, signatures, field/method refs, and
 * inner classes.
 *
 * Usage: RemapRefs <in.jar> <map.tsv> <out.jar>
 */
public class RemapRefs {
    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<>();
        for (String line : Files.readAllLines(Paths.get(args[1]))) {
            if (line.isBlank() || line.startsWith("#")) continue;
            String[] p = line.split("\t");
            if (p.length >= 2) map.put(p[0], p[1]);
        }
        System.err.println("map entries: " + map.size());

        Remapper remapper = new Remapper() {
            @Override
            public String map(String internalName) {
                String r = map.get(internalName);
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
                        ClassVisitor cv = new ClassRemapper(cw, remapper);
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
        System.err.println("wrote " + args[2]);
    }
}
