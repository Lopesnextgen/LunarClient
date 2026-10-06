package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import lombok.Generated;
import com.moonsworth.lunar.client.event.LunarEvent;

public class EventRenderScreenItem extends LunarEvent {
   private final GuiContainerBridge field1;
   private final SlotBridge field2;
   private ItemStackBridge field3;

   @Generated
   public GuiContainerBridge method1() {
      return this.field1;
   }

   @Generated
   public SlotBridge method2() {
      return this.field2;
   }

   @Generated
   public ItemStackBridge method3() {
      return this.field3;
   }

   @Generated
   public EventRenderScreenItem(GuiContainerBridge bridge5extension_31, SlotBridge bridge3_182) {
      this.field1 = bridge5extension_31;
      this.field2 = bridge3_182;
   }

   @Generated
   public void method4(ItemStackBridge bridgeextension_41) {
      this.field3 = bridgeextension_41;
   }
}
