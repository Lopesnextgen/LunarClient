package com.moonsworth.lunar.forge;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.files.Files;
import com.moonsworth.lunar.forge.lib.guava.collect.SetMultimap;
import com.moonsworth.lunar.ichor.Annotation8;
import com.moonsworth.lunar.ichor.Ichor4;
import com.moonsworth.lunar.ichor.MixinMisc4;
import com.moonsworth.lunar.ichor.URLClassLoader;
import com.moonsworth.lunar.ichor.util.FatalIchorError;
import com.moonsworth.lunar.loader.Ichor4Type;
import java.io.IOException;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.io.MappingFormats;
import org.cadixdev.lorenz.model.Mapping;

public class Ichor2Handler2_2 extends com.moonsworth.lunar.ichor.util.Ichor2Handler2 {
   private final SetMultimap<String, String> field3;
   private final SetMultimap<String, String> field4;

   public Ichor2Handler2_2(SetMultimap<String, String> var1, SetMultimap<String, String> var2) {
      super("net.minecraftforge.*|[^\\/]+");
      this.field3 = var1;
      this.field4 = var2;
   }

   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.POST_FORGE_PATCH};
   }

   @Annotation8(method5 = true)
   public MappingSet method2(URLClassLoader var1) {
      String var2 = Config.method36(var1.method1().method34().method6()).getId();
      MappingSet var3 = var1.method1().method10(Files.Data2.field4).orElseThrow(() -> new FatalIchorError("Can't find mappings"));
      MappingSet var4 = MappingSet.create();
      this.field3.forEach((var3x, var4x) -> {
         String var5 = var3.getClassMapping(var4x).<String>map(Mapping::getDeobfuscatedName).orElse(var4x);
         this.field4.put(var3x, var5);
         MixinMisc4.method6(var4x, var3x, var3, var4, this.field3::get);
      });
      if (Ichor6Impl.field3) {
         try {
            MappingFormats.TSRG.write(var4, Ichor6Impl.field5.resolve(var2 + "fixForge.tsrg"));
         } catch (IOException var6) {
            var6.printStackTrace();
         }
      }

      return var4;
   }
}
