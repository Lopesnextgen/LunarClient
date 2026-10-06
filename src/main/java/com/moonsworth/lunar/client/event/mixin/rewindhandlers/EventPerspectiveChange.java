package com.moonsworth.lunar.client.event.mixin.rewindhandlers;

import lombok.Generated;

public class EventPerspectiveChange extends com.moonsworth.lunar.client.event.CancellableEvent {
   private int field1;

   public int method1() {
      int number1 = this.field1 + 1;
      if (number1 > 2) {
         number1 = 0;
      }

      return number1;
   }

   @Generated
   public EventPerspectiveChange(int number1) {
      this.field1 = number1;
   }

   @Generated
   public EventPerspectiveChange() {
   }

   @Generated
   public int method2() {
      return this.field1;
   }
}
