package com.moonsworth.lunar.ichor.util;

import com.google.common.io.ByteStreams;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import lombok.Generated;
import org.objectweb.asm.tree.ClassNode;

public final class FatalIchorError9 {
   public static void method1(Path var0, Path var1, boolean var2, BiFunction<String, byte[], FatalIchorError14> var3) {
      Files.createDirectories(var1.getParent());
      ArrayDeque var4 = new ArrayDeque();
      var4.add(var0);

      try (JarOutputStream var5 = new JarOutputStream(new FileOutputStream(var1.toFile()))) {
         while (!var4.isEmpty()) {
            Path var6 = (Path)var4.poll();
            if (var6 != null) {
               try (JarFile var7 = new JarFile(var6.toFile())) {
                  FatalIchorError5.field1.info("Adding " + var6 + " to " + var1);
                  Enumeration var8 = var7.entries();

                  while (var8.hasMoreElements()) {
                     JarEntry var9 = (JarEntry)var8.nextElement();
                     String var10 = var9.getName();
                     if (!var10.equals("META-INF/MANIFEST.MF")
                        && (
                           !var10.startsWith("META-INF/")
                              || !var10.endsWith(".SF") && !var10.endsWith(".RSA") && !var10.endsWith(".DSA") && !var10.endsWith(".EC")
                        )
                        && !var10.endsWith(".dylib")
                        && !var10.endsWith(".so")
                        && !var10.endsWith(".dll")) {
                        InputStream var11 = var7.getInputStream(var9);
                        byte[] var12 = ByteStreams.toByteArray(var11);
                        var11.close();
                        boolean var13 = false;
                        if (var10.endsWith(".jar")) {
                           Path var21 = Files.createTempFile(var1.getParent(), "temp", ".jar");
                           Files.write(var21, var12);
                           var4.add(var21);
                           FatalIchorError5.field1.info("Added jar-in-jar " + var10 + " from " + var6 + " to " + var1);
                        } else {
                           if (var10.endsWith(".class")) {
                              var13 = true;
                              String var14 = var10.substring(0, var10.length() - ".class".length()).replace('/', '.');
                              FatalIchorError14 var15 = (FatalIchorError14)var3.apply(var14, var12);
                              var12 = var15.method1();
                              var10 = var15.className().replace('.', '/') + ".class";
                           }

                           if ((var2 || var13) && var12 != null) {
                              try {
                                 var5.putNextEntry(new ZipEntry(var10));
                                 var5.write(var12);
                                 var5.closeEntry();
                              } catch (ZipException var18) {
                                 if (!var18.getMessage().contains("duplicate entry")) {
                                    throw var18;
                                 }
                              }
                           } else if (var13) {
                              throw new IllegalStateException("Class " + var10 + " became null?");
                           }
                        }
                     }
                  }
               }
            }
         }

         var5.flush();
      }
   }

   public static void method2(Path var0, Path var1, BiFunction<String, byte[], FatalIchorError14> var2) {
      method1(var0, var1, true, var2);
   }

   public static void method3(Path var0, Path var1, Consumer<ClassNode> var2, ClassLoader var3) {
      method1(var0, var1, true, (var2x, var3x) -> {
         ClassNode var4 = FatalIchorError6.method16(var3x, 0);
         var2.accept(var4);
         return new FatalIchorError14(var2x, FatalIchorError6.method33(var4, var3, 0));
      });
   }

   public static void method4(TreeMap<String, byte[]> var0, File var1) {
      JarOutputStream var2 = new JarOutputStream(new FileOutputStream(var1));
      FileTime var3 = FileTime.from(Instant.EPOCH);

      for (Entry var5 : var0.entrySet()) {
         String var6 = (String)var5.getKey();
         byte[] var7 = (byte[])var5.getValue();
         ZipEntry var8 = new ZipEntry(var6);
         var8.setCreationTime(var3);
         var8.setLastAccessTime(var3);
         var8.setLastModifiedTime(var3);
         var2.putNextEntry(var8);
         var2.write(var7);
         var2.closeEntry();
      }

      var2.close();
   }

   public static void method5(File var0, File var1) {
      TreeMap var2 = new TreeMap();
      JarFile var3 = new JarFile(var0);
      Enumeration var4 = var3.entries();

      while (var4.hasMoreElements()) {
         JarEntry var5 = (JarEntry)var4.nextElement();
         String var6 = var5.getName();
         InputStream var7 = var3.getInputStream(var5);
         byte[] var8 = ByteStreams.toByteArray(var3.getInputStream(var5));
         var2.put(var6, var8);
         var7.close();
      }

      var3.close();
      method4(var2, var1);
   }

   public static void method6(Path var0, Set<String> var1) {
      try {
         if (Files.exists(var0)) {
            try (ZipFile var2 = new ZipFile(var0.toFile())) {
               Enumeration var3 = var2.entries();

               while (var3.hasMoreElements()) {
                  ZipEntry var4 = (ZipEntry)var3.nextElement();
                  String var5 = var4.getName();
                  if (var5.endsWith(".class")) {
                     var5 = var5.substring(0, var5.length() - ".class".length());
                     if (var5.startsWith("notch/")) {
                        var5 = var5.substring("notch/".length());
                     }

                     var5 = var5.replace('/', '.');
                     var1.add(var5);
                  }
               }
            } catch (IOException var8) {
               throw new IOException("Error while collecting class names for " + var0, var8);
            }
         }
      } catch (Throwable var9) {
         throw var9;
      }
   }

   public static Map<String, byte[]> method7(Path var0, Predicate<String> var1) {
      try {
         HashMap var2 = new HashMap();
         if (Files.exists(var0)) {
            try (ZipFile var3 = new ZipFile(var0.toFile())) {
               Enumeration var4 = var3.entries();

               while (var4.hasMoreElements()) {
                  ZipEntry var5 = (ZipEntry)var4.nextElement();
                  String var6 = var5.getName();
                  if (var6.endsWith(".class")) {
                     var6 = var6.substring(0, var6.length() - ".class".length()).replace('/', '.');
                     if (var1.test(var6)) {
                        byte[] var7 = ByteStreams.toByteArray(var3.getInputStream(var5));
                        var2.put(var6.intern(), var7);
                     }
                  }
               }
            } catch (IOException var10) {
               throw new IOException("Error while loading classes for " + var0, var10);
            }
         }

         return var2;
      } catch (Throwable var11) {
         throw var11;
      }
   }

   public static void method8(Path var0, Consumer<Path> var1) {
      if (Files.isDirectory(var0)) {
         try (Stream var2 = Files.list(var0)) {
            var2.filter(var0x -> var0x.getFileName().toString().endsWith(".jar")).forEach(var1);
         } catch (IOException var7) {
            var7.printStackTrace();
         }
      }
   }

   public static boolean method9(Path var0) {
      try {
         if (Files.notExists(var0)) {
            return false;
         }

         String var1 = var0.toString();
         if (!var1.endsWith(".zip") && !var1.endsWith(".jar")) {
            return false;
         }

         try (ZipFile var2 = new ZipFile(var0.toFile())) {
            return true;
         } catch (Exception var7) {
            Files.delete(var0);
            return false;
         }
      } catch (Throwable var8) {
         throw var8;
      }
   }

   @Generated
   private FatalIchorError9() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
