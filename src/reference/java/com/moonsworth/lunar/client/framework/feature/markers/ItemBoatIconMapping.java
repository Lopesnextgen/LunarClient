package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class ItemBoatIconMapping implements IconMapping<ItemStackBridge, ItemStackBridge> {
   public ItemBoatIconMapping() {
   }

   @NotNull
   @Override
   public String method4() {
      return "boat";
   }

   public boolean method2(@NotNull ItemStackBridge bridgeextension_41) {
      String text2 = bridgeextension_41.bridge$getItemRegistryName();
      return text2.startsWith("minecraft:") && text2.endsWith("_boat") || text2.equals("minecraft:boat");
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge bridgeextension_41) {
      return Ref.MC_VERSION < 5 ? Markers2_3.method2() : ItemIconRegistry.method5(bridgeextension_41).map(Markers2_3::method3).orElse(Markers2_3.method2());
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 markers2_31) {
      if (Ref.MC_VERSION >= 5) {
         String text2 = markers2_31.method1("oak_boat");
         Optional optional3 = ItemIconRegistry.method8("minecraft:" + text2);
         return optional3.isPresent() ? optional3 : ItemIconRegistry.method8("minecraft:oak_boat");
      } else {
         return ItemIconRegistry.method8("minecraft:boat");
      }
   }
}
