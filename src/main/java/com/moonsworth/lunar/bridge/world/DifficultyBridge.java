package com.moonsworth.lunar.bridge.world;

import java.util.Arrays;
import java.util.Comparator;
import javax.annotation.Nullable;

public enum DifficultyBridge {
   PEACEFUL(0, "peaceful"),
   EASY(1, "easy"),
   NORMAL(2, "normal"),
   HARD(3, "hard");

   public static DifficultyBridge[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(DifficultyBridge::getId)).toArray(DifficultyBridge[]::new);
   public int id;
   public String key;

   DifficultyBridge(int value, String text) {
      this.id = value;
      this.key = text;
   }

   public int getId() {
      return this.id;
   }

   public static DifficultyBridge byId(int index0) {
      return BY_ID[index0 % BY_ID.length];
   }

   @Nullable
   public static DifficultyBridge byName(String text) {
      for (DifficultyBridge itemcountertype4 : values()) {
         if (itemcountertype4.key.equals(text)) {
            return itemcountertype4;
         }
      }

      return null;
   }

   public String getKey() {
      return this.key;
   }
}
