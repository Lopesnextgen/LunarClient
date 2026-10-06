package com.moonsworth.lunar.client.framework.feature.attackindicator;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.minecraft.EntityEquipmentSlotBridge;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

abstract class AbstractItemAttackIndicator implements AttackIndicatorProvider {
   private ItemStackBridge field4 = null;
   private boolean field5 = false;

   @Override
   public boolean method1(@NotNull Bridge5Extension_5 bridge5extension_51) {
      return this.field4 != null;
   }

   public boolean method5(@NotNull Bridge5Extension_5 bridge5extension_51) {
      ItemStackBridge bridgeextension_42 = (ItemStackBridge)bridge5extension_51.bridge$getItemInUse().orElse(null);
      return bridgeextension_42 != null && bridgeextension_42 == this.method6();
   }

   protected abstract boolean method3(@NotNull ItemStackBridge bridgeextension_41);

   @Override
   public int method5() {
      return this.field4 == null ? 0 : (this.field5 ? 0 : 1);
   }

   @Override
   public void method8(@Nullable Bridge5Extension_5 bridge5extension_51) {
      this.field4 = null;
      if (bridge5extension_51 != null) {
         ItemStackBridge bridgeextension_42 = bridge5extension_51.bridge$getEquipmentInSlot(EntityEquipmentSlotBridge.MAINHAND);
         if (bridgeextension_42 != null && !bridgeextension_42.bridge$isEmpty() && this.method3(bridgeextension_42)) {
            this.field4 = bridgeextension_42;
            this.field5 = false;
         } else {
            bridgeextension_42 = bridge5extension_51.bridge$getEquipmentInSlot(EntityEquipmentSlotBridge.OFFHAND);
            if (bridgeextension_42 != null && !bridgeextension_42.bridge$isEmpty() && this.method3(bridgeextension_42)) {
               this.field4 = bridgeextension_42;
               this.field5 = true;
            }
         }
      }
   }

   @Generated
   public AbstractItemAttackIndicator() {
   }

   @Generated
   public ItemStackBridge method6() {
      return this.field4;
   }
}
