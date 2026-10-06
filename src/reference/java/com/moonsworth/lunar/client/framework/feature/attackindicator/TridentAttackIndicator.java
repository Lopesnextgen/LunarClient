package com.moonsworth.lunar.client.framework.feature.attackindicator;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.minecraft.EntityEquipmentSlotBridge;
import java.util.function.Predicate;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class TridentAttackIndicator extends AbstractItemAttackIndicator {
   private final boolean field6;
   private final Predicate<ItemBridge> field7;
   private boolean field8;
   private boolean field9;
   private ItemStackBridge field10 = null;

   @Override
   public boolean isVanilla() {
      return this.field6;
   }

   @Override
   protected boolean method3(@NotNull ItemStackBridge bridgeextension_41) {
      return this.field7.test(bridgeextension_41.bridge$getItem());
   }

   @Override
   public float method2(@NotNull Bridge5Extension_5 bridge5extension_51) {
      if (this.field8) {
         return bridge5extension_51.bridge$getItemProgress();
      } else {
         return bridge5extension_51.bridge$getEquipmentInSlot(EntityEquipmentSlotBridge.MAINHAND) == this.method6()
            ? Math.min(1.0F, bridge5extension_51.bridge$getAttackStrengthScale())
            : 0.0F;
      }
   }

   @Override
   public boolean method3(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return super.method3(bridge5extension_51) || this.field8;
   }

   @Override
   public boolean method4(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return this.method2(bridge5extension_51) >= 1.0F && MeleeAttackIndicator.method10(bridge5extension_51);
   }

   @Nullable
   @Override
   public ItemStackBridge method6(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return this.method6();
   }

   @Override
   public void method8(@Nullable Bridge5Extension_5 bridge5extension_51) {
      super.method8(bridge5extension_51);
      if (bridge5extension_51 == null) {
         this.field10 = null;
      } else {
         boolean flag2 = this.method5(bridge5extension_51);
         this.field9 = this.field8 != flag2 || this.method6() != this.field10;
         this.field8 = flag2;
         this.field10 = this.method6();
      }
   }

   @Override
   public void method7(@NotNull Bridge5Extension_5 bridge5extension_51) {
      if (!this.field9) {
         super.method7(bridge5extension_51);
      }
   }

   @Generated
   public TridentAttackIndicator(boolean flag1, Predicate<ItemBridge> predicate2) {
      this.field6 = flag1;
      this.field7 = predicate2;
   }
}
