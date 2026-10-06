package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum MixinHelper2$Type2 {
   OFF(3),
   FAST(2),
   FANCY(1);

   private final int protoId;

   @Generated
   public int getProtoId() {
      return this.protoId;
   }

   @Generated
   MixinHelper2$Type2(int value) {
      this.protoId = value;
   }
}
