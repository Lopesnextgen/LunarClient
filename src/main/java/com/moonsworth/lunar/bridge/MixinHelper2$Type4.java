package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum MixinHelper2$Type4 {
   NONE(0),
   RGSS(1),
   ANISOTROPIC(2);

   private final int protoId;

   @Generated
   public int getProtoId() {
      return this.protoId;
   }

   @Generated
   MixinHelper2$Type4(int value) {
      this.protoId = value;
   }
}
