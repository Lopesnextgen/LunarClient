package com.moonsworth.lunar.client.framework.feature.attackindicator.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class Attackindicator2Handler implements Attackindicator2 {
   private static final ItemStackBridge field4 = Bridge.method8().method38(Bridge.method28().method33());

   @Override
   public boolean method1(@NotNull Bridge5Extension_5 var1) {
      return var1.bridge$isSleeping();
   }

   @Override
   public float method2(@NotNull Bridge5Extension_5 var1) {
      return var1.bridge$getSleepProgress() / 100.0F;
   }

   @Nullable
   @Override
   public ItemStackBridge method6(@NotNull Bridge5Extension_5 var1) {
      return field4;
   }

   @Override
   public int method5() {
      return 100;
   }
}
