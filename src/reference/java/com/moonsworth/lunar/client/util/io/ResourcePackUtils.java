package com.moonsworth.lunar.client.util.io;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import lombok.Generated;
import com.moonsworth.lunar.client.framework.LunarConstants;

public final class ResourcePackUtils {
   private static final ResourceLocationBridge field1 = ResourceLocationBridge.create("lunar", "icons/back-folder-64x64.png");
   private static final ResourceLocationBridge field2 = ResourceLocationBridge.create("lunar", "icons/folder-64x64.png");
   private static final FilenameFilter field3 = (arg0, arg1) -> {
      File file2 = new File(arg0, arg1);
      return file2.isDirectory() && !new File(file2, "pack.mcmeta").isFile();
   };
   private static final FileFilter field4 = arg0 -> {
      boolean flag1 = arg0.isFile() && arg0.getName().endsWith(".zip");
      boolean flag2 = arg0.isDirectory() && new File(arg0, "pack.mcmeta").isFile();
      return flag1 || flag2;
   };

   public static boolean method1(String text0) {
      if (text0 != null && (text0.startsWith("assets/lunar/") || text0.startsWith("assets/lunar-jit/"))) {
         for (String text2 : LunarConstants.method1()) {
            if (text0.startsWith(text2)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public static File method2(File file0, String text1) {
      for (File file5 : Objects.requireNonNullElse(file0.listFiles(field4), new File[0])) {
         if (file5.getName().equals(text1) || text1.equals("file/" + file5.getName())) {
            return file5;
         }
      }

      for (File file10 : Objects.requireNonNullElse(file0.listFiles(field3), new File[0])) {
         File file6 = method2(file10, text1);
         if (file6 != null) {
            return file6;
         }
      }

      return null;
   }

   public static List<File> method3(List<File> list0, File file1) {
      if (file1 != null && file1.isDirectory()) {
         for (File file5 : Objects.requireNonNullElse(file1.listFiles(field3), new File[0])) {
            method3(list0, file5);
            list0.add(file5);
         }

         return list0;
      } else {
         return ImmutableList.of();
      }
   }

   public static void method4(String text0, Path path1) {
      try {
         Path path2 = path1.resolve("assets").resolve(text0);
         if (!Files.exists(path2)) {
            Files.createDirectories(path2);
         }
      } catch (Throwable exception3) {
         throw exception3;
      }
   }

   @Generated
   private ResourcePackUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   @Generated
   public static ResourceLocationBridge method5() {
      return field1;
   }

   @Generated
   public static ResourceLocationBridge method6() {
      return field2;
   }

   @Generated
   public static FilenameFilter method7() {
      return field3;
   }

   @Generated
   public static FileFilter method8() {
      return field4;
   }
}
