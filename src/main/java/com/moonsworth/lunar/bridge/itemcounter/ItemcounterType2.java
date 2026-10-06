package com.moonsworth.lunar.bridge.itemcounter;

import lombok.Generated;

public enum ItemcounterType2 {
   SURVIVAL(0, "survival"),
   CREATIVE(1, "creative"),
   ADVENTURE(2, "adventure"),
   SPECTATOR(3, "spectator");

   public static final ItemcounterType2[] VALUES = values();
   private final int id;
   private final String name;

   ItemcounterType2(int value, String var4) {
      this.id = value;
      this.name = var4;
   }

   public boolean isAdventure() {
      return this == ADVENTURE || this == SPECTATOR;
   }

   public boolean isCreative() {
      return this == CREATIVE;
   }

   public boolean isSurvivalOrAdventure() {
      return this == SURVIVAL || this == ADVENTURE;
   }

   public static ItemcounterType2 getByID(int var0) {
      for (ItemcounterType2 var4 : VALUES) {
         if (var4.id == var0) {
            return var4;
         }
      }

      return SURVIVAL;
   }

   public static ItemcounterType2 getByName(String var0) {
      for (ItemcounterType2 var4 : VALUES) {
         if (var4.name.equals(var0)) {
            return var4;
         }
      }

      return SURVIVAL;
   }

   @Generated
   public int getId() {
      return this.id;
   }

   @Generated
   public String getName() {
      return this.name;
   }
}
