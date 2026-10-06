package com.moonsworth.lunar.bridge.horsestats;

public enum EquipmentSlotBridge {
   MAINHAND,
   OFFHAND,
   FEET,
   LEGS,
   CHEST,
   HEAD;

   private static final EquipmentSlotBridge[] armor = new EquipmentSlotBridge[]{FEET, LEGS, CHEST, HEAD};

   public static EquipmentSlotBridge[] armorValues() {
      return armor;
   }
}
