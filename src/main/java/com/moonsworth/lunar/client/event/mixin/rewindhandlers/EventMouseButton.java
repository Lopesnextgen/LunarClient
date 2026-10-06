package com.moonsworth.lunar.client.event.mixin.rewindhandlers;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.event.input.InputAction;
import lombok.Generated;

public class EventMouseButton extends com.moonsworth.lunar.client.event.CancellableEvent {
   private final int field1;
   private final int field2;
   private final InputAction field3;

   public KeyCode method1() {
      return KeyCode.fromMouseButton(this.field1);
   }

   @Generated
   public int method2() {
      return this.field1;
   }

   @Generated
   public int method3() {
      return this.field2;
   }

   @Generated
   public InputAction method4() {
      return this.field3;
   }

   @Generated
   public EventMouseButton(int value, int value2, InputAction inputAction) {
      this.field1 = value;
      this.field2 = value2;
      this.field3 = inputAction;
   }
}
