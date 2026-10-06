package com.moonsworth.lunar.client.event.entity;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import lombok.Generated;

public class EventEntityWorldJoin extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   public BridgeExtension field1;
   public Itemcounter6 field2;

   @Generated
   public EventEntityWorldJoin(BridgeExtension bridge, Itemcounter6 itemcounter6) {
      this.field1 = bridge;
      this.field2 = itemcounter6;
   }
}
