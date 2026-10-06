package com.moonsworth.lunar.client.framework.feature;

import lombok.Generated;

public class HudElementBounds implements HudElement {
   private final float field1;
   private final float field2;
   private final float field3;
   private final float field4;
   private final float field5;

   public HudElementBounds(float value1, float value2, float value3, float value4) {
      this(value1, value2, value3, value4, 1.0F);
   }

   public HudElementBounds(float value1, float value2, float value3, float value4, float value) {
      this.field4 = value1;
      this.field5 = value2;
      this.field1 = value3;
      this.field2 = value4;
      this.field3 = value;
   }

   @Generated
   @Override
   public float getWidth() {
      return this.field1;
   }

   @Generated
   @Override
   public float getHeight() {
      return this.field2;
   }

   @Generated
   @Override
   public float getScale() {
      return this.field3;
   }

   @Generated
   @Override
   public float method1() {
      return this.field4;
   }

   @Generated
   @Override
   public float method2() {
      return this.field5;
   }
}
