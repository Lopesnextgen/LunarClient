package com.moonsworth.lunar.client.util.math;

import lombok.Generated;

public enum Direction2D {
   LEFT(-1, 0),
   UP(0, 1),
   RIGHT(1, 0),
   DOWN(0, -1);

   private final int i;
   private final int j;

   public Direction2D getAdjacent() {
      return this == LEFT ? DOWN : values()[this.ordinal() - 1];
   }

   public Direction2D getOpposite() {
      return values()[(this.ordinal() + 2) % 4];
   }

   @Generated
   Direction2D(int value, int value2) {
      this.i = value;
      this.j = value2;
   }

   @Generated
   public int getI() {
      return this.i;
   }

   @Generated
   public int getJ() {
      return this.j;
   }
}
