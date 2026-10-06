package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum PrioritizeChunkUpdates {
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
   PrioritizeChunkUpdates(int value) {
      this.protoId = value;
   }
}
