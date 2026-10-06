package com.moonsworth.lunar.client.framework.feature.crosshair;

import lombok.Generated;

public enum CrosshairGridSize implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   SMALL("crosshairGridSmall", 7),
   MEDIUM("crosshairGridMedium", 15),
   BIG("crosshairGridBig", 31),
   HUGE("crosshairGridHuge", 63);

   private final String id;
   private final int gridSize;

   public static CrosshairGridSize fromGridLength(int number0) {
      for (CrosshairGridSize gui2extension24 : values()) {
         if (number0 <= gui2extension24.gridSize) {
            return gui2extension24;
         }
      }

      throw new IllegalArgumentException(String.format("Invalid crosshair grid %d * %d", number0, number0));
   }

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   public int size() {
      return this.gridSize;
   }

   public CrosshairGridSize smaller() {
      CrosshairGridSize[] items1 = values();
      int index2 = Math.max(0, this.ordinal() - 1);
      return items1[index2];
   }

   public CrosshairGridSize bigger() {
      CrosshairGridSize[] items1 = values();
      int index2 = Math.min(items1.length - 1, this.ordinal() + 1);
      return items1[index2];
   }

   @Generated
   CrosshairGridSize(String text3, int number4) {
      this.id = text3;
      this.gridSize = number4;
   }
}
