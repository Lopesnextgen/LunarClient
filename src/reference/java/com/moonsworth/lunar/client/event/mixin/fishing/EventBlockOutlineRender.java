package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_4;
import com.moonsworth.lunar.ichor.Annotation2;
import lombok.Generated;

public abstract class EventBlockOutlineRender extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private final AbstractRenderContext field1;

   @Generated
   public AbstractRenderContext method1() {
      return this.field1;
   }

   @Generated
   public EventBlockOutlineRender(AbstractRenderContext var1) {
      this.field1 = var1;
   }

   public static class EventBlockOutlineRenderModern extends EventBlockOutlineRender {
      private final Bridge5_16 field2;
      private final Itemcounter_4 field3;

      @Annotation2(min = 6)
      public EventBlockOutlineRenderModern(Bridge5_16 var1, AbstractRenderContext var2, Itemcounter_4 itemcounter_4) {
         super(var2);
         this.field2 = var1;
         this.field3 = itemcounter_4;
      }

      @Generated
      public Bridge5_16 method2() {
         return this.field2;
      }

      @Generated
      public Itemcounter_4 method3() {
         return this.field3;
      }
   }

   public static class EventBlockOutlineRenderLegacy extends EventBlockOutlineRender {
      private final AxisAlignedBBBridge field2;

      @Annotation2(max = 5)
      public EventBlockOutlineRenderLegacy(AbstractRenderContext var1, AxisAlignedBBBridge var2) {
         super(var1);
         this.field2 = var2;
      }

      @Generated
      public AxisAlignedBBBridge method2() {
         return this.field2;
      }
   }
}
