package com.moonsworth.lunar.client.framework.feature.attackindicator.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class Attackindicator2Handler4 implements Attackindicator2 {
   @Override
   public boolean isVanilla() {
      return true;
   }

   @Override
   public boolean method1(@NotNull Bridge5Extension_5 var1) {
      ItemStackBridge var2 = var1.bridge$getHeldItem();
      return var2 == null
         || var2.bridge$isEmpty()
         || !var2.bridge$getItem().bridge$isItemSword()
            && !var2.bridge$getItem().bridge$isAxe()
            && !var2.bridge$getItem().bridge$isItemPickaxe()
            && !var2.bridge$getItem().bridge$isItemShovel()
            && !var2.bridge$getItem().bridge$isTrident()
            && !var2.bridge$getItem().bridge$isSpear();
   }

   @Override
   public float method2(@NotNull Bridge5Extension_5 var1) {
      return var1.bridge$getAttackStrengthScale();
   }

   @Nullable
   @Override
   public ItemStackBridge method6(@NotNull Bridge5Extension_5 var1) {
      return null;
   }
}
