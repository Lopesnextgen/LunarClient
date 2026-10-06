package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.Bridge3_17;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.BridgeExtension;
import lombok.Generated;

public abstract class BlockHighlightRenderEvent extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private final Bridge3_17 field1;
   private final BridgeExtension field2;
   private final Bridge3_23 field3;
   private final double field4;

   @Generated
   public Bridge3_17 method1() {
      return this.field1;
   }

   @Generated
   public BridgeExtension method2() {
      return this.field2;
   }

   @Generated
   public Bridge3_23 getBlock() {
      return this.field3;
   }

   @Generated
   public double method3() {
      return this.field4;
   }

   @Generated
   public BlockHighlightRenderEvent(Bridge3_17 bridge3_17, BridgeExtension bridge, Bridge3_23 bridge3_23, double value) {
      this.field1 = bridge3_17;
      this.field2 = bridge;
      this.field3 = bridge3_23;
      this.field4 = value;
   }
}
