package com.moonsworth.lunar.bridge.world;

import lombok.Generated;

public enum GameTypeBridge {
   SURVIVAL(0, "survival"),
   CREATIVE(1, "creative"),
   ADVENTURE(2, "adventure"),
   SPECTATOR(3, "spectator");

   public static final GameTypeBridge[] VALUES = values();
   private final int id;
   private final String name;

   GameTypeBridge(int value, String text) {
      this.id = value;
      this.name = text;
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

   public static GameTypeBridge getByID(int value) {
      for (GameTypeBridge itemcountertype24 : VALUES) {
         if (itemcountertype24.id == value) {
            return itemcountertype24;
         }
      }

      return SURVIVAL;
   }

   public static GameTypeBridge getByName(String text) {
      for (GameTypeBridge itemcountertype24 : VALUES) {
         if (itemcountertype24.name.equals(text)) {
            return itemcountertype24;
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
