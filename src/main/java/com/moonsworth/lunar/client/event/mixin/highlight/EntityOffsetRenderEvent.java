package com.moonsworth.lunar.client.event.mixin.highlight;

import lombok.Generated;

public class EntityOffsetRenderEvent extends com.moonsworth.lunar.client.highlight.Highlight {
   private final boolean field1;
   private float field2 = 0.0F;
   private float field3 = 0.0F;
   private float field4 = 0.0F;
   private float field5 = 0.0F;
   private float field6 = 0.0F;
   private float field7 = 0.0F;
   private float scale = 1.0F;

   public void method1(float var1, float var2, float var3) {
      this.field2 = var1;
      this.field3 = var2;
      this.field4 = var3;
   }

   public void method2(float var1, float var2, float var3) {
      this.field5 = var1;
      this.field6 = var2;
      this.field7 = var3;
   }

   @Generated
   public boolean method3() {
      return this.field1;
   }

   @Generated
   public float getXOffset() {
      return this.field2;
   }

   @Generated
   public float getYOffset() {
      return this.field3;
   }

   @Generated
   public float method4() {
      return this.field4;
   }

   @Generated
   public float method5() {
      return this.field5;
   }

   @Generated
   public float method6() {
      return this.field6;
   }

   @Generated
   public float method7() {
      return this.field7;
   }

   @Generated
   public float getScale() {
      return this.scale;
   }

   @Generated
   public EntityOffsetRenderEvent(boolean var1) {
      this.field1 = var1;
   }

   @Generated
   public void setScale(float var1) {
      this.scale = var1;
   }
}
