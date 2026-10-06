package com.moonsworth.lunar.client.event.screen;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import lombok.Generated;
import com.moonsworth.lunar.client.event.LunarEvent;

public class EventScreenOpen extends LunarEvent {
   private GuiScreenBridge field1;
   private GuiScreenBridge field2;

   @Generated
   public GuiScreenBridge method1() {
      return this.field1;
   }

   @Generated
   public GuiScreenBridge method2() {
      return this.field2;
   }

   @Generated
   public void method3(GuiScreenBridge bridge5extension61) {
      this.field1 = bridge5extension61;
   }

   @Generated
   public void method4(GuiScreenBridge bridge5extension61) {
      this.field2 = bridge5extension61;
   }

   @Generated
   public EventScreenOpen(GuiScreenBridge bridge5extension61, GuiScreenBridge guiScreenBridge) {
      this.field1 = bridge5extension61;
      this.field2 = guiScreenBridge;
   }
}
