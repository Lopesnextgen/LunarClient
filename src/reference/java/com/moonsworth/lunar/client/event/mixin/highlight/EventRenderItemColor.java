package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.ichor.VersionGate;
import lombok.Generated;

@VersionGate(1)
public class EventRenderItemColor extends com.moonsworth.lunar.client.event.CancellableEvent {
   private final float field1;
   private final float field2;
   private final ItemStackBridge field3;
   private final AbstractRenderContext field4;
   private final Runnable field5;
   private final Runnable field6;

   @Generated
   public EventRenderItemColor(float value, float value2, ItemStackBridge bridgeextension_43, AbstractRenderContext bridgeextension_94, Runnable runnable5, Runnable runnable6) {
      this.field1 = value;
      this.field2 = value2;
      this.field3 = bridgeextension_43;
      this.field4 = bridgeextension_94;
      this.field5 = runnable5;
      this.field6 = runnable6;
   }

   @Generated
   public float method1() {
      return this.field1;
   }

   @Generated
   public float method2() {
      return this.field2;
   }

   @Generated
   public ItemStackBridge getItem() {
      return this.field3;
   }

   @Generated
   public AbstractRenderContext method3() {
      return this.field4;
   }

   @Generated
   public Runnable method4() {
      return this.field5;
   }

   @Generated
   public Runnable method5() {
      return this.field6;
   }

   public static class EventRenderItemColorCancel extends com.moonsworth.lunar.client.event.CancellableEvent {
      public EventRenderItemColorCancel() {
      }
   }
}
