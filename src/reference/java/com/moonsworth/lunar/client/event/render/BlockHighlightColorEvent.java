package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.Bridge3_17;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.BridgeExtension;
import lombok.Generated;

public class BlockHighlightColorEvent extends BlockHighlightRenderEvent {
   private float red;
   private float green;
   private float blue;

   public BlockHighlightColorEvent(Bridge3_17 var1, BridgeExtension bridge, Bridge3_23 bridge3_23, double value, float value2, float value3, float value4) {
      super(var1, bridge, bridge3_23, value);
      this.red = value2;
      this.green = value3;
      this.blue = value4;
   }

   @Generated
   public float method4() {
      return this.red;
   }

   @Generated
   public float method5() {
      return this.green;
   }

   @Generated
   public float method6() {
      return this.blue;
   }

   @Generated
   public void method4(float var1) {
      this.red = var1;
   }

   @Generated
   public void method5(float var1) {
      this.green = var1;
   }

   @Generated
   public void method6(float var1) {
      this.blue = var1;
   }
}
