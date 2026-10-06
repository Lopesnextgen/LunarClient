package com.moonsworth.lunar.client.event.entity;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import java.util.List;
import lombok.Generated;

public class EventEntityCollisionBoxes extends com.moonsworth.lunar.client.event.CancellableEvent {
   private final BridgeExtension field1;
   private final AxisAlignedBBBridge field2;
   private final List<AxisAlignedBBBridge> field3;

   @Generated
   public BridgeExtension method1() {
      return this.field1;
   }

   @Generated
   public AxisAlignedBBBridge method2() {
      return this.field2;
   }

   @Generated
   public List<AxisAlignedBBBridge> method3() {
      return this.field3;
   }

   @Generated
   public EventEntityCollisionBoxes(BridgeExtension bridge, AxisAlignedBBBridge horsestats122, List<AxisAlignedBBBridge> list) {
      this.field1 = bridge;
      this.field2 = horsestats122;
      this.field3 = list;
   }
}
