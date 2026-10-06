package com.moonsworth.lunar.bridge.horsestats;

public enum HorsestatsType3 {
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

   HorsestatsType3(int var3, int var4, int var5, String var6) {
      this.index = var3;
      this.horizontalIndex = var5;
      this.opposite = var4;
      this.name = var6;
   }

   public static HorsestatsType3 fromEnumFacingBridge(Horsestats25 var0) {
      return values()[var0.bridge$index()];
   }
}
