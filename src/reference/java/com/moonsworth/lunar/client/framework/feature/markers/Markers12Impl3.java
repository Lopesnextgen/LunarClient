package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class Markers12Impl3 implements SExtension_2<ItemStackBridge, ItemStackBridge> {
   @NotNull
   @Override
   public String method4() {
      return "grass";
   }

   public boolean method2(@NotNull ItemStackBridge var1) {
      if (ThreadModuleDump63.MC_VERSION <= 5) {
         return false;
      }

      String var2 = var1.bridge$getItemRegistryName();
      return "minecraft:short_grass".equals(var2)
         || "minecraft:grass_path".equals(var2)
         || "minecraft:grass".equals(var2)
         || "minecraft:dirt_path".equals(var2);
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge var1) {
      return var1.bridge$getItemRegistryName().endsWith("_path") ? Markers2_3.method3("path") : Markers2_3.method2();
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 var1) {
      String var2 = var1.method1(null);
      if ("path".equals(var2)) {
         var2 = ThreadModuleDump63.MC_VERSION > 7 ? "minecraft:dirt_path" : "minecraft:grass_path";
      } else {
         var2 = ThreadModuleDump63.MC_VERSION >= 20 ? "minecraft:short_grass" : "minecraft:grass";
      }

      return SIterator_2.method8(var2);
   }
}
