package com.moonsworth.lunar.client.event.mixin.gui;

import lombok.Generated;
import net.kyori.adventure.text.Component;

public class EventTitle extends com.moonsworth.lunar.client.event.CancellableEvent {
   private final Component title;
   private final EventTitle.TitleSource field1;
   private boolean field2;

   @Generated
   public EventTitle(Component component1, EventTitle.TitleSource type2, boolean flag) {
      this.title = component1;
      this.field1 = type2;
      this.field2 = flag;
   }

   @Generated
   public EventTitle(Component component1, EventTitle.TitleSource type2) {
      this.title = component1;
      this.field1 = type2;
   }

   @Generated
   public Component getTitle() {
      return this.title;
   }

   @Generated
   public EventTitle.TitleSource method2() {
      return this.field1;
   }

   @Generated
   public boolean method3() {
      return this.field2;
   }

   public enum TitleSource {
      SERVER,
      APOLLO;

      TitleSource() {
      }
   }
}
