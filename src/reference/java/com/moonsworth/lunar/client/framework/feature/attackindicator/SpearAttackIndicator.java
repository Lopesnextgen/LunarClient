package com.moonsworth.lunar.client.framework.feature.attackindicator;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.KineticWeaponPhases;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class SpearAttackIndicator extends TridentAttackIndicator {
   public SpearAttackIndicator() {
      super(true, ItemBridge::bridge$isSpear);
   }

   @Override
   public float method2(@NotNull Bridge5Extension_5 bridge5extension_51) {
      KineticWeaponPhases mixinhelper5_52 = this.method9(bridge5extension_51);
      if (mixinhelper5_52 == null) {
         return super.method2(bridge5extension_51);
      }

      int number3 = bridge5extension_51.bridge$getTicksUsingItem();
      if (number3 < mixinhelper5_52.method1()) {
         return (float)number3 / mixinhelper5_52.method1();
      }

      int number4 = mixinhelper5_52.method4() - mixinhelper5_52.method1();
      return number4 <= 0 ? 0.0F : Math.max(0.0F, 1.0F - (float)(number3 - mixinhelper5_52.method1()) / number4);
   }

   @Override
   public boolean method4(@NotNull Bridge5Extension_5 bridge5extension_51) {
      KineticWeaponPhases mixinhelper5_52 = this.method9(bridge5extension_51);
      if (mixinhelper5_52 == null) {
         return super.method4(bridge5extension_51);
      }

      int number3 = bridge5extension_51.bridge$getTicksUsingItem();
      return number3 >= mixinhelper5_52.method1() && number3 <= mixinhelper5_52.method2() && MeleeAttackIndicator.method10(bridge5extension_51);
   }

   @Nullable
   private KineticWeaponPhases method9(@NotNull Bridge5Extension_5 bridge5extension_51) {
      ItemStackBridge bridgeextension_42 = this.method6();
      return bridgeextension_42 != null && this.method5(bridge5extension_51) ? bridgeextension_42.bridge$getKineticWeaponPhases() : null;
   }
}
