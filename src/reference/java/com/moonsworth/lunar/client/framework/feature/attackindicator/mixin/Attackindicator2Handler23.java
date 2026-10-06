package com.moonsworth.lunar.client.framework.feature.attackindicator.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class Attackindicator2Handler23 extends Attackindicator2Handler2 {
   @Override
   public float method2(@NotNull Bridge5Extension_5 var1) {
      return var1.bridge$isUsingItem() ? 1.0F - var1.bridge$getItemProgress() : 1.0F;
   }

   @Override
   public boolean method3(@NotNull Bridge5Extension_5 var1) {
      return this.method1(var1);
   }

   @Nullable
   @Override
   public ItemStackBridge method6(@NotNull Bridge5Extension_5 var1) {
      return this.method6();
   }

   @Override
   protected boolean method3(@NotNull ItemStackBridge var1) {
      return var1.bridge$getItemUseAction().isConsumable();
   }
}
