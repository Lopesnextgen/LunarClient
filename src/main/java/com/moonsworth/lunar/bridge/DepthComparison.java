package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum DepthComparison {
   GL_NEVER(512),
   GL_LESS(513),
   GL_EQUAL(514),
   GL_LEQUAL(515),
   GL_GREATER(516),
   GL_NOTEQUAL(517),
   GL_GEQUAL(518),
   GL_ALWAYS(519);

   private final int id;

   public static DepthComparison fromId(int value) {
      for (DepthComparison var4 : values()) {
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
   DepthComparison(int value) {
      this.id = value;
   }
}
