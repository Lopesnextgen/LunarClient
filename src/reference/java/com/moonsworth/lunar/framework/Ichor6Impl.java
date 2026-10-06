package com.moonsworth.lunar.framework;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.files.Files;
import com.moonsworth.lunar.ichor.Ichor5;
import com.moonsworth.lunar.ichor.IchorModule;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.util.FatalIchorError5;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Ichor6Impl extends IchorModule {
   public static final String field1 = "optifine";
   public static final FatalIchorError5 field2 = FatalIchorError5.method2("OptiFine");
   private Ichor5Handler field3;

   public Ichor6Impl() {
      super("optifine");
   }

   @Override
   public List<Ichor5> method1(IchorPipeline var1) {
      if (!Config.method36(var1.method34().method6()).method18()) {
         return List.of();
      }

      var1.method34().method3().method4(Files.Data2.field7);
      this.field3 = new Ichor5Handler();
      return List.of(this.field3);
   }

   @Override
   public Map<String, String> method2(IchorPipeline var1) {
      Config var2 = Config.method36(var1.method34().method6());
      return !var2.method18() ? Map.of() : Map.of("optifine", "OptiFine_" + var2.getId() + ".jar");
   }

   public static boolean method3(String var0) {
      return var0.startsWith("net/optifine/") || var0.startsWith("net/minecraftforge/");
   }

   @Override
   public void method3(IchorPipeline var1, String var2, BiConsumer<String, Supplier<byte[]>> var3) {
      if (Config.method36(var1.method34().method6()).method18() && var2.equals("optifine")) {
         Path var4 = var1.method11(var2).orElseThrow();

         try (ZipFile var5 = new ZipFile(var4.toFile())) {
            Enumeration var6 = var5.entries();

            while (var6.hasMoreElements()) {
               ZipEntry var7 = (ZipEntry)var6.nextElement();
               String var8 = var7.getName();
               this.field3.method1().getFileFilter().apply(var8).ifPresent(var4x -> var3.accept(var4x, () -> {
                  try {
                     return var5.getInputStream(var7).readAllBytes();
                  } catch (IOException var4xx) {
                     throw new RuntimeException("couldn't read file " + var8, var4xx);
                  }
               }));
            }
         } catch (Exception var11) {
            throw new IllegalStateException("Failed to open entries of " + var4, var11);
         }
      } else {
         super.method3(var1, var2, var3);
      }
   }

   @Override
   public IchorModule.Type method5() {
      return IchorModule.Type.INIT;
   }
}
