package com.moonsworth.lunar.bridge.horsestats;

public enum HorsestatsType2 {
   MAINHAND,
   OFFHAND,
   FEET,
   LEGS,
   CHEST,
   HEAD;

   private static final HorsestatsType2[] armor = new HorsestatsType2[]{FEET, LEGS, CHEST, HEAD};

   public static HorsestatsType2[] armorValues() {
      return armor;
   }
}
