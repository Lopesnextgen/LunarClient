package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import lombok.Generated;

enum MapRotation {
   NORTH(0, -1),
   EAST(31, 0),
   SOUTH(0, 31),
   WEST(-1, 0);

   private final int x;
   private final int z;

   @Generated
   MapRotation(int value, int value2) {
      this.x = value;
      this.z = value2;
   }

   @Generated
   public int getX() {
      return this.x;
   }

   @Generated
   public int getZ() {
      return this.z;
   }
}
