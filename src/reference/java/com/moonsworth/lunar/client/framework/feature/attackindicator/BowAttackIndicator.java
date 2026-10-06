package com.moonsworth.lunar.client.framework.feature.attackindicator;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class BowAttackIndicator extends AbstractItemAttackIndicator {
   BowAttackIndicator() {
   }

   @Override
   protected boolean method3(@NotNull ItemStackBridge bridgeextension_41) {
      return bridgeextension_41.bridge$getItem().bridge$isItemBow() || bridgeextension_41.bridge$getItem().bridge$isCrossbow();
   }

   @Override
   public boolean method3(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return this.method5(bridge5extension_51) || this.method6().bridge$isCrossbowFullyCharged();
   }

   @Override
   public float method2(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return this.method6().bridge$isCrossbowFullyCharged() ? 1.0F : bridge5extension_51.bridge$getItemProgress();
   }

   @Override
   public boolean method4(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return this.method2(bridge5extension_51) == 1.0F;
   }

   @Nullable
   @Override
   public ItemStackBridge method6(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return this.method6();
   }
}
