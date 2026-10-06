package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter_4;
import com.moonsworth.lunar.ichor.VersionGate;
import lombok.Generated;

public abstract class EventRenderBlockOutline extends com.moonsworth.lunar.client.event.CancellableEvent {
   private final AbstractRenderContext field1;

   @Generated
   public AbstractRenderContext method1() {
      return this.field1;
   }

   @Generated
   public EventRenderBlockOutline(AbstractRenderContext bridgeextension_91) {
      this.field1 = bridgeextension_91;
   }

   public static class EventRenderBlockOutlineModern extends EventRenderBlockOutline {
      private final Bridge5_16 field2;
      private final Itemcounter_4 field3;

      @VersionGate(min = 6)
      public EventRenderBlockOutlineModern(Bridge5_16 bridge5_161, AbstractRenderContext bridgeextension_92, Itemcounter_4 itemcounter_43) {
         super(bridgeextension_92);
         this.field2 = bridge5_161;
         this.field3 = itemcounter_43;
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

   public static class EventRenderBlockOutlineLegacy extends EventRenderBlockOutline {
      private final AxisAlignedBBBridge field2;

      @VersionGate(max = 5)
      public EventRenderBlockOutlineLegacy(AbstractRenderContext bridgeextension_91, AxisAlignedBBBridge horsestats122) {
         super(bridgeextension_91);
         this.field2 = horsestats122;
      }

      @Generated
      public AxisAlignedBBBridge method2() {
         return this.field2;
      }
   }
}
