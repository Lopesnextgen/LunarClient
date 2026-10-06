package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import lombok.Generated;

public class EventRenderEntities extends com.moonsworth.lunar.client.event.LunarEvent {
   private final AbstractRenderContext field1;
   private final double field2;
   private final double field3;
   private final double field4;

   @Generated
   public AbstractRenderContext method1() {
      return this.field1;
   }

   @Generated
   public double getX() {
      return this.field2;
   }

   @Generated
   public double getY() {
      return this.field3;
   }

   @Generated
   public double getZ() {
      return this.field4;
   }

   @Generated
   public EventRenderEntities(AbstractRenderContext bridgeextension_91, double value, double value2, double value3) {
      this.field1 = bridgeextension_91;
      this.field2 = value;
      this.field3 = value2;
      this.field4 = value3;
   }
}
