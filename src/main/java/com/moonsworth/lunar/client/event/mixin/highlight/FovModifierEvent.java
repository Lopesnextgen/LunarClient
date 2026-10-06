package com.moonsworth.lunar.client.event.mixin.highlight;

import lombok.Generated;

public abstract class FovModifierEvent extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private float field1;
   private final float field2;
   private final float fovModifierHandPrev;
   private final float fovModifierHand;

   @Generated
   public float method1() {
      return this.field1;
   }

   @Generated
   public float method2() {
      return this.field2;
   }

   @Generated
   public float getFovModifierHandPrev() {
      return this.fovModifierHandPrev;
   }

   @Generated
   public float getFovModifierHand() {
      return this.fovModifierHand;
   }

   @Generated
   public FovModifierEvent(float var1, float var2, float var3, float var4) {
      this.field1 = var1;
      this.field2 = var2;
      this.fovModifierHandPrev = var3;
      this.fovModifierHand = var4;
   }

   @Generated
   public void method5(float var1) {
      this.field1 = var1;
   }

   public static class Data extends FovModifierEvent {
      public Data(float var1, float var2, float var3, float var4) {
         super(var1, var2, var3, var4);
      }
   }

   public static class FovModifierPostEvent extends FovModifierEvent {
      public FovModifierPostEvent(float var1, float var2, float var3, float var4) {
         super(var1, var2, var3, var4);
      }
   }
}
