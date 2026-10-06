package com.moonsworth.lunar.bridge;

import lombok.Generated;

public class InterpolatedCameraBridge implements CameraBridge {
   private final float field1;
   private final com.moonsworth.lunar.bridge.BridgeExtension field2;

   private static double lerp(double value, double value2, double value3) {
      return value + (value2 - value) * value3;
   }

   public double bridge$getPosX() {
      return lerp(this.field2.method3(), this.field2.bridge$getPosX(), this.field1);
   }

   public double bridge$getPosY() {
      return lerp(this.field2.method4(), this.field2.bridge$getPosY(), this.field1);
   }

   public double bridge$getPosZ() {
      return lerp(this.field2.method5(), this.field2.bridge$getPosZ(), this.field1);
   }

   public float bridge$getYaw() {
      return (float)lerp(this.field2.bridge$getPreviousRotationYaw(), this.field2.bridge$getRotationYaw(), this.field1);
   }

   public float bridge$getPitch() {
      return (float)lerp(this.field2.bridge$getPreviousRotationPitch(), this.field2.bridge$getRotationPitch(), this.field1);
   }

   public void bridge$setEyeHeight(float value1) {
   }

   public boolean method1() {
      return this.field2 instanceof EntityPlayerBridge bridgeextension2221 && bridgeextension2221.method2();
   }

   @Generated
   public InterpolatedCameraBridge(float value1, com.moonsworth.lunar.bridge.BridgeExtension bridge) {
      this.field1 = value1;
      this.field2 = bridge;
   }
}
