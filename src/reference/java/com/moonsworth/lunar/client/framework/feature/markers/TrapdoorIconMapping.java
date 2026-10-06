package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class TrapdoorIconMapping implements IconMapping<ItemStackBridge, ItemStackBridge> {
   public TrapdoorIconMapping() {
   }

   @NotNull
   @Override
   public String method4() {
      return "trapdoor";
   }

   public boolean method2(@NotNull ItemStackBridge bridgeextension_41) {
      String text2 = bridgeextension_41.bridge$getItemRegistryName();
      return text2.startsWith("minecraft:") && text2.endsWith("_trapdoor")
         || text2.equals("minecraft:trapdoor")
         || text2.equals("minecraft:wooden_trapdoor")
         || text2.equals("minecraft:iron_trapdoor");
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge bridgeextension_41) {
      String text2 = bridgeextension_41.bridge$getItemRegistryName();
      return text2.contains("_trapdoor") ? Markers2_3.method3(text2.split(":")[1].replace("_trapdoor", "")) : Markers2_3.method2();
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 markers2_31) {
      if (Ref.MC_VERSION > 5) {
         Optional optional3 = ItemIconRegistry.method8("minecraft:" + markers2_31.method1("oak") + "_trapdoor");
         return optional3.isPresent() ? optional3 : ItemIconRegistry.method8("minecraft:oak_trapdoor");
      } else {
         String text2 = markers2_31.value();
         return "iron".equals(text2) ? ItemIconRegistry.method8("minecraft:iron_trapdoor") : ItemIconRegistry.method8("minecraft:trapdoor");
      }
   }
}
