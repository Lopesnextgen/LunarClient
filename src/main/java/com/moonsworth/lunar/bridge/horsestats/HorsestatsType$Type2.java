package com.moonsworth.lunar.bridge.horsestats;

public enum HorsestatsType$Type2 {
   POSITIVE(1, "Towards positive"),
   NEGATIVE(-1, "Towards negative");

   private final int offset;
   private final String description;

   HorsestatsType$Type2(int value, String text) {
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

   public HorsestatsType$Type2 getOpposite() {
      return this == POSITIVE ? NEGATIVE : POSITIVE;
   }
}
