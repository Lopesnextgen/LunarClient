package com.moonsworth.lunar.client.framework.feature.attackindicator;

import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.framework.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class BlockBreakAttackIndicator implements AttackIndicatorProvider {
   BlockBreakAttackIndicator() {
   }

   @Override
   public boolean method1(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return Ref.method3().bridge$getPlayerController().bridge$isHittingBlock();
   }

   @Override
   public float method2(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return Ref.method3().bridge$getPlayerController().bridge$getBlockDestroyProgress();
   }

   @Override
   public int method5() {
      return 99;
   }

   @Nullable
   @Override
   public ItemStackBridge method6(@NotNull Bridge5Extension_5 bridge5extension_51) {
      Horsestats20Extension2 horsestats20extension22 = Ref.method3().bridge$getPlayerController().bridge$getBlockBeingDestroyed();
      WorldBridgeExtension itemcounter6extension3 = Ref.method3().bridge$getWorld();
      if (itemcounter6extension3 == null) {
         return null;
      }

      Bridge3_23 bridge3_234 = itemcounter6extension3.method4(horsestats20extension22);
      return bridge3_234 == null ? null : bridge3_234.bridge$getStack(horsestats20extension22);
   }
}
