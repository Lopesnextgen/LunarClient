package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.BlockStateBridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import lombok.Generated;

public class EventBlockPlace extends com.moonsworth.lunar.client.event.CancellableEvent {
   private Bridge3_23 field1;
   private BlockStateBridge field2;
   private Vec3iBridge field3;

   @Generated
   public EventBlockPlace(Bridge3_23 bridge3_231, BlockStateBridge bridge2_172, Vec3iBridge horsestats203) {
      this.field1 = bridge3_231;
      this.field2 = bridge2_172;
      this.field3 = horsestats203;
   }

   @Generated
   public Bridge3_23 getBlock() {
      return this.field1;
   }

   @Generated
   public BlockStateBridge method1() {
      return this.field2;
   }

   @Generated
   public Vec3iBridge method2() {
      return this.field3;
   }
}
