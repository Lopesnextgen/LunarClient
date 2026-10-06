package com.moonsworth.lunar.client.cosmetics;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import lombok.Generated;
import org.joml.Vector3f;

public class SprayPlacementTracker implements SprayPlacement {
   private final SprayEntry field1;
   private long field2;
   private final Vector3f field3;
   private final HorsestatsType_2 field4;
   private final float field5;
   private final Horsestats20Extension2[] field6;
   private final int field7;

   @Generated
   @Override
   public SprayEntry method1() {
      return this.field1;
   }

   @Generated
   public long method6() {
      return this.field2;
   }

   @Generated
   @Override
   public Vector3f method2() {
      return this.field3;
   }

   @Generated
   @Override
   public HorsestatsType_2 method3() {
      return this.field4;
   }

   @Generated
   @Override
   public float getRotation() {
      return this.field5;
   }

   @Generated
   @Override
   public Horsestats20Extension2[] method4() {
      return this.field6;
   }

   @Generated
   @Override
   public int method5() {
      return this.field7;
   }

   @Generated
   public SprayPlacementTracker(SprayEntry var1, long value, Vector3f vector3f, HorsestatsType_2 horsestatsType_2, float value2, Horsestats20Extension2[] items, int value3) {
      this.field1 = var1;
      this.field2 = value;
      this.field3 = vector3f;
      this.field4 = horsestatsType_2;
      this.field5 = value2;
      this.field6 = items;
      this.field7 = value3;
   }

   @Generated
   public void method7(long var1) {
      this.field2 = var1;
   }
}
