package com.moonsworth.lunar.ichor;

import java.util.Optional;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.model.ClassMapping;

public class MixinMisc2Handler implements MixinMisc2 {
   private final MappingSet field1;

   public MixinMisc2Handler(MappingSet var1) {
      this.field1 = var1;
   }

   @Override
   public String remap(String var1) {
      if (var1 != null && !var1.isEmpty()) {
         Optional var2 = this.field1.computeClassMapping(var1);
         if (var2.isPresent()) {
            var1 = ((ClassMapping)var2.get()).getFullDeobfuscatedName();
         }

         return var1;
      } else {
         return var1;
      }
   }

   @Override
   public String unmap(String var1) {
      return var1 != null && !var1.isEmpty() ? MixinMisc4.method2(this.field1, var1) : var1;
   }
}
