package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum DepthFunction {
   GL_NEVER(512),
   GL_LESS(513),
   GL_EQUAL(514),
   GL_LEQUAL(515),
   GL_GREATER(516),
   GL_NOTEQUAL(517),
   GL_GEQUAL(518),
   GL_ALWAYS(519);

   private final int id;

   public static DepthFunction fromId(int value) {
      for (DepthFunction bridgetype_104 : values()) {
         if (bridgetype_104.id == value) {
            return bridgetype_104;
         }
      }

      return null;
   }

   @Generated
   public int getId() {
      return this.id;
   }

   @Generated
   DepthFunction(int value) {
      this.id = value;
   }
}
