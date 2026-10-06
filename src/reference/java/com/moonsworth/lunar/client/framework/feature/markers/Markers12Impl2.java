package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class Markers12Impl2 implements SExtension_2<ItemStackBridge, ItemStackBridge> {
   @NotNull
   @Override
   public String method4() {
      return "scute";
   }

   public boolean method2(@NotNull ItemStackBridge var1) {
      String var2 = var1.bridge$getItemRegistryName();
      return var2.startsWith("minecraft:") && var2.endsWith("_scute") || var2.equals("minecraft:scute");
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge var1) {
      if (ThreadModuleDump63.MC_VERSION >= 22) {
         String var2 = var1.bridge$getItemRegistryName();
         var2 = var2.split(":")[1];
         return Markers2_3.method3(var2);
      } else {
         return Markers2_3.method2();
      }
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 var1) {
      String var2 = "minecraft:scute";
      if (ThreadModuleDump63.MC_VERSION >= 22) {
         var2 = "minecraft:" + var1.method1("turtle_scute");
      }

      return SIterator_2.method8(var2);
   }
}
