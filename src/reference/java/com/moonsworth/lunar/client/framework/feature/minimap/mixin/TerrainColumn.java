package com.moonsworth.lunar.client.framework.feature.minimap.mixin;

import com.moonsworth.lunar.bridge.BlockStateBridge;

public class TerrainColumn {
   private final int height;
   private final int depth;
   private final BlockStateBridge fluidState;

   public TerrainColumn(int value, int value2, BlockStateBridge bridge2_173) {
      this.height = value;
      this.depth = value2;
      this.fluidState = bridge2_173;
   }

   public int method1() {
      return this.height;
   }

   public int getDepth() {
      return this.depth;
   }

   public BlockStateBridge getFluidState() {
      return this.fluidState;
   }
}
