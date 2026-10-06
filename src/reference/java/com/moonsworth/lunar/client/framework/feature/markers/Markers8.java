package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class Markers8 implements SExtension_2<ItemStackBridge, ItemStackBridge> {
   @NotNull
   @Override
   public String method4() {
      return "nether_brick";
   }

   public boolean method2(@NotNull ItemStackBridge var1) {
      String var2 = var1.bridge$getItemRegistryName();
      return "minecraft:nether_bricks".equals(var2)
         || "minecraft:red_nether_bricks".equals(var2)
         || "minecraft:nether_brick".equals(var2)
         || "minecraft:netherbrick".equals(var2)
         || "minecraft:red_nether_brick".equals(var2);
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge var1) {
      String var2 = var1.bridge$getItemRegistryName();
      if (!"minecraft:red_nether_bricks".equals(var2) && !"minecraft:red_nether_brick".equals(var2)) {
         if (!"minecraft:nether_bricks".equals(var2) && (ThreadModuleDump63.MC_VERSION > 5 || !"minecraft:nether_brick".equals(var2))) {
            return !"minecraft:netherbrick".equals(var2) && (ThreadModuleDump63.MC_VERSION <= 5 || !"minecraft:nether_brick".equals(var2))
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
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 var1) {
      String var2 = switch (var1.method1("block")) {
         case "red" -> ThreadModuleDump63.MC_VERSION > 5 ? "minecraft:red_nether_bricks" : "minecraft:red_nether_brick";
         case "block" -> ThreadModuleDump63.MC_VERSION > 5 ? "minecraft:nether_bricks" : "minecraft:nether_brick";
         case "item" -> ThreadModuleDump63.MC_VERSION > 5 ? "minecraft:nether_brick" : "minecraft:netherbrick";
         default -> "";
      };
      return SIterator_2.method8(var2);
   }
}
