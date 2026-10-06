package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.EquipmentSlotBridge;

public interface BridgeExtension22_3 extends BridgeExtension2_2 {
   default ItemStackRenderStateBridge bridge$getArmor(EquipmentSlotBridge equipmentSlotBridge) {
      return switch (equipmentSlotBridge) {
         case HEAD -> this.bridge$getHeadItem();
         case CHEST -> this.bridge$getChestItem();
         case LEGS -> this.bridge$getLegsItem();
         case FEET -> this.bridge$getFeetItem();
         default -> throw new IllegalStateException("Invalid armor slot: " + equipmentSlotBridge);
      };
   }

   ItemStackRenderStateBridge bridge$getMainHandItemRenderState();

   @com.moonsworth.lunar.ichor.Annotation2(min = 5)
   ItemStackRenderStateBridge bridge$getOffHandItemRenderState();

   @Override
   ItemStackRenderStateBridge bridge$getHeadItem();

   ItemStackBridge bridge$getChestItem();

   ItemStackBridge bridge$getLegsItem();

   ItemStackBridge bridge$getFeetItem();
}
