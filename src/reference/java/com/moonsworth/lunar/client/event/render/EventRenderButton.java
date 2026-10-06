package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.GuiButtonBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import lombok.Generated;
import com.moonsworth.lunar.client.event.CancellableEvent;

public class EventRenderButton extends CancellableEvent {
   private final GuiScreenBridge field1;
   private final GuiButtonBridge field2;

   @Generated
   public GuiScreenBridge method1() {
      return this.field1;
   }

   @Generated
   public GuiButtonBridge method2() {
      return this.field2;
   }

   @Generated
   public EventRenderButton(GuiScreenBridge guiScreenBridge, GuiButtonBridge bridge2_202) {
      this.field1 = guiScreenBridge;
      this.field2 = bridge2_202;
   }
}
