package com.moonsworth.lunar.client.framework.feature.attackindicator.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_4;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.EquipmentSlotBridge;
import java.util.function.Predicate;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class Attackindicator2Handler26 extends Attackindicator2Handler2 {
   private final boolean field6;
   private final Predicate<Bridge6_4> field7;
   private boolean field8;
   private boolean field9;
   private ItemStackBridge field10 = null;

   @Override
   public boolean isVanilla() {
      return this.field6;
   }

   @Override
   protected boolean method3(@NotNull ItemStackBridge var1) {
      return this.field7.test(var1.bridge$getItem());
   }

   @Override
   public float method2(@NotNull Bridge5Extension_5 var1) {
      if (this.field8) {
         return var1.bridge$getItemProgress();
      } else {
         return var1.bridge$getEquipmentInSlot(EquipmentSlotBridge.MAINHAND) == this.method6()
            ? Math.min(1.0F, var1.bridge$getAttackStrengthScale())
            : 0.0F;
      }
   }

   @Override
   public boolean method3(@NotNull Bridge5Extension_5 var1) {
      return super.method1(var1) || this.field8;
   }

   @Override
   public boolean method4(@NotNull Bridge5Extension_5 var1) {
      return this.method2(var1) >= 1.0F && Attackindicator2Handler5.method10(var1);
   }

   @Nullable
   @Override
   public ItemStackBridge method6(@NotNull Bridge5Extension_5 var1) {
      return this.method6();
   }

   @Override
   public void method8(@Nullable Bridge5Extension_5 var1) {
      super.method8(var1);
      if (var1 == null) {
         this.field10 = null;
      } else {
         boolean var2 = this.method5(var1);
         this.field9 = this.field8 != var2 || this.method6() != this.field10;
         this.field8 = var2;
         this.field10 = this.method6();
      }
   }

   @Override
   public void method7(@NotNull Bridge5Extension_5 var1) {
      if (!this.field9) {
         super.method8(var1);
      }
   }

   @Generated
   public Attackindicator2Handler26(boolean var1, Predicate<Bridge6_4> var2) {
      this.field6 = var1;
      this.field7 = var2;
   }
}
