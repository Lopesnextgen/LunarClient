package com.moonsworth.lunar.client.driver.component;

import lombok.Generated;

public class PositionQuad {
   protected float field1;
   protected float field2;
   protected float field3;
   protected float field4;

   public void reset() {
      this.field1 = 0.0F;
      this.field2 = 0.0F;
      this.field3 = 0.0F;
      this.field4 = 0.0F;
   }

   public static PositionQuad method1() {
      return new PositionQuad(0.0F, 0.0F, 0.0F, 0.0F);
   }

   @Generated
   public float method2() {
      return this.field1;
   }

   @Generated
   public float method3() {
      return this.field2;
   }

   @Generated
   public float method4() {
      return this.field3;
   }

   @Generated
   public float method5() {
      return this.field4;
   }

   @Generated
   public void method6(float value1) {
      this.field1 = value1;
   }

   @Generated
   public void method7(float value1) {
      this.field2 = value1;
   }

   @Generated
   public void method8(float value1) {
      this.field3 = value1;
   }

   @Generated
   public void method9(float value1) {
      this.field4 = value1;
   }

   @Generated
   @Override
   public String toString() {
      return "PositionQuad(one=" + this.method2() + ", two=" + this.method3() + ", three=" + this.method4() + ", four=" + this.method5() + ")";
   }

   @Generated
   public PositionQuad(float value1, float value, float value2, float value3) {
      this.field1 = value1;
      this.field2 = value;
      this.field3 = value2;
      this.field4 = value3;
   }
}
