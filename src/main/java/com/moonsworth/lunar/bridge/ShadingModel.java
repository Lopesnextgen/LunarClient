package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum ShadingModel {
   GL_FLAT(7424),
   GL_SMOOTH(7425);

   private final int id;

   public static ShadingModel fromId(int value) {
      for (ShadingModel var4 : values()) {
         if (var4.id == value) {
            return var4;
         }
      }

      return null;
   }

   @Generated
   public int getId() {
      return this.id;
   }

   @Generated
   ShadingModel(int value) {
      this.id = value;
   }
}
