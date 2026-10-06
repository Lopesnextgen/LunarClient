package com.moonsworth.lunar.client.framework.feature.attackindicator;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import java.util.function.Predicate;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class CooldownAttackIndicator extends AbstractItemAttackIndicator {
   private final Predicate<ItemBridge> predicate;

   @Override
   protected boolean method3(@NotNull ItemStackBridge bridgeextension_41) {
      return this.predicate.test(bridgeextension_41.bridge$getItem());
   }

   @Override
   public float method2(@NotNull Bridge5Extension_5 bridge5extension_51) {
      float value2 = bridge5extension_51.bridge$getCooldown(this.method6());
      return value2 == -1.0F ? 1.0F : 1.0F - value2;
   }

   @Override
   public boolean method3(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return bridge5extension_51.bridge$getCooldown(this.method6()) != -1.0F;
   }

   @Nullable
   @Override
   public ItemStackBridge method6(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return this.method6();
   }

   @Generated
   public CooldownAttackIndicator(Predicate<ItemBridge> predicate1) {
      this.predicate = predicate1;
   }
}
