package com.moonsworth.lunar.client.event.mixin.gui;

import lombok.Generated;

public class EventScreenAction extends com.moonsworth.lunar.client.event.CancellableEvent {
   private final Runnable field1;

   public void method1() {
      this.field1.run();
   }

   @Generated
   public EventScreenAction(Runnable runnable1) {
      this.field1 = runnable1;
   }
}
