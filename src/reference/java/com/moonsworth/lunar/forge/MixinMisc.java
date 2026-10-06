package com.moonsworth.lunar.forge;

import com.moonsworth.lunar.forge.lib.InputStreamLoader3_2;
import com.moonsworth.lunar.forge.lib.MixinExtra13;
import com.moonsworth.lunar.forge.lib.guava.collect.Iterables;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import lombok.Generated;

public class MixinMisc {
   private final Map<String, MixinMisc2> field1 = new HashMap<>();
   private final List<MixinMisc2> field2 = new ArrayList<>();

   public Iterable<MixinMisc2> method1() {
      return Iterables.concat(this.field1.values(), this.field2);
   }

   public MixinMisc method2(Path var1) {
      try (ZipInputStream var2 = new ZipInputStream(new BufferedInputStream(Files.newInputStream(var1)))) {
         ZipEntry var3;
         while ((var3 = var2.getNextEntry()) != null) {
            if (var3.getName().endsWith("binpatches.pack.lzma")) {
               this.method3(var2);
               break;
            }
         }

         return this;
      } catch (Throwable var7) {
         throw var7;
      }
   }

   public void method3(InputStream var1) {
      try (
         InputStreamLoader3_2 var2 = new InputStreamLoader3_2(new BufferedInputStream(var1));
         InputStream var3 = this.method5(var2);
         ZipInputStream var4 = new ZipInputStream(var3);
      ) {
         ZipEntry var5;
         while ((var5 = var4.getNextEntry()) != null) {
            if (!var5.isDirectory() && var5.getName().endsWith(".binpatch") && var5.getName().startsWith("binpatch/client/")) {
               this.method4(var4);
            }
         }
      } catch (Throwable var13) {
         throw var13;
      }
   }

   private void method4(InputStream var1) {
      MixinMisc2 var2 = new MixinMisc2().method1(var1);
      if (var2.field2) {
         MixinMisc2 var3 = this.field1.put(var2.sourceClassName, var2);
         if (var3 != null) {
            throw new IllegalStateException("Found two binpatches for " + var2.sourceClassName);
         }
      } else {
         this.field2.add(var2);
      }
   }

   private InputStream method5(InputStream var1) {
      try {
         ByteArrayOutputStream var2 = new ByteArrayOutputStream();
         PrintStream var3 = System.out;
         System.setOut(new PrintStream(OutputStream.nullOutputStream()));
         new MixinExtra13().method2(new MixinMisc.Data3(var1), new JarOutputStream(var2));
         System.setOut(var3);
         return new ByteArrayInputStream(var2.toByteArray());
      } catch (Throwable var4) {
         throw var4;
      }
   }

   @Generated
   public Map<String, MixinMisc2> method6() {
      return this.field1;
   }

   @Generated
   public List<MixinMisc2> method7() {
      return this.field2;
   }

   private static class Data3 extends FilterInputStream {
      public Data3(InputStream var1) {
         super(var1);
      }

      @Override
      public boolean markSupported() {
         return false;
      }

      @Override
      public int available() {
         return Integer.MAX_VALUE;
      }
   }
}
