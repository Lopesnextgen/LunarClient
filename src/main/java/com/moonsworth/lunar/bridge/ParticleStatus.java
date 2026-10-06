package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum ParticleStatus {
   ALL(1),
   DECREASED(2),
   MINIMAL(3);

   private final int protoId;

   @Generated
   public int getProtoId() {
      return this.protoId;
   }

   @Generated
   ParticleStatus(int value) {
      this.protoId = value;
   }
}
