package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum TextureFiltering {
   NONE(0),
   RGSS(1),
   ANISOTROPIC(2);

   private final int protoId;

   @Generated
   public int getProtoId() {
      return this.protoId;
   }

   @Generated
   TextureFiltering(int value) {
      this.protoId = value;
   }
}
