package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class NetherBrickIconMapping implements IconMapping<ItemStackBridge, ItemStackBridge> {
   public NetherBrickIconMapping() {
   }

   @NotNull
   @Override
   public String method4() {
      return "nether_brick";
   }

   public boolean method2(@NotNull ItemStackBridge bridgeextension_41) {
      String text2 = bridgeextension_41.bridge$getItemRegistryName();
      return "minecraft:nether_bricks".equals(text2)
         || "minecraft:red_nether_bricks".equals(text2)
         || "minecraft:nether_brick".equals(text2)
         || "minecraft:netherbrick".equals(text2)
         || "minecraft:red_nether_brick".equals(text2);
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge bridgeextension_41) {
      String text2 = bridgeextension_41.bridge$getItemRegistryName();
      if (!"minecraft:red_nether_bricks".equals(text2) && !"minecraft:red_nether_brick".equals(text2)) {
         if (!"minecraft:nether_bricks".equals(text2) && (Ref.MC_VERSION > 5 || !"minecraft:nether_brick".equals(text2))) {
            return !"minecraft:netherbrick".equals(text2) && (Ref.MC_VERSION <= 5 || !"minecraft:nether_brick".equals(text2))
               ? Markers2_3.method2()
               : Markers2_3.method3("item");
         } else {
            return Markers2_3.method3("block");
         }
      } else {
         return Markers2_3.method3("red");
      }
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 markers2_31) {
      String text2 = switch (markers2_31.method1("block")) {
         case "red" -> Ref.MC_VERSION > 5 ? "minecraft:red_nether_bricks" : "minecraft:red_nether_brick";
         case "block" -> Ref.MC_VERSION > 5 ? "minecraft:nether_bricks" : "minecraft:nether_brick";
         case "item" -> Ref.MC_VERSION > 5 ? "minecraft:nether_brick" : "minecraft:netherbrick";
         default -> "";
      };
      return ItemIconRegistry.method8(text2);
   }
}
