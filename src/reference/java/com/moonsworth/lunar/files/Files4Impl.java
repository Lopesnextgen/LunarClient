package com.moonsworth.lunar.files;

import java.lang.ref.SoftReference;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Files4Impl extends Files4_2 {
   private final Path field1;
   private final boolean field2;
   private static final Map<String, SoftReference<byte[]>> field3 = new ConcurrentHashMap<>();

   public Files4Impl(Path var1) {
      this(var1, "true".equals(System.getProperty("mx.offline")));
   }

   public Files4Impl(Path var1, boolean var2) {
      try {
         this.field1 = var1;
         this.field2 = var2;
         java.nio.file.Files.createDirectories(var1);
      } catch (Throwable var4) {
         throw var4;
      }
   }

   @Override
   public Optional<Files2_2> method4(Files6 var1, Files3 var2) {
      String var3 = var2.url();
      if (var3 == null) {
         return Optional.empty();
      }

      if (var3.contains("${")) {
         return Optional.empty();
      }

      String var4 = this.method2(var3);
      Path var5 = this.field1.resolve(var4);
      SoftReference var6 = field3.get(var4);
      byte[] var7 = var6 != null ? (byte[])var6.get() : null;

      try {
         if (var7 == null) {
            if (java.nio.file.Files.exists(var5) && !var2.method5()) {
               long var14 = System.currentTimeMillis();
               var7 = java.nio.file.Files.readAllBytes(var5);
               long var15 = System.currentTimeMillis() - var14;
               if (var15 > 100L) {
                  Files7.field2.info("Reading from file " + var5 + " took " + var15 + "ms");
               }
            } else {
               if (this.field2) {
                  return Optional.empty();
               }

               long var8 = System.currentTimeMillis();

               try {
                  var7 = Files3_2.method3(var3);
               } catch (FilesException var12) {
               }

               if (var7 == null) {
                  return Optional.empty();
               }

               long var10 = System.currentTimeMillis() - var8;
               if (var10 > 100L) {
                  Files7.field2.info("Reading from URL " + var3 + " took " + var10 + "ms");
               }

               if (!var2.method5()) {
                  java.nio.file.Files.write(var5, var7);
               }
            }

            field3.put(var4, new SoftReference<>(var7));
         }

         return Optional.of(new Files2_2(var2, var7));
      } catch (Exception var13) {
         return Optional.empty();
      }
   }

   private String method2(String var1) {
      return var1.replaceAll("[^a-zA-Z0-9-_.]", "_");
   }

   @Override
   public void method5(Files6 var1, Files2_2 var2) {
   }
}
