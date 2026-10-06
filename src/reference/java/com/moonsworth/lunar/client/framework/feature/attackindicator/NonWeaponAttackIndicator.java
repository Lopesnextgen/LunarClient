package com.moonsworth.lunar.client.framework.feature.attackindicator;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class NonWeaponAttackIndicator implements AttackIndicatorProvider {
   NonWeaponAttackIndicator() {
   }

   @Override
   public boolean isVanilla() {
      return true;
   }

   @Override
   public boolean method1(@NotNull Bridge5Extension_5 bridge5extension_51) {
      ItemStackBridge bridgeextension_42 = bridge5extension_51.bridge$getHeldItem();
      return bridgeextension_42 == null
         || bridgeextension_42.bridge$isEmpty()
         || !bridgeextension_42.bridge$getItem().bridge$isItemSword()
            && !bridgeextension_42.bridge$getItem().bridge$isAxe()
            && !bridgeextension_42.bridge$getItem().bridge$isItemPickaxe()
            && !bridgeextension_42.bridge$getItem().bridge$isItemShovel()
            && !bridgeextension_42.bridge$getItem().bridge$isTrident()
            && !bridgeextension_42.bridge$getItem().bridge$isSpear();
   }

   @Override
   public float method2(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return bridge5extension_51.bridge$getAttackStrengthScale();
   }

   @Nullable
   @Override
   public ItemStackBridge method6(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return null;
   }
}
