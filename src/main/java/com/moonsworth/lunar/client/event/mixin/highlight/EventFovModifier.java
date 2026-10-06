package com.moonsworth.lunar.client.event.mixin.highlight;

import lombok.Generated;

public abstract class EventFovModifier extends com.moonsworth.lunar.client.event.CancellableEvent {
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
   public EventFovModifier(float value1, float value2, float value3, float value4) {
      this.field1 = value1;
      this.field2 = value2;
      this.fovModifierHandPrev = value3;
      this.fovModifierHand = value4;
   }

   @Generated
   public void method5(float value1) {
      this.field1 = value1;
   }

   public static class FovInput extends EventFovModifier {
      public FovInput(float value1, float value2, float value3, float value4) {
         super(value1, value2, value3, value4);
      }
   }

   public static class EventFovModifierPost extends EventFovModifier {
      public EventFovModifierPost(float value1, float value2, float value3, float value4) {
         super(value1, value2, value3, value4);
      }
   }
}
