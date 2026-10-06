package com.moonsworth.lunar.client.ui.notification;

import java.net.URI;
import net.kyori.adventure.text.Component;

public class NotificationLink {
   private final String field1;
   private final Component field2;
   private final URI uri;

   public NotificationLink(String text, Component component2, URI uri3) {
      this.field1 = text;
      this.field2 = component2;
      this.uri = uri3;
   }

   public String id() {
      return this.field1;
   }

   public Component method1() {
      return this.field2;
   }
}
