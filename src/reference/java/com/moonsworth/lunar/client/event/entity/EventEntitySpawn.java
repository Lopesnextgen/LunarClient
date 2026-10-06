package com.moonsworth.lunar.client.event.entity;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import lombok.Generated;

public class EventEntitySpawn extends com.moonsworth.lunar.client.event.CancellableEvent {
   public BridgeExtension field1;
   public Itemcounter6 field2;

   @Generated
   public EventEntitySpawn(BridgeExtension bridge, Itemcounter6 itemcounter62) {
      this.field1 = bridge;
      this.field2 = itemcounter62;
   }
}
