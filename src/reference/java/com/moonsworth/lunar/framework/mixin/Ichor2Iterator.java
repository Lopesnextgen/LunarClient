package com.moonsworth.lunar.framework.mixin;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.framework.FrameworkType;
import com.moonsworth.lunar.framework.Ichor6Impl;
import com.moonsworth.lunar.ichor.Annotation10;
import com.moonsworth.lunar.ichor.Annotation5;
import com.moonsworth.lunar.ichor.IchorTransformer;
import com.moonsworth.lunar.ichor.IchorInjection;
import com.moonsworth.lunar.ichor.Ichor4;
import com.moonsworth.lunar.ichor.MixinShared;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.ichor.util.FatalIchorError7;
import com.moonsworth.lunar.loader.Ichor4Type;
import com.nothome.delta.GDiffPatcher;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

public final class Ichor2Iterator implements IchorInjection {
   private static final Map<String, List<String>> field1 = Map.of(
      "v1_7", List.of(), "v1_8", List.of("bot"), "v1_12", List.of("cgb"), "v1_16", List.of(), "v1_17", List.of()
   );
   private final FrameworkType field2;
   private final Map<String, byte[]> field3;
   private final Map<String, byte[]> field4;
   private final List<Ichor2Iterator.Data> field5;
   private boolean initialized = false;
   private final File field6;
   private final boolean field7;

   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.OPTIFINE_PATCH};
   }

   public Ichor2Iterator(FrameworkType var1, File var2, boolean var3) {
      this.field2 = var1;
      this.field3 = new ConcurrentHashMap<>();
      this.field4 = new ConcurrentHashMap<>();
      this.field5 = new LinkedList<>();
      this.field6 = var2;
      this.field7 = var3;
   }

   @Override
   public boolean method1(IchorTransformer.Data3 var1) {
      return !var1.className().contains("/") || var1.className().startsWith("com/mojang/blaze3d/") || var1.className().startsWith("net/minecraft/");
   }

   @Annotation5
   public byte[] method3(String var1, byte[] var2, URLClassLoader var3) {
      try {
         if (this.field7) {
            Config var4 = Config.method36(var3.method1().method34().method6());
            List var5 = field1.get(var4.getId());
            if (var5 != null && var5.contains(var1)) {
               Ichor6Impl.field2.info("Skipping OptiFine patch for " + var1);
               return var2;
            }
         }

         this.method5();
         String var15 = var1 + ".class";
         byte[] var16 = this.field3.get(var15);
         if (var16 != null) {
            InputStream var6 = Thread.currentThread().getContextClassLoader().getResourceAsStream(var15);
            if (var6 == null) {
               return var2;
            }

            byte[] var7 = FatalIchorError7.toByteArray(var6);
            ByteArrayInputStream var8 = new ByteArrayInputStream(var16);
            ByteArrayOutputStream var9 = new ByteArrayOutputStream();
            byte[] var10 = var7;

            try {
               GDiffPatcher var11 = new GDiffPatcher();
               var11.patch(var7, var8, var9);
               var10 = var9.toByteArray();
            } catch (EOFException var12) {
               var10 = var9.toByteArray();
            } catch (IOException var13) {
               var13.printStackTrace();
            }

            return var10;
         } else {
            return var2;
         }
      } catch (Throwable var14) {
         throw var14;
      }
   }

   @Annotation10
   public void method4(MixinShared var1) {
      String var2 = var1.getResourcePath();
      byte[] var3 = var1.method3();
      this.method5();

      try {
         for (Ichor2Iterator.Data var5 : this.field5) {
            if (var5.field1.matcher(var1.getResourcePath()).matches()) {
               if (!"*".equals(var5.field2)) {
                  var1.setResourcePath(var5.field2);
               }

               if (var3 == null && !var1.getResourcePath().equals(var2)) {
                  InputStream var6 = Thread.currentThread().getContextClassLoader().getResourceAsStream(var1.getResourcePath());
                  if (var6 != null) {
                     var3 = FatalIchorError7.toByteArray(var6);
                  }
               }
               break;
            }
         }

         byte[] var12 = this.field3.get(var1.getResourcePath());
         if (var3 == null) {
            var3 = this.field4.get(var1.getResourcePath());
         }

         if (var12 != null && var3 != null) {
            ByteArrayInputStream var13 = new ByteArrayInputStream(var12);
            ByteArrayOutputStream var14 = new ByteArrayOutputStream();

            try {
               GDiffPatcher var7 = new GDiffPatcher();
               var7.patch(var3, var13, var14);
               var3 = var14.toByteArray();
            } catch (EOFException var8) {
               var3 = var14.toByteArray();
            } catch (IOException var9) {
               var9.printStackTrace();
            } catch (NullPointerException var10) {
               throw new IllegalStateException("Failed to patch " + var1.getResourcePath(), var10);
            }
         }

         var1.method4(var3);
      } catch (IOException var11) {
         var11.printStackTrace();
      }
   }

   private void method5() {
      if (!this.initialized) {
         this.initialized = true;
         this.method6();
      }
   }

   private void method6() {
      try (JarFile var1 = new JarFile(this.field6)) {
         Enumeration var2 = var1.entries();

         while (var2.hasMoreElements()) {
            JarEntry var3 = (JarEntry)var2.nextElement();
            String var4 = var3.getName();
            if (var4.startsWith("patch/") && var4.endsWith(".xdelta")) {
               JarEntry var19 = var1.getJarEntry(var4);
               InputStream var21 = var1.getInputStream(var19);
               byte[] var23 = FatalIchorError7.toByteArray(var21);
               this.field2.getFileFilter().apply(var4).ifPresent(var2x -> {
                  String var3x = var2x.substring(6, var2x.lastIndexOf(46));
                  this.field3.put(var3x, var23);
               });
            } else if (var4.startsWith("assets/")) {
               JarEntry var18 = var1.getJarEntry(var4);
               InputStream var20 = var1.getInputStream(var18);
               byte[] var22 = FatalIchorError7.toByteArray(var20);
               this.field4.put(var4, var22);
            } else if (var4.equals("patch.cfg")) {
               JarEntry var5 = var1.getJarEntry(var4);
               byte[] var6 = FatalIchorError7.toByteArray(var1.getInputStream(var5));
               String[] var7 = new String(var6).split("\n");

               for (String var11 : var7) {
                  if (!var11.startsWith("#") && var11.contains("=")) {
                     String[] var12 = var11.split("=");
                     assert var12.length == 2 : String.format("Illegal pattern in OptiFine patch.cfg: %s", var11);
                     Pattern var13 = Pattern.compile(var12[0].trim());
                     String var14 = var12[1].trim();
                     this.field5.add(new Ichor2Iterator.Data(var13, var14));
                  }
               }
            }
         }
      } catch (Throwable var17) {
         throw var17;
      }
   }

   private class Data {
      private final Pattern field1;
      private final String field2;

      private Data(Pattern var1, String var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public Pattern method1() {
         return this.field1;
      }

      public String method2() {
         return this.field2;
      }
   }
}
