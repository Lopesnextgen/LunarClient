package com.moonsworth.lunar.client.event.mixin.chat;

import lombok.Generated;
import net.kyori.adventure.text.Component;

public class EventComponentMessageLegacy extends com.moonsworth.lunar.client.highlight.HighlightImpl {
   private final Component field1;
   private final String field2;

   @Generated
   public EventComponentMessageLegacy(Component component, String text) {
      this.field1 = component;
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
