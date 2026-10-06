package com.moonsworth.lunar.client.event.render;

import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ClickTypeBridge;
import javax.annotation.Nullable;
import lombok.Generated;
import com.moonsworth.lunar.client.event.CancellableEvent;

public class EventRenderSlot extends CancellableEvent {
   private final GuiContainerBridge field1;
   @Nullable
   private final SlotBridge field2;
   private int field3;
   private int field4;
   private ClickTypeBridge field5;
   public boolean field6;

   public EventRenderSlot(GuiContainerBridge bridge5extension_31, SlotBridge bridge3_182, int value, int value2, ClickTypeBridge bridgetype_135) {
      this.field1 = bridge5extension_31;
      this.field2 = bridge3_182;
      this.field3 = value;
      this.field4 = value2;
      this.field5 = bridgetype_135;
   }

   public void method1(int number1) {
      this.field3 = number1;
      this.field6 = true;
   }

   public void method2(int number1) {
      this.field4 = number1;
      this.field6 = true;
   }

   public void method3(ClickTypeBridge bridgetype_131) {
      this.field5 = bridgetype_131;
      this.field6 = true;
   }

   @Generated
   public GuiContainerBridge method4() {
      return this.field1;
   }

   @Nullable
   @Generated
   public SlotBridge method5() {
      return this.field2;
   }

   @Generated
   public int getSlotId() {
      return this.field3;
   }

   @Generated
   public int method6() {
      return this.field4;
   }

   @Generated
   public ClickTypeBridge method7() {
      return this.field5;
   }

   @Generated
   public boolean isModified() {
      return this.field6;
   }
}
