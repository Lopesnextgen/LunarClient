package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import lombok.Generated;

public enum PuzzleType {
   ICE_FILL(-132, -492),
   ICE_PATH(-60, -276),
   BLAZE_LOWER(-96, -204),
   BLAZE_UPPER(-60, -204),
   TP_MAZE(-60, -456),
   THREE_WEIRDOS(-60, -96),
   WATER_BOARD(-60, -60),
   TIC_TAC_TOE(-96, -168),
   QUIZ(-60, -600),
   BOULDER(-60, -564),
   BOMB(-60, -636),
   CREEPER_BEAMS(-60, -528);

   private final int x;
   private final int y;

   public static PuzzleType getByCoords(int value, int value2) {
      for (PuzzleType hologramstype85 : values()) {
         if (hologramstype85.x == value && hologramstype85.y == value2) {
            return hologramstype85;
         }
      }

      return null;
   }

   @Generated
   public int getX() {
      return this.x;
   }

   @Generated
   public int getY() {
      return this.y;
   }

   @Generated
   PuzzleType(int value, int value2) {
      this.x = value;
      this.y = value2;
   }
}
