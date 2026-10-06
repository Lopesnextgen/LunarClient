package com.moonsworth.lunar.files;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipFile;
import lombok.Generated;

public final class Files_5 {
   private static final char[] field1 = "0123456789abcdef".toCharArray();

   public static boolean method1(Path var0) {
      try {
         if (java.nio.file.Files.notExists(var0)) {
            return true;
         }

         String var1 = var0.toString();
         if (!var1.endsWith(".zip") && !var1.endsWith(".jar")) {
            return true;
         }

         try {
            new ZipFile(var0.toFile()).close();
            return false;
         } catch (Exception var3) {
            java.nio.file.Files.delete(var0);
            return true;
         }
      } catch (Throwable var4) {
         throw var4;
      }
   }

   public static String method2(Path var0) {
      return method3(var0, "SHA-1");
   }

   public static String method3(Path var0, String var1) {
      try {
         MessageDigest var2 = MessageDigest.getInstance(var1);

         try (FileInputStream var3 = new FileInputStream(var0.toFile())) {
            byte[] var4 = new byte[8192];

            for (int var5 = var3.read(var4); var5 != -1; var5 = var3.read(var4)) {
               var2.update(var4, 0, var5);
            }

            return method5(var2.digest());
         }
      } catch (Exception var9) {
         return "";
      }
   }

   public static String method4(Path var0) {
      try {
         CRC32 var1 = new CRC32();

         try (
            FileInputStream var2 = new FileInputStream(var0.toFile());
            CheckedInputStream var3 = new CheckedInputStream(var2, var1);
         ) {
            byte[] var4 = new byte[8192];
            int var5 = var3.read(var4);

            while (var5 != -1) {
               var5 = var3.read(var4);
            }

            return Long.toHexString(var1.getValue());
         }
      } catch (Exception var11) {
         return "";
      }
   }

   public static String method5(byte[] var0) {
      char[] var1 = new char[var0.length * 2];

      for (int var2 = 0; var2 < var0.length; var2++) {
         int var3 = var0[var2] & 255;
         var1[var2 * 2] = field1[var3 >>> 4];
         var1[var2 * 2 + 1] = field1[var3 & 15];
      }

      return new String(var1);
   }

   @Generated
   private Files_5() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
