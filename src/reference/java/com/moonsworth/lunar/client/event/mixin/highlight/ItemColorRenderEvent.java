package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.ichor.Annotation2;
import lombok.Generated;

@Annotation2(1)
public class ItemColorRenderEvent extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private final float field1;
   private final float field2;
   private final ItemStackBridge field3;
   private final AbstractRenderContext field4;
   private final Runnable field5;
   private final Runnable field6;

   @Generated
   public ItemColorRenderEvent(float value, float value2, ItemStackBridge itemStackBridge, AbstractRenderContext abstractRenderContext, Runnable runnable, Runnable runnable2) {
      this.field1 = value;
      this.field2 = value2;
      this.field3 = itemStackBridge;
      this.field4 = abstractRenderContext;
      this.field5 = runnable;
      this.field6 = runnable2;
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

   public static class ItemColorCancelEvent extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   }
}
