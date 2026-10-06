package com.moonsworth.lunar.legacy.wrapper;

import net.minecraft.util.EnumFacing;

enum BakedQuadFace {
   DOWN(EnumFacing.DOWN),
   UP(EnumFacing.UP),
   WEST(EnumFacing.WEST),
   EAST(EnumFacing.EAST);

   private final EnumFacing enumFacing;

   BakedQuadFace(EnumFacing facing3) {
      this.enumFacing = facing3;
   }

   EnumFacing getFacing() {
      return this.enumFacing;
   }
}
