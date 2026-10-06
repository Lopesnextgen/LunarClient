package com.moonsworth.lunar.client.framework.feature.armorstatus.mixin;

import lombok.Generated;

public enum ArmorstatusType {
   HELD_ITEM(-1),
   HELMET(3),
   CHESTPLATE(2),
   LEGGINGS(1),
   BOOTS(0),
   OFF_HAND_HELD_ITEM(-2);

   private final int slotId;

   public boolean isHeld() {
      return this == HELD_ITEM || this == OFF_HAND_HELD_ITEM;
   }

   @Generated
   ArmorstatusType(int value) {
      this.slotId = value;
   }

   @Generated
   public int getSlotId() {
      return this.slotId;
   }
}
