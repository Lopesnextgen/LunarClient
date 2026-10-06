package com.moonsworth.lunar.client.util;

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

public final class ThreadModuleDump76 {
   private static final ResourceLocationBridge field1 = ResourceLocationBridge.create("lunar", "icons/back-folder-64x64.png");
   private static final ResourceLocationBridge field2 = ResourceLocationBridge.create("lunar", "icons/folder-64x64.png");
   private static final FilenameFilter field3 = (var0, var1) -> {
      File var2 = new File(var0, var1);
      return var2.isDirectory() && !new File(var2, "pack.mcmeta").isFile();
   };
   private static final FileFilter field4 = var0 -> {
      boolean var1 = var0.isFile() && var0.getName().endsWith(".zip");
      boolean var2 = var0.isDirectory() && new File(var0, "pack.mcmeta").isFile();
      return var1 || var2;
   };

   public static boolean shouldIncludeAsset(String var0) {
      if (var0 != null && (var0.startsWith("assets/lunar/") || var0.startsWith("assets/lunar-jit/"))) {
         for (String var2 : ThreadModuleDump48.method1()) {
            if (var0.startsWith(var2)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public static File findResource(File var0, String var1) {
      for (File var5 : Objects.requireNonNullElse(var0.listFiles(field4), new File[0])) {
         if (var5.getName().equals(var1) || var1.equals("file/" + var5.getName())) {
            return var5;
         }
      }

      for (File var10 : Objects.requireNonNullElse(var0.listFiles(field3), new File[0])) {
         File var6 = findResource(var10, var1);
         if (var6 != null) {
            return var6;
         }
      }

      return null;
   }

   public static List<File> listDirectories(List<File> var0, File var1) {
      if (var1 != null && var1.isDirectory()) {
         for (File var5 : Objects.requireNonNullElse(var1.listFiles(field3), new File[0])) {
            listDirectories(var0, var5);
            var0.add(var5);
         }

         return var0;
      } else {
         return ImmutableList.of();
      }
   }

   public static void createAssetDirectory(String var0, Path var1) {
      try {
         Path var2 = var1.resolve("assets").resolve(var0);
         if (!Files.exists(var2)) {
            Files.createDirectories(var2);
         }
      } catch (Throwable var3) {
         throw var3;
      }
   }

   @Generated
   private ThreadModuleDump76() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   @Generated
   public static ResourceLocationBridge getBackFolderIcon() {
      return field1;
   }

   @Generated
   public static ResourceLocationBridge getFolderIcon() {
      return field2;
   }

   @Generated
   public static FilenameFilter getDirectoryFilter() {
      return field3;
   }

   @Generated
   public static FileFilter getPackFilter() {
      return field4;
   }
}
