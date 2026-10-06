package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class Markers12Impl implements SExtension_2<ItemStackBridge, ItemStackBridge> {
   @NotNull
   @Override
   public String method4() {
      return "terracota";
   }

   public boolean method2(@NotNull ItemStackBridge var1) {
      String var2 = var1.bridge$getItemRegistryName();
      return ThreadModuleDump63.MC_VERSION <= 5 && var2.equals("minecraft:stained_hardened_clay")
         || !var2.endsWith("_glazed_terracotta") && var2.endsWith("_terracotta");
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge var1) {
      if (ThreadModuleDump63.MC_VERSION <= 5) {
         String var2 = (String)Markers7.field1.inverse().get(15 - var1.bridge$getItemDamage());
         return Markers2_3.method3(var2);
      } else {
         return SIterator_2.method5(var1).map(var0 -> Markers2_3.method3(var0.replace("_terracotta", ""))).orElse(Markers2_3.method2());
      }
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 var1) {
      if (ThreadModuleDump63.MC_VERSION > 5) {
         String var4 = "minecraft:" + (var1.isEmpty() ? "terracotta" : var1.value() + "_terracotta");
         Optional var3 = SIterator_2.method8(var4);
         return var3.isPresent() ? var3 : SIterator_2.method8("minecraft:terracotta");
      }

      ItemStackBridge var2 = SIterator_2.method9("minecraft:stained_hardened_clay");
      if (!var1.isEmpty()) {
         var2.bridge$setItemDamage(15 - (Integer)Markers7.field1.get(var1.value()));
      }

      return SIterator_2.method7(var2);
   }
}
