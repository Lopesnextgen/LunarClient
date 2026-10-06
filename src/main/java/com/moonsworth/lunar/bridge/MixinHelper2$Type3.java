package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum MixinHelper2$Type3 {
   NONE(1),
   PLAYER_AFFECTED(2),
   NEARBY(3),
   UNSPECIFIED(0);

   private final int protoId;

   @Generated
   public int getProtoId() {
      return this.protoId;
   }

   @Generated
   MixinHelper2$Type3(int value) {
      this.protoId = value;
   }
}
