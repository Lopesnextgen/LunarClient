package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum ShadeModel {
   GL_FLAT(7424),
   GL_SMOOTH(7425);

   private final int id;

   public static ShadeModel fromId(int value) {
      for (ShadeModel bridgetype44 : values()) {
         if (bridgetype44.id == value) {
            return bridgetype44;
         }
      }

      return null;
   }

   @Generated
   public int getId() {
      return this.id;
   }

   @Generated
   ShadeModel(int value) {
      this.id = value;
   }
}
