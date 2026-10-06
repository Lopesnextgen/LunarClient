package com.moonsworth.lunar.bridge.minecraft;

public enum EntityEquipmentSlotBridge {
   MAINHAND,
   OFFHAND,
   FEET,
   LEGS,
   CHEST,
   HEAD;

   private static final EntityEquipmentSlotBridge[] armor = new EntityEquipmentSlotBridge[]{FEET, LEGS, CHEST, HEAD};

   EntityEquipmentSlotBridge() {
   }

   public static EntityEquipmentSlotBridge[] armorValues() {
      return armor;
   }
}
