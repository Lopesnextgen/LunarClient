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

public final class JarUtils {
   public static void method1(Path path0, Path path1, boolean flag2, BiFunction<String, byte[], ClassBytes> function3) {
      Files.createDirectories(path1.getParent());
      ArrayDeque arraydeque4 = new ArrayDeque();
      arraydeque4.add(path0);

      try (JarOutputStream jaroutputstream5 = new JarOutputStream(new FileOutputStream(path1.toFile()))) {
         while (!arraydeque4.isEmpty()) {
            Path path6 = (Path)arraydeque4.poll();
            if (path6 != null) {
               try (JarFile jarfile7 = new JarFile(path6.toFile())) {
                  IchorLogger.field1.info("Adding " + path6 + " to " + path1);
                  Enumeration enumeration8 = jarfile7.entries();

                  while (enumeration8.hasMoreElements()) {
                     JarEntry jarentry9 = (JarEntry)enumeration8.nextElement();
                     String text10 = jarentry9.getName();
                     if (!text10.equals("META-INF/MANIFEST.MF")
                        && (
                           !text10.startsWith("META-INF/")
                              || !text10.endsWith(".SF") && !text10.endsWith(".RSA") && !text10.endsWith(".DSA") && !text10.endsWith(".EC")
                        )
                        && !text10.endsWith(".dylib")
                        && !text10.endsWith(".so")
                        && !text10.endsWith(".dll")) {
                        InputStream input11 = jarfile7.getInputStream(jarentry9);
                        byte[] items12 = ByteStreams.toByteArray(input11);
                        input11.close();
                        boolean flag13 = false;
                        if (text10.endsWith(".jar")) {
                           Path path21 = Files.createTempFile(path1.getParent(), "temp", ".jar");
                           Files.write(path21, items12);
                           arraydeque4.add(path21);
                           IchorLogger.field1.info("Added jar-in-jar " + text10 + " from " + path6 + " to " + path1);
                        } else {
                           if (text10.endsWith(".class")) {
                              flag13 = true;
                              String text14 = text10.substring(0, text10.length() - ".class".length()).replace('/', '.');
                              ClassBytes fatalichorerror1415 = (ClassBytes)function3.apply(text14, items12);
                              items12 = fatalichorerror1415.method1();
                              text10 = fatalichorerror1415.className().replace('.', '/') + ".class";
                           }

                           if ((flag2 || flag13) && items12 != null) {
                              try {
                                 jaroutputstream5.putNextEntry(new ZipEntry(text10));
                                 jaroutputstream5.write(items12);
                                 jaroutputstream5.closeEntry();
                              } catch (ZipException zipexception18) {
                                 if (!zipexception18.getMessage().contains("duplicate entry")) {
                                    throw zipexception18;
                                 }
                              }
                           } else if (flag13) {
                              throw new IllegalStateException("Class " + text10 + " became null?");
                           }
                        }
                     }
                  }
               }
            }
         }

         jaroutputstream5.flush();
      }
   }

   public static void method2(Path path0, Path path1, BiFunction<String, byte[], ClassBytes> function2) {
      method1(path0, path1, true, function2);
   }

   public static void method3(Path path0, Path path1, Consumer<ClassNode> consumer2, ClassLoader classloader3) {
      method1(path0, path1, true, (arg2x, arg3x) -> {
         ClassNode node4 = AsmUtils.method16(arg3x, 0);
         consumer2.accept(node4);
         return new ClassBytes(arg2x, AsmUtils.method33(node4, classloader3, 0));
      });
   }

   public static void method4(TreeMap<String, byte[]> map0, File file1) {
      JarOutputStream jaroutputstream2 = new JarOutputStream(new FileOutputStream(file1));
      FileTime filetime3 = FileTime.from(Instant.EPOCH);

      for (Entry entry5 : map0.entrySet()) {
         String text6 = (String)entry5.getKey();
         byte[] items7 = (byte[])entry5.getValue();
         ZipEntry zipentry8 = new ZipEntry(text6);
         zipentry8.setCreationTime(filetime3);
         zipentry8.setLastAccessTime(filetime3);
         zipentry8.setLastModifiedTime(filetime3);
         jaroutputstream2.putNextEntry(zipentry8);
         jaroutputstream2.write(items7);
         jaroutputstream2.closeEntry();
      }

      jaroutputstream2.close();
   }

