package com.moonsworth.lunar.client.framework.feature.attackindicator.mixin;

import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class Attackindicator2Handler3 implements Attackindicator2 {
   @Override
   public boolean method1(@NotNull Bridge5Extension_5 var1) {
      return ThreadModuleDump63.method3().bridge$getPlayerController().bridge$isHittingBlock();
   }

   @Override
   public float method2(@NotNull Bridge5Extension_5 var1) {
      return ThreadModuleDump63.method3().bridge$getPlayerController().bridge$getBlockDestroyProgress();
   }

   @Override
   public int method5() {
      return 99;
   }

   @Nullable
   @Override
   public ItemStackBridge method6(@NotNull Bridge5Extension_5 var1) {
      Horsestats20Extension2 var2 = ThreadModuleDump63.method3().bridge$getPlayerController().bridge$getBlockBeingDestroyed();
      Itemcounter6Extension var3 = ThreadModuleDump63.method3().bridge$getWorld();
      if (var3 == null) {
         return null;
      }

      Bridge3_23 var4 = var3.method4(var2);
      return var4 == null ? null : var4.bridge$getStack(var2);
   }
}
