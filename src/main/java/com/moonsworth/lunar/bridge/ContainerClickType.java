package com.moonsworth.lunar.bridge;

public enum ContainerClickType {
   PICKUP,
   QUICK_MOVE,
   SWAP,
   CLONE,
   THROW,
   QUICK_CRAFT,
   PICKUP_ALL;

   public static ContainerClickType fromId(int var0) {
      return values()[var0];
   }

   public static ContainerClickType fromVanilla(Enum<?> var0) {
      return fromId(var0.ordinal());
   }

   public int toId() {
      return this.ordinal();
   }

   public <T extends Enum<T>> T toVanilla(T[] items) {
      return (T)items[this.ordinal()];
   }
}
