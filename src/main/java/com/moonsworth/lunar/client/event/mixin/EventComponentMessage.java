package com.moonsworth.lunar.client.event.mixin;

import lombok.Generated;
import net.kyori.adventure.text.Component;

public class EventComponentMessage extends com.moonsworth.lunar.client.event.CancellableEvent {
   private final Component field1;
   private final String field2;

   @Generated
   public EventComponentMessage(Component component1, String text) {
      this.field1 = component1;
      this.field2 = text;
   }

   @Generated
   public Component method1() {
      return this.field1;
   }

   @Generated
   public String method2() {
      return this.field2;
   }
}
