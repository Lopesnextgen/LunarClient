import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

import java.io.*;
import java.util.*;
import java.util.zip.*;

/**
 * Rewrites the superclass of classes matching a name pattern.
 * Usage: SetSuper <in.jar> <out.jar> <classPatternRegex> <newSuper>
 */
public class SetSuper {
    public static void main(String[] args) throws Exception {
        java.util.regex.Pattern pat = java.util.regex.Pattern.compile(args[2]);
        String sup = args[3];
        try (ZipFile zip = new ZipFile(args[0]);
             ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(args[1]))) {
            Enumeration<? extends ZipEntry> en = zip.entries();
            while (en.hasMoreElements()) {
                ZipEntry e = en.nextElement();
                byte[] data = zip.getInputStream(e).readAllBytes();
                if (e.getName().endsWith(".class") && pat.matcher(e.getName()).find()) {
                    try {
                        ClassNode cn = new ClassNode();
                        new ClassReader(data).accept(cn, 0);
                        String old = cn.superName;
                        cn.superName = sup;
                        ClassWriter cw = new ClassWriter(0);
                        cn.accept(cw);
                        data = cw.toByteArray();
                        System.err.println("setSuper " + e.getName() + ": " + old + " -> " + sup);
                    } catch (Exception ex) {
                        System.err.println("skip " + e.getName() + ": " + ex);
                    }
                }
                zout.putNextEntry(new ZipEntry(e.getName()));
                zout.write(data);
                zout.closeEntry();
            }
        }
    }
}
