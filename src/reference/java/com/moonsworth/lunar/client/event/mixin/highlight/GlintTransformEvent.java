package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class GlintTransformEvent extends com.moonsworth.lunar.client.highlight.Highlight {
   private static final float field1 = -1.13F;
   private final Bridge6_10 field2;
   private final GlintTransformEvent.Type field3;
   private final ItemStackBridge field4;
   private final com.moonsworth.lunar.bridge.horsestats.ItemTransformsBridge.Type field5;
   private final AbstractRenderContext field6;

   public GlintTransformEvent(Bridge6_10 var1, ItemStackBridge var2, com.moonsworth.lunar.bridge.horsestats.ItemTransformsBridge.Type var3, AbstractRenderContext var4) {
      this.field2 = var1;
      this.field4 = var2;
      this.field5 = var3;
      this.field6 = var4;
      this.field3 = GlintTransformEvent.Type.AFTER_TRANSFORMS;
   }

   public void method1(float var1, float var2, float var3) {
      boolean var4 = ThreadModuleDump63.MC_VERSION > 5;
      if (this.field3 == GlintTransformEvent.Type.BEFORE_TRANSFORMS || var4) {
         if (var4 && this.field5.thirdPerson()) {
            var1 = -var1;
            float var5 = var2;
            var2 = var3;
            var3 = var5;
            var2 = -var2;
         }

         this.field6.translate(var1, var2, var3);
      }
   }

   public void scale(float var1, float var2, float var3) {
      if (this.field3 == GlintTransformEvent.Type.AFTER_TRANSFORMS) {
         this.field6.scale(var1, var2, var3);
      }
   }

   public void method2(float var1) {
      if (this.field3 == GlintTransformEvent.Type.AFTER_TRANSFORMS) {
         if (ThreadModuleDump63.MC_VERSION > 5 && this.field5.firstPerson()) {
            var1 += -1.13F;
         }

         this.field6.method4(var1, 1.0F, 0.0F, 0.0F);
      }
   }

   public void method3(float var1) {
      if (this.field3 == GlintTransformEvent.Type.AFTER_TRANSFORMS) {
         this.field6.method4(var1, 0.0F, 1.0F, 0.0F);
      }
   }

   public void method4(float var1) {
      if (this.field3 == GlintTransformEvent.Type.AFTER_TRANSFORMS) {
         this.field6.method4(var1, 0.0F, 0.0F, 1.0F);
      }
   }

   public void method5(float var1, float var2, float var3) {
      if (this.field3 == GlintTransformEvent.Type.AFTER_TRANSFORMS) {
         if (ThreadModuleDump63.MC_VERSION > 5 && this.field5.firstPerson()) {
            var1 += -1.13F;
         }

         this.field6.method5(var1, var2, var3);
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
   public GlintTransformEvent(
      Bridge6_10 var1, GlintTransformEvent.Type var2, ItemStackBridge var3, com.moonsworth.lunar.bridge.horsestats.ItemTransformsBridge.Type var4, AbstractRenderContext var5
   ) {
      this.field2 = var1;
      this.field3 = var2;
      this.field4 = var3;
      this.field5 = var4;
      this.field6 = var5;
   }

   @Generated
   private GlintTransformEvent.Type method9() {
      return this.field3;
   }

   @Generated
   private AbstractRenderContext method10() {
      return this.field6;
   }

   public enum Type {
      BEFORE_TRANSFORMS,
      AFTER_TRANSFORMS;
   }
}
