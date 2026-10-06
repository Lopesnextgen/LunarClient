package com.moonsworth.lunar.bridge;

import lombok.Generated;

public class Bridge5$Data implements Bridge_8 {
   private final Bridge5_16 field1;

   @Override
   public void translate(double var1, double var3, double value) {
      this.field1.bridge$translate(var1, var3, value);
   }

   @Override
   public void scale(float var1, float var2, float var3) {
      this.field1.bridge$scale(var1, var2, var3);
   }

   @Override
   public void method5(float var1, float var2, float var3) {
      this.field1.bridge$rotateDegrees(var1, var2, var3);
   }

   @Generated
   public Bridge5$Data(Bridge5_16 var1) {
      this.field1 = var1;
   }
}
