package com.moonsworth.lunar.bridge.minecraft;

public enum EnumFacingValue {
   DOWN(0, 1, -1, "down"),
   UP(1, 0, -1, "up"),
   NORTH(2, 3, 2, "north"),
   SOUTH(3, 2, 0, "south"),
   WEST(4, 5, 1, "west"),
   EAST(5, 4, 3, "east");

   public final int index;
   public final int opposite;
   public final int horizontalIndex;
   public final String name;

   EnumFacingValue(int value, int value2, int value3, String text) {
      this.index = value;
      this.horizontalIndex = value3;
      this.opposite = value2;
      this.name = text;
   }

   public static EnumFacingValue fromEnumFacingBridge(EnumFacingBridge horsestats250) {
      return values()[horsestats250.bridge$index()];
   }
}
