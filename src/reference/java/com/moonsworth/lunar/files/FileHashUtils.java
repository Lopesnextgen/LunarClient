package com.moonsworth.lunar.files;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipFile;
import lombok.Generated;

public final class FileHashUtils {
   private static final char[] field1 = "0123456789abcdef".toCharArray();

   public static boolean method1(Path path0) {
      try {
         if (java.nio.file.Files.notExists(path0)) {
            return true;
         }

         String text1 = path0.toString();
         if (!text1.endsWith(".zip") && !text1.endsWith(".jar")) {
            return true;
         }

         try {
            new ZipFile(path0.toFile()).close();
            return false;
         } catch (Exception exception3) {
            java.nio.file.Files.delete(path0);
            return true;
         }
      } catch (Throwable exception4) {
         throw exception4;
      }
   }

   public static String method2(Path path0) {
      return method3(path0, "SHA-1");
   }

   public static String method3(Path path0, String text1) {
      try {
         MessageDigest messagedigest2 = MessageDigest.getInstance(text1);

         try (FileInputStream stream3 = new FileInputStream(path0.toFile())) {
            byte[] items4 = new byte[8192];

            for (int index5 = stream3.read(items4); index5 != -1; index5 = stream3.read(items4)) {
               messagedigest2.update(items4, 0, index5);
            }

            return method5(messagedigest2.digest());
         }
      } catch (Exception exception9) {
         return "";
      }
   }

   public static String method4(Path path0) {
      try {
         CRC32 crc321 = new CRC32();

         try (
            FileInputStream stream2 = new FileInputStream(path0.toFile());
            CheckedInputStream checkedinputstream3 = new CheckedInputStream(stream2, crc321);
         ) {
            byte[] items4 = new byte[8192];
            int number5 = checkedinputstream3.read(items4);

            while (number5 != -1) {
               number5 = checkedinputstream3.read(items4);
            }

            return Long.toHexString(crc321.getValue());
         }
      } catch (Exception exception11) {
         return "";
      }
   }

   public static String method5(byte[] items0) {
      char[] items1 = new char[items0.length * 2];

      for (int index2 = 0; index2 < items0.length; index2++) {
         int index3 = items0[index2] & 255;
         items1[index2 * 2] = field1[index3 >>> 4];
         items1[index2 * 2 + 1] = field1[index3 & 15];
      }

      return new String(items1);
   }

   @Generated
   private FileHashUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
