package com.moonsworth.lunar.client.framework.feature.attackindicator;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class ConsumableAttackIndicator extends AbstractItemAttackIndicator {
   ConsumableAttackIndicator() {
   }

   @Override
   public float method2(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return bridge5extension_51.bridge$isUsingItem() ? 1.0F - bridge5extension_51.bridge$getItemProgress() : 1.0F;
   }

   @Override
   public boolean method3(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return this.method5(bridge5extension_51);
   }

   @Nullable
   @Override
   public ItemStackBridge method6(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return this.method6();
   }

   @Override
   protected boolean method3(@NotNull ItemStackBridge bridgeextension_41) {
      return bridgeextension_41.bridge$getItemUseAction().isConsumable();
   }
}
