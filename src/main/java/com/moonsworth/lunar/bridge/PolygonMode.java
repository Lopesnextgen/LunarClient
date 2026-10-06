package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum PolygonMode implements GlEnum {
   GL_POINT(6912, false),
   GL_LINE(6913, true),
   GL_FILL(6914, true);

   private final int glId;
   private final boolean supported;

   @Generated
   public int getGlId() {
      return this.glId;
   }

   @Generated
   public boolean isSupported() {
      return this.supported;
   }

   @Generated
   PolygonMode(int value, boolean flag) {
      this.glId = value;
      this.supported = flag;
   }
}
