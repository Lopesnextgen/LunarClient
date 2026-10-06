package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public abstract class Markers12Base implements SExtension_2<ItemStackBridge, ItemStackBridge> {
   protected abstract String method1(int var1, ItemStackBridge var2);

   protected abstract int method2(String var1);

   protected abstract String method3();

   public boolean method4(@NotNull ItemStackBridge var1) {
      String var2 = var1.bridge$getItemRegistryName();
      return var2.startsWith("minecraft:") && var2.endsWith("_" + this.method3())
         || var2.equals("minecraft:" + this.method3());
   }

   @NotNull
   public Markers2_3 method5(@NotNull ItemStackBridge var1) {
      if (ThreadModuleDump63.MC_VERSION <= 5) {
         int var2 = var1.bridge$getItemDamage();
         return Markers2_3.method3(this.method1(var2, var1));
      } else {
         return SIterator_2.method5(var1)
            .map(var1x -> Markers2_3.method3(var1x.replace("_" + this.method3(), "")))
            .orElse(Markers2_3.method2());
      }
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 var1) {
      if (ThreadModuleDump63.MC_VERSION > 5) {
         Optional var4 = SIterator_2.method8("minecraft:" + var1.method1(this.method3()) + "_" + this.method3());
         return var4.isPresent() ? var4 : SIterator_2.method8("minecraft:" + this.method3() + "_" + this.method3());
      } else {
         int var2 = this.method2(var1.value());
         ItemStackBridge var3 = SIterator_2.method9("minecraft:" + this.method3());
         var3.bridge$setItemDamage(var2);
         return SIterator_2.method7(var3);
      }
   }
}
