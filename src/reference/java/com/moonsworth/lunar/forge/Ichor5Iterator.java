package com.moonsworth.lunar.forge;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.forge.lib.guava.collect.Multimaps;
import com.moonsworth.lunar.forge.lib.guava.collect.SetMultimap;
import com.moonsworth.lunar.ichor.IchorTransformer;
import com.moonsworth.lunar.ichor.Ichor4;
import com.moonsworth.lunar.ichor.Ichor5;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.MixinInternal2;
import com.moonsworth.lunar.ichor.MixinMisc4;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import com.moonsworth.lunar.ichor.util.FatalIchorError7;
import com.moonsworth.lunar.loader.Ichor4Type;
import com.moonsworth.lunar.loader.mixin.MixinInternal3Handler;
import java.io.BufferedInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import lombok.Generated;
import org.cadixdev.bombe.provider.ClassProvider;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.io.MappingFormats;

public class Ichor5Iterator implements Ichor5, ClassProvider {
   public static final String field1 = "deobfuscation_data-%s.lzma";
   private final Map<String, String> field2 = new ConcurrentHashMap<>();
   public final MappingSet field3 = MappingSet.create();
   private final SetMultimap<String, String> field4 = Multimaps.synchronizedSetMultimap(
      Multimaps.newSetMultimap(new ConcurrentHashMap<>(), ConcurrentHashMap::newKeySet)
   );
   private final SetMultimap<String, String> field5 = Multimaps.synchronizedSetMultimap(
      Multimaps.newSetMultimap(new ConcurrentHashMap<>(), ConcurrentHashMap::newKeySet)
   );
   private final Map<String, String> field6 = new ConcurrentHashMap<>();
   private final Map<String, byte[]> field7 = new ConcurrentHashMap<>();

   @Override
   public void loadIchor(IchorTransformer var1) {
      Config var2 = Config.method36(var1.method20().method34().method6());
      if (!var2.method19()) {
         IchorPipeline var3 = var1.method20();
         if (var3.method33().contains(Ichor4Type.FORGE_PATCH)) {
            MixinMisc var4 = this.method1(var3);
            this.method4(var3.method11("forge").orElseThrow(), "net/minecraftforge/");
            var1.method1(new Ichor2Handler_2(var4));
            var1.method1(new Ichor2Handler2_2(this.field4, this.field5));
            var1.method1(new Ichor2Iterator3(this.field2, this.field4));
            var1.method22().add(new Ichor5Iterator.Data2(var2));
            var1.method1(new Ichor2Impl(this));
            var1.method1(new Ichor2Iterator2(this));
            var1.method1(new Ichor2Iterator());
         }
      }
   }

   private MixinMisc method1(IchorPipeline var1) {
      try {
         Path var2 = var1.method11("forge").orElseThrow();
         MixinMisc var3 = new MixinMisc().method2(var2);
         int var4 = var3.method6().size();
         int var5 = var3.method7().size();
         int var6 = var4 + var5;
         Ichor6Impl.field2.info("Parsed {} Forge patches ({} modifications, {} additions)", var6, var4, var5);
         MixinMisc3 var7 = new MixinMisc3(var3);
         var7.method3().forEach((var1x, var2x) -> {
            this.field7.put(var1x.field1, var2x);
            this.field4.putAll(var1x.sourceClassName, FatalIchorError6.method8(var2x));
         });
         var7.method1().forEach((var1x, var2x) -> this.field2.put(var2x, var1x));
         var7.method2().forEach(this.field3::createTopLevelClassMapping);
         if (Ichor6Impl.field3) {
            String var8 = Config.method36(var1.method34().method6()).getId();
            MappingSet var9 = MappingSet.create();
            this.field2.forEach(var9::createTopLevelClassMapping);
            MappingFormats.CSRG.write(var9, Ichor6Impl.field5.resolve(var8 + "_reverseMappings.csrg"));
         }

         return var3;
      } catch (Throwable var10) {
         throw var10;
      }
   }

   public byte[] get(String var1) {
      return this.field7.get(var1);
   }

   public void method2(String var1, String var2) {
      Ichor6Impl.field2.info("Registering mod: " + var1 + " -> " + var2);
      this.field6.put(var1, var2);
   }

   public void method3(IchorPipeline var1, String var2, String var3) {
      Path var4 = var1.method11(var2).orElseThrow(() -> new IllegalStateException("Could not find external file " + var2));
      this.method4(var4, var3);
   }

   public void method4(Path var1, String var2) {
      try (ZipInputStream var3 = new ZipInputStream(new BufferedInputStream(Files.newInputStream(var1)))) {
         ZipEntry var4;
         while ((var4 = var3.getNextEntry()) != null) {
            String var5 = var4.getName();
            if (var5.startsWith(var2) && var5.endsWith(".class")) {
               String var6 = var5.substring(0, var5.indexOf(46));
               byte[] var7 = FatalIchorError7.method1(var3, false);
               this.field4.putAll(var6, FatalIchorError6.method8(var7));
            }
         }
      } catch (Throwable var10) {
         throw var10;
      }
   }

   public void method5(URLClassLoader var1, MappingSet var2) {
      this.field5.asMap().entrySet().parallelStream().forEach(var2x -> {
         String var3 = var2x.getKey();
         Set var4 = FatalIchorError6.method12(var3, var1);
         if (!var4.isEmpty()) {
            for (String var6 : var4) {
               MixinMisc4.method6(var6, var3, var2, var2, var1xx -> FatalIchorError6.method12(var1xx, var1));
            }
         }
      });
   }

   @Generated
   public Map<String, String> method6() {
      return this.field6;
   }

   public static class Data2 extends MixinInternal3Handler {
      public Data2(Config var1) {
         super(var1);
      }

      @Override
      public void method1(Ichor4 var1, MixinInternal2 var2, URLClassLoader var3) {
         if (var1 == Ichor4Type.MIXIN) {
            var2.registerMixins(
               List.of(
                  "mixins.ichor.forge." + this.IORIRHROIIIICHHIICIHHRHRORHRCI.getId() + ".json",
                  "mixins." + this.IORIRHROIIIICHHIICIHHRHRORHRCI.method47() + "_forge_" + this.IORIRHROIIIICHHIICIHHRHRORHRCI.getId() + ".json"
               )
            );
         }
      }
   }
}
