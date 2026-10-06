package com.moonsworth.lunar.bridge.itemcounter;

import lombok.Generated;

public enum Itemcounter$Type {
   GREEN(1),
   BLUE(3);

   private final int legacyId;

   public static Itemcounter$Type fromLegacyId(int value) {
      for (Itemcounter$Type var4 : values()) {
         if (var4.legacyId == value) {
            return var4;
         }
      }

      return null;
   }

   @Generated
   Itemcounter$Type(int value) {
      this.legacyId = value;
   }
}
