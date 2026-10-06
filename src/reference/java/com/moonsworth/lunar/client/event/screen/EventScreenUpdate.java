package com.moonsworth.lunar.client.event.screen;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import lombok.Generated;
import com.moonsworth.lunar.client.event.LunarEvent;

public class EventScreenUpdate extends LunarEvent {
   private GuiScreenBridge field1;

   @Generated
   public EventScreenUpdate(GuiScreenBridge bridge5extension61) {
      this.field1 = bridge5extension61;
   }

   @Generated
   public GuiScreenBridge method1() {
      return this.field1;
   }

   @Generated
   public void method2(GuiScreenBridge bridge5extension61) {
      this.field1 = bridge5extension61;
   }
}
