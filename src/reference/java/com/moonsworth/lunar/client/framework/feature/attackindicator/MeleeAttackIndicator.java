package com.moonsworth.lunar.client.framework.feature.attackindicator;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.function.Predicate;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class MeleeAttackIndicator implements AttackIndicatorProvider {
   private final Predicate<ItemBridge> field4;

   @Override
   public boolean isVanilla() {
      return true;
   }

   @Override
   public boolean method1(@NotNull Bridge5Extension_5 bridge5extension_51) {
      ItemStackBridge bridgeextension_42 = this.method9(bridge5extension_51);
      return bridgeextension_42 != null && !bridgeextension_42.bridge$isEmpty() && this.field4.test(bridgeextension_42.bridge$getItem());
   }

   @Override
   public boolean method3(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return AttackIndicatorProvider.super.method3(bridge5extension_51) || this.method4(bridge5extension_51);
   }

   @Override
   public float method2(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return Math.min(1.0F, bridge5extension_51.bridge$getAttackStrengthScale());
   }

   @Override
   public boolean method4(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return this.method2(bridge5extension_51) >= 1.0F && method10(bridge5extension_51);
   }

   @Nullable
   @Override
   public ItemStackBridge method6(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return this.method9(bridge5extension_51);
   }

   @Override
   public int method5() {
      return 1;
   }

   private ItemStackBridge method9(@NotNull Bridge5Extension_5 bridge5extension_51) {
      ItemStackBridge bridgeextension_42 = bridge5extension_51.bridge$getHeldItem();
      return bridgeextension_42 != null && !bridgeextension_42.bridge$isEmpty() ? bridgeextension_42 : null;
   }

   public static boolean method10(@NotNull Bridge5Extension_5 bridge5extension_50) {
      return Ref.method3().bridge$getPointedEntity().orElse(null) instanceof EntityLivingBridge bridgeextension2_51
         && bridge5extension_50.bridge$getCurrentItemAttackStrengthDelay() > 5.0F
         && bridgeextension2_51.bridge$isAlive();
   }

   @Generated
   public MeleeAttackIndicator(Predicate<ItemBridge> predicate1) {
      this.field4 = predicate1;
   }
}
