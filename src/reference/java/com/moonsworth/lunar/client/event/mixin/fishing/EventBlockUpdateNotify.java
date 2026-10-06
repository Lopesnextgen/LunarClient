package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import lombok.Generated;

public abstract class EventBlockUpdateNotify extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private final Vector3iBridge field1;
   private final Bridge3_23 field2;

   @Generated
   public Vector3iBridge method1() {
      return this.field1;
   }

   @Generated
   public Bridge3_23 getBlock() {
      return this.field2;
   }

   @Generated
   public EventBlockUpdateNotify(Vector3iBridge var1, Bridge3_23 var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   public static class Data extends EventBlockUpdateNotify {
      public Data(Vector3iBridge var1, Bridge3_23 var2) {
         super(var1, var2);
      }
   }
}
