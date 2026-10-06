package com.moonsworth.lunar.legacy.wrapper;

import net.minecraft.util.EnumFacing;

enum Wrapper2$Type {
   DOWN(EnumFacing.DOWN),
   UP(EnumFacing.UP),
   WEST(EnumFacing.WEST),
   EAST(EnumFacing.EAST);

   private final EnumFacing enumFacing;

   Wrapper2$Type(EnumFacing enumFacing2) {
      this.enumFacing = enumFacing2;
   }

   EnumFacing getFacing() {
      return this.enumFacing;
   }
}
