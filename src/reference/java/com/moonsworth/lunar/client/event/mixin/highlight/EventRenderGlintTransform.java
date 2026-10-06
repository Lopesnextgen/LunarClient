package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class EventRenderGlintTransform extends com.moonsworth.lunar.client.event.LunarEvent {
   private static final float field1 = -1.13F;
   private final Bridge6_10 field2;
   private final EventRenderGlintTransform.TransformPhase field3;
   private final ItemStackBridge field4;
   private final com.moonsworth.lunar.bridge.horsestats.ItemTransformsBridge.Type field5;
   private final AbstractRenderContext field6;

   public EventRenderGlintTransform(Bridge6_10 bridge6_101, ItemStackBridge bridgeextension_42, com.moonsworth.lunar.bridge.horsestats.ItemTransformsBridge.Type type, AbstractRenderContext bridgeextension_94) {
      this.field2 = bridge6_101;
      this.field4 = bridgeextension_42;
      this.field5 = type;
      this.field6 = bridgeextension_94;
      this.field3 = EventRenderGlintTransform.TransformPhase.AFTER_TRANSFORMS;
   }

   public void method1(float value1, float value2, float value3) {
      boolean flag4 = Ref.MC_VERSION > 5;
      if (this.field3 == EventRenderGlintTransform.TransformPhase.BEFORE_TRANSFORMS || flag4) {
         if (flag4 && this.field5.thirdPerson()) {
            value1 = -value1;
            float value5 = value2;
            value2 = value3;
            value3 = value5;
            value2 = -value2;
         }

         this.field6.translate(value1, value2, value3);
      }
   }

   public void scale(float value1, float value2, float value3) {
      if (this.field3 == EventRenderGlintTransform.TransformPhase.AFTER_TRANSFORMS) {
         this.field6.scale(value1, value2, value3);
      }
   }

   public void method2(float value1) {
      if (this.field3 == EventRenderGlintTransform.TransformPhase.AFTER_TRANSFORMS) {
         if (Ref.MC_VERSION > 5 && this.field5.firstPerson()) {
            value1 += -1.13F;
         }

         this.field6.method4(value1, 1.0F, 0.0F, 0.0F);
      }
   }

   public void method3(float value1) {
      if (this.field3 == EventRenderGlintTransform.TransformPhase.AFTER_TRANSFORMS) {
         this.field6.method4(value1, 0.0F, 1.0F, 0.0F);
      }
   }

   public void method4(float value1) {
      if (this.field3 == EventRenderGlintTransform.TransformPhase.AFTER_TRANSFORMS) {
         this.field6.method4(value1, 0.0F, 0.0F, 1.0F);
      }
   }

   public void method5(float value1, float value2, float value3) {
      if (this.field3 == EventRenderGlintTransform.TransformPhase.AFTER_TRANSFORMS) {
         if (Ref.MC_VERSION > 5 && this.field5.firstPerson()) {
            value1 += -1.13F;
         }

         this.field6.method5(value1, value2, value3);
      }
   }

   @Generated
   public Bridge6_10 method6() {
      return this.field2;
   }

   @Generated
   public ItemStackBridge method7() {
      return this.field4;
   }

   @Generated
   public com.moonsworth.lunar.bridge.horsestats.ItemTransformsBridge.Type method8() {
      return this.field5;
   }

   @Generated
   public EventRenderGlintTransform(
      Bridge6_10 bridge6_101, EventRenderGlintTransform.TransformPhase transformPhase, ItemStackBridge bridgeextension_43, com.moonsworth.lunar.bridge.horsestats.ItemTransformsBridge.Type type, AbstractRenderContext bridgeextension_95
   ) {
      this.field2 = bridge6_101;
      this.field3 = transformPhase;
      this.field4 = bridgeextension_43;
      this.field5 = type;
      this.field6 = bridgeextension_95;
   }

   @Generated
   private EventRenderGlintTransform.TransformPhase method9() {
      return this.field3;
   }

   @Generated
   private AbstractRenderContext method10() {
      return this.field6;
   }

   public enum TransformPhase {
      BEFORE_TRANSFORMS,
      AFTER_TRANSFORMS;

      TransformPhase() {
      }
   }
}
