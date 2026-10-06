package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public abstract class EventRenderTickPhase extends Highlight {
   private final float field1;

   @Generated
   public float method1() {
      return this.field1;
   }

   @Generated
   private EventRenderTickPhase(float var1) {
      this.field1 = var1;
   }

   public static class EventRenderTickBegin extends EventRenderTickPhase {
      public EventRenderTickBegin(float var1) {
         super(var1);
      }
   }

   public static class EventRenderTickFinish extends EventRenderTickPhase {
      public EventRenderTickFinish(float var1) {
         super(var1);
      }
   }
}
