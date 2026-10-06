package com.moonsworth.lunar.client.framework.feature.attackindicator;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class SleepAttackIndicator implements AttackIndicatorProvider {
   private static final ItemStackBridge field4 = Bridge.method8().method38(Bridge.method28().method33());

   SleepAttackIndicator() {
   }

   @Override
   public boolean method1(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return bridge5extension_51.bridge$isSleeping();
   }

   @Override
   public float method2(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return bridge5extension_51.bridge$getSleepProgress() / 100.0F;
   }

   @Nullable
   @Override
   public ItemStackBridge method6(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return field4;
   }

   @Override
   public int method5() {
      return 100;
   }
}
