package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum PolygonDrawMode implements Bridge_23 {
   GL_POINT(6912, false),
   GL_LINE(6913, true),
   GL_FILL(6914, true);

   private final int glId;
   private final boolean supported;

   @Generated
   @Override
   public int getGlId() {
      return this.glId;
   }

   @Generated
   @Override
   public boolean isSupported() {
      return this.supported;
   }

   @Generated
   PolygonDrawMode(int value, boolean flag) {
      this.glId = value;
      this.supported = flag;
   }
}
