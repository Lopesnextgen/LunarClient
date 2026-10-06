package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.cadixdev.bombe.provider.ClassProvider;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.model.Mapping;

public interface MixinMisc5 {
   Map<String, String> method1();

   Map<String, String> method2();

   default void method3(IchorPipeline var1, String var2, List<String> var3) {
      IchorModule var4 = var1.method15(var2);
      var4.method3(var1, var2, (var2x, var3x) -> {
         for (String var5 : var3) {
            if (var2x.startsWith(var5) && var2x.endsWith(".class")) {
               try {
                  this.method4(var3x.get(), var2x);
                  break;
               } catch (Exception var7) {
                  throw new IllegalStateException("Failed to read inheritance map from file " + var2x, var7);
               }
            }
         }
      });
   }

   default void method4(byte[] var1, String var2) {
      if (var1 != null) {
         String var3 = var2.substring(0, var2.indexOf(46));
         FatalIchorError6.method7(var1).ifPresent(var2x -> this.method1().put(var3, var2x));
      }
   }

   default void method5(MappingSet var1, ClassProvider var2) {
      for (Entry var4 : this.method1().entrySet()) {
         String var5 = (String)var4.getKey();
         String var6 = var1.getClassMapping((String)var4.getValue()).<String>map(Mapping::getFullDeobfuscatedName).orElse((String)var4.getValue());
         this.method2().put(var5, var6);
         Set var7 = FatalIchorError6.method9(var5, var2);
         var7.forEach(var3 -> MixinMisc4.method6(var3, var5, var1, var1, var1xx -> FatalIchorError6.method9(var1xx, var2)));
      }
   }

   default void method6(MappingSet var1) {
      HashMap var2 = new HashMap();
      this.method1().forEach((var2x, var3) -> {
         String var4 = var1.getClassMapping(var2x).<String>map(Mapping::getFullDeobfuscatedName).orElse(var2x);
         String var5 = var1.getClassMapping(var3).<String>map(Mapping::getFullDeobfuscatedName).orElse(var3);
         var2.put(var4, var5);
      });
      this.method1().clear();
      this.method1().putAll(var2);
   }
}
