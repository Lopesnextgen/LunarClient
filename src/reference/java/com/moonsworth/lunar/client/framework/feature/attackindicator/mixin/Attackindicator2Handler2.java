package com.moonsworth.lunar.client.framework.feature.attackindicator.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.EquipmentSlotBridge;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

abstract class Attackindicator2Handler2 implements Attackindicator2 {
   private ItemStackBridge field4 = null;
   private boolean field5 = false;

   @Override
   public boolean method1(@NotNull Bridge5Extension_5 var1) {
      return this.field4 != null;
   }

   public boolean method5(@NotNull Bridge5Extension_5 var1) {
      ItemStackBridge var2 = (ItemStackBridge)var1.bridge$getItemInUse().orElse(null);
      return var2 != null && var2 == this.method6();
   }

   protected abstract boolean method3(@NotNull ItemStackBridge var1);

   @Override
   public int method5() {
      return this.field4 == null ? 0 : (this.field5 ? 0 : 1);
   }

   @Override
   public void method8(@Nullable Bridge5Extension_5 var1) {
      this.field4 = null;
      if (var1 != null) {
         ItemStackBridge var2 = var1.bridge$getEquipmentInSlot(EquipmentSlotBridge.MAINHAND);
         if (var2 != null && !var2.bridge$isEmpty() && this.method3(var2)) {
            this.field4 = var2;
            this.field5 = false;
         } else {
            var2 = var1.bridge$getEquipmentInSlot(EquipmentSlotBridge.OFFHAND);
            if (var2 != null && !var2.bridge$isEmpty() && this.method3(var2)) {
               this.field4 = var2;
               this.field5 = true;
            }
         }
      }
   }

   @Generated
   public Attackindicator2Handler2() {
   }

   @Generated
   public ItemStackBridge method6() {
      return this.field4;
   }
}
