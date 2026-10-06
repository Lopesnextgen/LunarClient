package com.moonsworth.lunar.client.event.screen;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import lombok.Generated;
import com.moonsworth.lunar.client.event.CancellableEvent;

public class EventScreenChange extends CancellableEvent {
   private GuiScreenBridge field1;

   @Generated
   public GuiScreenBridge method1() {
      return this.field1;
   }

   @Generated
   public EventScreenChange(GuiScreenBridge guiScreenBridge) {
      this.field1 = guiScreenBridge;
   }
}
