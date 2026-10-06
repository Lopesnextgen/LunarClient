package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class Markers12Impl4 implements SExtension_2<ItemStackBridge, ItemStackBridge> {
   @NotNull
   @Override
   public String method4() {
      return "signs";
   }

   public boolean method2(@NotNull ItemStackBridge var1) {
      return var1.bridge$getItem().bridge$isItemSign();
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge var1) {
      return ThreadModuleDump63.MC_VERSION > 5 ? SIterator_2.method5(var1).map(var0 -> {
         if (var0.contains("_hanging_sign")) {
            var0 = var0.replace("_hanging_sign", "");
            return Markers2_3.method3(var0 + ":hanging");
         } else {
            var0 = var0.replace("_standing_sign", "");
            var0 = var0.replace("_wall_sign", "");
            var0 = var0.replace("_sign", "");
            return Markers2_3.method3(var0);
         }
      }).orElse(Markers2_3.method2()) : Markers2_3.method2();
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 var1) {
      String var2 = var1.method1("oak");
      boolean var3 = false;
      if (var2.endsWith(":hanging")) {
         var2 = var2.replace(":hanging", "");
         var3 = true;
      }

      if (ThreadModuleDump63.MC_VERSION <= 5) {
         return SIterator_2.method8("minecraft:sign");
      }

      String var4 = var2;
      if (var3 && ThreadModuleDump63.MC_VERSION >= 15) {
         var4 = var4 + "_hanging_sign";
      } else {
         var4 = var4 + "_sign";
      }

      Optional var5 = SIterator_2.method8("minecraft:" + var4);
      return var5.isPresent() ? var5 : SIterator_2.method8("minecraft:oak_sign");
   }
}
