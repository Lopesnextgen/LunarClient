package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum TextureFormat {
   RGBA8(4),
   RED8(1),
   DEPTH32(4);

   private final int pixelSize;

   public boolean hasColorAspect() {
      return this == RGBA8 || this == RED8;
   }

   public boolean hasDepthAspect() {
      return this == DEPTH32;
   }

   @Generated
   public int pixelSize() {
      return this.pixelSize;
   }

   @Generated
   TextureFormat(int value) {
      this.pixelSize = value;
   }
}
