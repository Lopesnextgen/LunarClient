package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import lombok.Generated;

public abstract class EventBlockUpdate extends com.moonsworth.lunar.client.event.CancellableEvent {
   private final Vec3iBridge field1;
   private final Bridge3_23 field2;

   @Generated
   public Vec3iBridge method1() {
      return this.field1;
   }

   @Generated
   public Bridge3_23 getBlock() {
      return this.field2;
   }

   @Generated
   public EventBlockUpdate(Vec3iBridge horsestats201, Bridge3_23 bridge3_232) {
      this.field1 = horsestats201;
      this.field2 = bridge3_232;
   }

   public static class BlockUpdate extends EventBlockUpdate {
      public BlockUpdate(Vec3iBridge horsestats201, Bridge3_23 bridge3_232) {
         super(horsestats201, bridge3_232);
      }
   }
}
