package com.moonsworth.lunar.bridge;

import lombok.Generated;

public class Bridge2$Data implements Bridge2_19 {
   private final float field1;
   private final BridgeExtension field2;

   private static double lerp(double value, double var2, double value2) {
      return value + (var2 - value) * value2;
   }

   @Override
   public double bridge$getPosX() {
      return lerp(this.field2.method3(), this.field2.bridge$getPosX(), this.field1);
   }

   @Override
   public double bridge$getPosY() {
      return lerp(this.field2.method4(), this.field2.bridge$getPosY(), this.field1);
   }

   @Override
   public double bridge$getPosZ() {
      return lerp(this.field2.method5(), this.field2.bridge$getPosZ(), this.field1);
   }

   @Override
   public float bridge$getYaw() {
      return (float)lerp(this.field2.bridge$getPreviousRotationYaw(), this.field2.bridge$getRotationYaw(), this.field1);
   }

   @Override
   public float bridge$getPitch() {
      return (float)lerp(this.field2.bridge$getPreviousRotationPitch(), this.field2.bridge$getRotationPitch(), this.field1);
   }

   @Override
   public void bridge$setEyeHeight(float var1) {
   }

   public boolean method1() {
      return this.field2 instanceof EntityPlayerBridge var1 && var1.method2();
   }

   @Generated
   public Bridge2$Data(float var1, BridgeExtension var2) {
      this.field1 = var1;
      this.field2 = var2;
   }
}
