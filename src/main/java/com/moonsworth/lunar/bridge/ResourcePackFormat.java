package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum ResourcePackFormat {
   v1_6$1_8(1),
   v1_9$1_10(2),
   v1_11$1_12(3),
   v1_13$1_14(4),
   v1_15$1_16_1(5),
   v1_16_2$1_16_5(6),
   v1_17(7);

   private final int id;

   ResourcePackFormat(int value) {
      this.id = value;
   }

   @Generated
   public int getId() {
      return this.id;
   }
}
