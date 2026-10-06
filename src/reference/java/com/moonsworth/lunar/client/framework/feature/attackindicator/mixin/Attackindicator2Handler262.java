package com.moonsworth.lunar.client.framework.feature.attackindicator.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.KineticWeaponTiming;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class Attackindicator2Handler262 extends Attackindicator2Handler26 {
   public Attackindicator2Handler262() {
      super(true, Bridge6_4::bridge$isSpear);
   }

   @Override
   public float method2(@NotNull Bridge5Extension_5 var1) {
      KineticWeaponTiming var2 = this.method9(var1);
      if (var2 == null) {
         return super.method2(var1);
      }

      int var3 = var1.bridge$getTicksUsingItem();
      if (var3 < var2.method1()) {
         return (float)var3 / var2.method1();
      }

      int var4 = var2.method4() - var2.method1();
      return var4 <= 0 ? 0.0F : Math.max(0.0F, 1.0F - (float)(var3 - var2.method1()) / var4);
   }

   @Override
   public boolean method4(@NotNull Bridge5Extension_5 var1) {
      KineticWeaponTiming var2 = this.method9(var1);
      if (var2 == null) {
         return super.method4(var1);
      }

      int var3 = var1.bridge$getTicksUsingItem();
      return var3 >= var2.method1() && var3 <= var2.method2() && Attackindicator2Handler5.method10(var1);
   }

   @Nullable
   private KineticWeaponTiming method9(@NotNull Bridge5Extension_5 var1) {
      ItemStackBridge var2 = this.method6();
      return var2 != null && this.method4(var1) ? var2.bridge$getKineticWeaponPhases() : null;
   }
}
