package com.moonsworth.lunar.bridge;

public enum ClickTypeBridge {
   PICKUP,
   QUICK_MOVE,
   SWAP,
   CLONE,
   THROW,
   QUICK_CRAFT,
   PICKUP_ALL;

   ClickTypeBridge() {
   }

   public static ClickTypeBridge fromId(int index0) {
      return values()[index0];
   }

   public static ClickTypeBridge fromVanilla(Enum<?> enumValue) {
      return fromId(enumValue.ordinal());
   }

   public int toId() {
      return this.ordinal();
   }

   public <T extends Enum<T>> T toVanilla(T[] items1) {
      return (T)items1[this.ordinal()];
   }
}
