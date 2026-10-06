package com.moonsworth.lunar.client.framework.feature.attackindicator.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class Attackindicator2Handler22 extends Attackindicator2Handler2 {
   @Override
   protected boolean method3(@NotNull ItemStackBridge var1) {
      return var1.bridge$getItem().bridge$isItemBow() || var1.bridge$getItem().bridge$isCrossbow();
   }

   @Override
   public boolean method3(@NotNull Bridge5Extension_5 var1) {
      return this.method5(var1) || this.method6().bridge$isCrossbowFullyCharged();
   }

   @Override
   public float method2(@NotNull Bridge5Extension_5 var1) {
      return this.method6().bridge$isCrossbowFullyCharged() ? 1.0F : var1.bridge$getItemProgress();
   }

   @Override
   public boolean method4(@NotNull Bridge5Extension_5 var1) {
      return this.method2(var1) == 1.0F;
   }

   @Nullable
   @Override
   public ItemStackBridge method6(@NotNull Bridge5Extension_5 var1) {
      return this.method6();
   }
}
