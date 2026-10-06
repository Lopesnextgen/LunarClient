package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum CloudStatus {
   OFF(3),
   FAST(2),
   FANCY(1);

   private final int protoId;

   @Generated
   public int getProtoId() {
      return this.protoId;
   }

   @Generated
   CloudStatus(int value) {
      this.protoId = value;
   }
}
