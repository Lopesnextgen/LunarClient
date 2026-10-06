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
import java.util.Map;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.io.MappingFormats;
import org.cadixdev.lorenz.model.ClassMapping;
import org.cadixdev.lorenz.model.InnerClassMapping;

public class Ichor2Iterator3 extends com.moonsworth.lunar.ichor.util.Ichor2Handler {
   private final Map<String, String> field2;
   private final SetMultimap<String, String> field3;

   public Ichor2Iterator3(Map<String, String> var1, SetMultimap<String, String> var2) {
      super("net/minecraft/", "net/minecraftforge/");
      this.field2 = var1;
      this.field3 = var2;
   }

   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.POST_FORGE_PATCH};
   }

   @Annotation8(method5 = true)
   public MappingSet method2(URLClassLoader var1) {
      String var2 = Config.method36(var1.method1().method34().method6()).getId();
      MappingSet var3 = var1.method1().method10(Files.Data2.field8).map(MixinMisc4::method1).orElseThrow(() -> new FatalIchorError("Can't find mappings"));
      this.field3
         .asMap()
         .entrySet()
         .parallelStream()
         .forEach(var2x -> var2x.getValue().forEach(var3x -> MixinMisc4.method6(var3x, (String)var2x.getKey(), var3, var3, this.field3::get)));
      if (Ichor6Impl.field3) {
         try {
            MappingFormats.TSRG.write(var3, Ichor6Impl.field5.resolve(var2 + "_revertToObf.tsrg"));
         } catch (IOException var5) {
            var5.printStackTrace();
         }
      }

      return var3;
   }

   @Deprecated
   private void method3(ClassMapping<?, ?> var1) {
      String var2 = this.field2.get(var1.getFullObfuscatedName());
      if (var2 != null && !var1.getFullDeobfuscatedName().equals(var2)) {
         Ichor6Impl.field2.info("Fixing Forge reobf: " + var1.getFullDeobfuscatedName() + " -> " + var2);
         var1.setDeobfuscatedName(var2);
      }

      for (InnerClassMapping var4 : var1.getInnerClassMappings()) {
         this.method3(var4);
      }
   }
}
