package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum GlMatrixMode {
   GL_MODELVIEW(5888),
   GL_PROJECTION(5889),
   GL_TEXTURE(5890),
   GL_COLOR(6144);

   private final int id;

   public static GlMatrixMode fromId(int value) {
      for (GlMatrixMode bridgetype3_34 : values()) {
         if (bridgetype3_34.id == value) {
            return bridgetype3_34;
         }
      }

      return null;
   }

   @Generated
   public int getId() {
      return this.id;
   }

   @Generated
   GlMatrixMode(int value) {
      this.id = value;
   }
}
