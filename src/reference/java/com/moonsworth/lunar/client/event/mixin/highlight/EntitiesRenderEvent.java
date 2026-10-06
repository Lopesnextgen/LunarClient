package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import lombok.Generated;

public class EntitiesRenderEvent extends com.moonsworth.lunar.client.highlight.Highlight {
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
   public EntitiesRenderEvent(AbstractRenderContext abstractRenderContext, double value, double value2, double value3) {
      this.field1 = abstractRenderContext;
      this.field2 = value;
      this.field3 = value2;
      this.field4 = value3;
   }
}
