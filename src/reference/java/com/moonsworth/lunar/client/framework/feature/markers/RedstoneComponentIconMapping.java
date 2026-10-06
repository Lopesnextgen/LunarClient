package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class RedstoneComponentIconMapping implements IconMapping<ItemStackBridge, ItemStackBridge> {
   public RedstoneComponentIconMapping() {
   }

   @NotNull
   @Override
   public String method4() {
      return "powered";
   }

   public boolean method2(@NotNull ItemStackBridge bridgeextension_41) {
      return Ref.MC_VERSION > 5
         ? false
         : bridgeextension_41.bridge$getItemRegistryName().endsWith("_comparator") || bridgeextension_41.bridge$getItemRegistryName().endsWith("_repeater");
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge bridgeextension_41) {
      return ItemIconRegistry.method5(bridgeextension_41).map(arg0 -> Markers2_3.method3(arg0.split("_")[1])).orElse(Markers2_3.method2());
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 markers2_31) {
      return "repeater".equals(markers2_31.value())
         ? ItemIconRegistry.method8("minecraft:repeater")
         : ("comparator".equals(markers2_31.value()) ? ItemIconRegistry.method8("minecraft:comparator") : Optional.empty());
   }
}
