package com.moonsworth.lunar.client.event.mixin.highlight;

import com.moonsworth.lunar.bridge.Bridge11_5;
import lombok.Generated;

public class EventRenderGuardian extends com.moonsworth.lunar.client.event.CancellableEvent {
   private final Bridge11_5 field1;

   @Generated
   public Bridge11_5 method1() {
      return this.field1;
   }

   @Generated
   public EventRenderGuardian(Bridge11_5 bridge11_51) {
      this.field1 = bridge11_51;
   }
}
