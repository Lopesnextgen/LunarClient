package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class Markers4 implements SExtension_2<ItemStackBridge, ItemStackBridge> {
   private final String field1;

   @NotNull
   @Override
   public String method4() {
      return this.field1;
   }

   public boolean method2(@NotNull ItemStackBridge var1) {
      String var2 = var1.bridge$getItemRegistryName();
      return var2.endsWith("_" + this.field1);
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge var1) {
      return SIterator_2.method5(var1).map(var1x -> {
         var1x = var1x.replace("_" + this.field1, "");
         if ("wooden".equals(var1x)) {
            var1x = "oak";
         }

         return Markers2_3.method3(var1x);
      }).orElse(Markers2_3.method2());
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 var1) {
      String var2 = var1.method1("oak");
      if (ThreadModuleDump63.MC_VERSION > 5) {
         Optional var4 = SIterator_2.method8("minecraft:" + var2 + "_" + this.field1);
         return var4.isPresent() ? var4 : SIterator_2.method8("minecraft:oak_" + this.field1);
      }

      if ("oak".equals(var2)) {
         var2 = "wooden";
      }

      Optional var3 = SIterator_2.method8("minecraft:" + var2 + "_" + this.field1);
      return var3.isPresent() ? var3 : SIterator_2.method8("minecraft:wooden_" + this.field1);
   }

   @Generated
   public Markers4(String var1) {
      this.field1 = var1;
   }
}
