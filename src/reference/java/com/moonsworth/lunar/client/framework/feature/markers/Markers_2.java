package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class Markers_2 implements SExtension_2<ItemStackBridge, ItemStackBridge> {
   @NotNull
   @Override
   public String method4() {
      return "powered";
   }

   public boolean method2(@NotNull ItemStackBridge var1) {
      return ThreadModuleDump63.MC_VERSION > 5
         ? false
         : var1.bridge$getItemRegistryName().endsWith("_comparator") || var1.bridge$getItemRegistryName().endsWith("_repeater");
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge var1) {
      return SIterator_2.method5(var1).map(var0 -> Markers2_3.method3(var0.split("_")[1])).orElse(Markers2_3.method2());
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 var1) {
      return "repeater".equals(var1.value())
         ? SIterator_2.method8("minecraft:repeater")
         : ("comparator".equals(var1.value()) ? SIterator_2.method8("minecraft:comparator") : Optional.empty());
   }
}
