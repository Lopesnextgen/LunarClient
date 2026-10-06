package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class GrassIconMapping implements IconMapping<ItemStackBridge, ItemStackBridge> {
   public GrassIconMapping() {
   }

   @NotNull
   @Override
   public String method4() {
      return "grass";
   }

   public boolean method2(@NotNull ItemStackBridge bridgeextension_41) {
      if (Ref.MC_VERSION <= 5) {
         return false;
      }

      String text2 = bridgeextension_41.bridge$getItemRegistryName();
      return "minecraft:short_grass".equals(text2)
         || "minecraft:grass_path".equals(text2)
         || "minecraft:grass".equals(text2)
         || "minecraft:dirt_path".equals(text2);
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge bridgeextension_41) {
      return bridgeextension_41.bridge$getItemRegistryName().endsWith("_path") ? Markers2_3.method3("path") : Markers2_3.method2();
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 markers2_31) {
      String text2 = markers2_31.method1(null);
      if ("path".equals(text2)) {
         text2 = Ref.MC_VERSION > 7 ? "minecraft:dirt_path" : "minecraft:grass_path";
      } else {
         text2 = Ref.MC_VERSION >= 20 ? "minecraft:short_grass" : "minecraft:grass";
      }

      return ItemIconRegistry.method8(text2);
   }
}
