package com.moonsworth.lunar.bridge;

import lombok.Generated;

public class PoseStackDataBridge implements PoseStackBridge {
   private final Bridge5_16 field1;

   public void translate(double value1, double value3, double value) {
      this.field1.bridge$translate(value1, value3, value);
   }

   public void scale(float value1, float value2, float value3) {
      this.field1.bridge$scale(value1, value2, value3);
   }

   public void method5(float value1, float value2, float value3) {
      this.field1.bridge$rotateDegrees(value1, value2, value3);
   }

   @Generated
   public PoseStackDataBridge(Bridge5_16 bridge5_161) {
      this.field1 = bridge5_161;
   }
}
