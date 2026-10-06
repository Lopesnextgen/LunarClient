package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class Markers3_3 implements SExtension_2<ItemStackBridge, ItemStackBridge> {
   @NotNull
   @Override
   public String method4() {
      return "boat";
   }

   public boolean method2(@NotNull ItemStackBridge var1) {
      String var2 = var1.bridge$getItemRegistryName();
      return var2.startsWith("minecraft:") && var2.endsWith("_boat") || var2.equals("minecraft:boat");
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge var1) {
      return ThreadModuleDump63.MC_VERSION < 5 ? Markers2_3.method2() : SIterator_2.method5(var1).map(Markers2_3::method3).orElse(Markers2_3.method2());
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 var1) {
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         String var2 = var1.method1("oak_boat");
         Optional var3 = SIterator_2.method8("minecraft:" + var2);
         return var3.isPresent() ? var3 : SIterator_2.method8("minecraft:oak_boat");
      } else {
         return SIterator_2.method8("minecraft:boat");
      }
   }
}
