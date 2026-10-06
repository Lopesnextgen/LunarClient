package com.moonsworth.lunar.bridge.world;

import lombok.Generated;

public enum MapDecorationType {
   GREEN(1),
   BLUE(3);

   private final int legacyId;

   public static MapDecorationType fromLegacyId(int value) {
      for (MapDecorationType itemcounter$type4 : values()) {
         if (itemcounter$type4.legacyId == value) {
            return itemcounter$type4;
         }
      }

      return null;
   }

   @Generated
   MapDecorationType(int value) {
      this.legacyId = value;
   }
}
