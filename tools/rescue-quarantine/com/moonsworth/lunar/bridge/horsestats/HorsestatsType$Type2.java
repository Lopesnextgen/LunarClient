package com.moonsworth.lunar.bridge.horsestats;

public enum HorsestatsType$Type2 {
   POSITIVE(1, "Towards positive"),
   NEGATIVE(-1, "Towards negative");

   private final int offset;
   private final String description;

   HorsestatsType$Type2(int var3, String var4) {
      this.offset = var3;
      this.description = var4;
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
