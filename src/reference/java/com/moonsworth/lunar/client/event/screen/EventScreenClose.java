package com.moonsworth.lunar.client.event.screen;

import com.moonsworth.lunar.bridge.Bridge7_8;
import lombok.Generated;
import com.moonsworth.lunar.client.event.CancellableEvent;

public class EventScreenClose extends CancellableEvent {
   private final Bridge7_8 field1;

   @Generated
   public EventScreenClose(Bridge7_8 bridge7_81) {
      this.field1 = bridge7_81;
   }

   @Generated
   public Bridge7_8 method1() {
      return this.field1;
   }
}
