package com.moonsworth.lunar.bridge.minecraft;

public enum DirectionAxisDirection {
   POSITIVE(1, "Towards positive"),
   NEGATIVE(-1, "Towards negative");

   private final int offset;
   private final String description;

   DirectionAxisDirection(int value, String text) {
      this.offset = value;
      this.description = text;
   }

   public int offset() {
      return this.offset;
   }

   @Override
   public String toString() {
      return this.description;
   }

   public DirectionAxisDirection getOpposite() {
      return this == POSITIVE ? NEGATIVE : POSITIVE;
   }
}
