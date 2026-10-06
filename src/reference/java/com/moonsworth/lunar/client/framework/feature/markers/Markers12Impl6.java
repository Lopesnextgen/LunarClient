package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class Markers12Impl6 implements SExtension_2<ItemStackBridge, ItemStackBridge> {
   @NotNull
   @Override
   public String method4() {
      return "trapdoor";
   }

   public boolean method2(@NotNull ItemStackBridge var1) {
      String var2 = var1.bridge$getItemRegistryName();
      return var2.startsWith("minecraft:") && var2.endsWith("_trapdoor")
         || var2.equals("minecraft:trapdoor")
         || var2.equals("minecraft:wooden_trapdoor")
         || var2.equals("minecraft:iron_trapdoor");
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge var1) {
      String var2 = var1.bridge$getItemRegistryName();
      return var2.contains("_trapdoor") ? Markers2_3.method3(var2.split(":")[1].replace("_trapdoor", "")) : Markers2_3.method2();
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 var1) {
      if (ThreadModuleDump63.MC_VERSION > 5) {
         Optional var3 = SIterator_2.method8("minecraft:" + var1.method1("oak") + "_trapdoor");
         return var3.isPresent() ? var3 : SIterator_2.method8("minecraft:oak_trapdoor");
      } else {
         String var2 = var1.value();
         return "iron".equals(var2) ? SIterator_2.method8("minecraft:iron_trapdoor") : SIterator_2.method8("minecraft:trapdoor");
      }
   }
}
