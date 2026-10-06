package com.moonsworth.lunar.files;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Function;
import javax.annotation.Nullable;
import org.cadixdev.lorenz.io.MappingsReader;
import org.cadixdev.lorenz.io.srg.SrgReader;
import org.cadixdev.lorenz.io.srg.csrg.CSrgReader;
import org.cadixdev.lorenz.io.srg.tsrg.TSrgReader;
import org.cadixdev.lorenz.io.srg.xsrg.XSrgReader;

public class Files4Base2 extends Files4Base {
   private final Path field2;

   public Files4Base2(Files4_2 var1, Path var2) {
      super(var1);

      try {
         this.field2 = var2;
         if (!java.nio.file.Files.exists(var2)) {
            java.nio.file.Files.createDirectories(var2);
         }
      } catch (Throwable var4) {
         throw var4;
      }
   }

   public Path method1(Files6 var1, Files3 var2) {
      String var3 = var1.method5().stream().filter(var0 -> var0.key().equals("mcVer")).findFirst().orElseThrow().value();
      String var4 = var3
         + "/"
         + var2.namespace()
         + "/"
         + var1.method6().getId()
         + "-"
         + Integer.toHexString(var1.method6().hashCode())
         + "/"
         + var2.method1(var1).method4();
      return this.field2.resolve(var4);
   }

   @Override
   public Optional<Files2_2> method4(Files6 var1, Files3 var2) {
      try {
         Path var3 = this.method1(var1, var2);
         byte[] var4 = this.method4(var3);
         if (!var2.method5() && var4 != null) {
            return Optional.of(new Files2_2(var2, var4));
         }

         Optional var5 = super.method4(var1, var2);
         var5.ifPresent(var2x -> this.method5(var1, var2x));
         return var5.isEmpty() && var4 != null ? Optional.of(new Files2_2(var2, var4)) : var5;
      } catch (Throwable var6) {
         throw var6;
      }
   }

   @Override
   public void method5(Files6 var1, Files2_2 var2) {
      try {
         if (var2.method1() != null) {
            Path var3 = this.method1(var1, var2.method1());
            java.nio.file.Files.createDirectories(var3.getParent());
            java.nio.file.Files.write(var3, var2.method2());
         }

         super.method5(var1, var2);
      } catch (Throwable var4) {
         throw var4;
      }
   }

   @Nullable
   public byte[] method4(Path var1) {
      try {
         if (java.nio.file.Files.exists(var1)) {
            try {
               byte[] var2 = java.nio.file.Files.readAllBytes(var1);
               String var3 = var1.toString();
               if (var3.endsWith(".srg")) {
                  this.method5(var2, SrgReader::new);
               } else if (var3.endsWith(".xsrg")) {
                  this.method5(var2, XSrgReader::new);
               } else if (var3.endsWith(".csrg")) {
                  this.method5(var2, CSrgReader::new);
               } else if (var3.endsWith(".tsrg")) {
                  this.method5(var2, TSrgReader::new);
               } else if (var3.endsWith(".kin")) {
                  this.method6(var2);
               }

               return var2;
            } catch (Exception var4) {
               if (var4.getMessage() != null && !var4.getMessage().contains("tsrg2")) {
                  new IllegalStateException("Failed to read data in " + var1, var4).printStackTrace();
                  java.nio.file.Files.delete(var1);
               }
            }
         }

         return null;
      } catch (Throwable var5) {
         throw var5;
      }
   }

   private void method5(byte[] var1, Function<Reader, MappingsReader> var2) {
      try {
         MappingsReader var3 = (MappingsReader)var2.apply(new InputStreamReader(new ByteArrayInputStream(var1), StandardCharsets.UTF_8));
         var3.read();
         var3.close();
      } catch (Throwable var4) {
         throw var4;
      }
   }

   private void method6(byte[] var1) {
      try {
         MappingsReader var2 = Files5_2.field1.createReader(new ByteArrayInputStream(var1));

         try {
            var2.read();
         } catch (Throwable var6) {
            if (var2 != null) {
               try {
                  var2.close();
               } catch (Throwable var5) {
                  var6.addSuppressed(var5);
               }
            }

            throw var6;
         }

         if (var2 != null) {
            var2.close();
         }
      } catch (Throwable var7) {
         throw var7;
      }
   }
}