   public static void method5(File file0, File file1) {
      TreeMap map2 = new TreeMap();
      JarFile jarfile3 = new JarFile(file0);
      Enumeration enumeration4 = jarfile3.entries();

      while (enumeration4.hasMoreElements()) {
         JarEntry jarentry5 = (JarEntry)enumeration4.nextElement();
         String text6 = jarentry5.getName();
         InputStream input7 = jarfile3.getInputStream(jarentry5);
         byte[] items8 = ByteStreams.toByteArray(jarfile3.getInputStream(jarentry5));
         map2.put(text6, items8);
         input7.close();
      }

      jarfile3.close();
      method4(map2, file1);
   }

   public static void method6(Path path0, Set<String> set1) {
      try {
         if (Files.exists(path0)) {
            try (ZipFile zipfile2 = new ZipFile(path0.toFile())) {
               Enumeration enumeration3 = zipfile2.entries();

               while (enumeration3.hasMoreElements()) {
                  ZipEntry zipentry4 = (ZipEntry)enumeration3.nextElement();
                  String text5 = zipentry4.getName();
                  if (text5.endsWith(".class")) {
                     text5 = text5.substring(0, text5.length() - ".class".length());
                     if (text5.startsWith("notch/")) {
                        text5 = text5.substring("notch/".length());
                     }

                     text5 = text5.replace('/', '.');
                     set1.add(text5);
                  }
               }
            } catch (IOException exception8) {
               throw new IOException("Error while collecting class names for " + path0, exception8);
            }
         }
      } catch (Throwable exception9) {
         throw exception9;
      }
   }

   public static Map<String, byte[]> method7(Path path0, Predicate<String> predicate1) {
      try {
         HashMap map2 = new HashMap();
         if (Files.exists(path0)) {
            try (ZipFile zipfile3 = new ZipFile(path0.toFile())) {
               Enumeration enumeration4 = zipfile3.entries();

               while (enumeration4.hasMoreElements()) {
                  ZipEntry zipentry5 = (ZipEntry)enumeration4.nextElement();
                  String text6 = zipentry5.getName();
                  if (text6.endsWith(".class")) {
                     text6 = text6.substring(0, text6.length() - ".class".length()).replace('/', '.');
                     if (predicate1.test(text6)) {
                        byte[] items7 = ByteStreams.toByteArray(zipfile3.getInputStream(zipentry5));
                        map2.put(text6.intern(), items7);
                     }
                  }
               }
            } catch (IOException exception10) {
               throw new IOException("Error while loading classes for " + path0, exception10);
            }
         }

         return map2;
      } catch (Throwable exception11) {
         throw exception11;
      }
   }

   public static void method8(Path path0, Consumer<Path> consumer1) {
      if (Files.isDirectory(path0)) {
         try (Stream stream2 = Files.list(path0)) {
            stream2.filter(arg0x -> arg0x.getFileName().toString().endsWith(".jar")).forEach(consumer1);
         } catch (IOException exception7) {
            exception7.printStackTrace();
         }
      }
   }

   public static boolean method9(Path path0) {
      try {
         if (Files.notExists(path0)) {
            return false;
         }

         String text1 = path0.toString();
         if (!text1.endsWith(".zip") && !text1.endsWith(".jar")) {
            return false;
         }

         try (ZipFile zipfile2 = new ZipFile(path0.toFile())) {
            return true;
         } catch (Exception exception7) {
            Files.delete(path0);
            return false;
         }
      } catch (Throwable exception8) {
         throw exception8;
      }
   }

   @Generated
   private JarUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
