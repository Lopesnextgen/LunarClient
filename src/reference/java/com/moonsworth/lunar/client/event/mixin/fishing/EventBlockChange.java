package com.moonsworth.lunar.client.event.mixin.fishing;

import com.moonsworth.lunar.bridge.BlockStateBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventBlockChange extends LunarEvent {
   private Vec3iBridge field1;
   private BlockStateBridge field2;
   private BlockStateBridge field3;

   @Generated
   public Vec3iBridge method1() {
      return this.field1;
   }

   @Generated
   public BlockStateBridge method2() {
      return this.field2;
   }

   @Generated
   public BlockStateBridge method3() {
      return this.field3;
   }

   @Generated
   public EventBlockChange(Vec3iBridge horsestats201, BlockStateBridge bridge2_172, BlockStateBridge bridge2_173) {
      this.field1 = horsestats201;
      this.field2 = bridge2_172;
      this.field3 = bridge2_173;
   }
}
