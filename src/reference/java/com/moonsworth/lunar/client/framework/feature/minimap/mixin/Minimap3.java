package com.moonsworth.lunar.client.framework.feature.minimap.mixin;

import com.moonsworth.lunar.bridge.Bridge2_17;

public class Minimap3 {
   private final int height;
   private final int depth;
   private final Bridge2_17 fluidState;

   public Minimap3(int value, int value2, Bridge2_17 bridge2_17) {
      this.height = value;
      this.depth = value2;
      this.fluidState = bridge2_17;
   }

   public int method1() {
      return this.height;
   }

   public int getDepth() {
      return this.depth;
   }

   public Bridge2_17 getFluidState() {
      return this.fluidState;
   }
}
