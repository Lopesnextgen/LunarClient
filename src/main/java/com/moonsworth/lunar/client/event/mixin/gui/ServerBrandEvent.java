package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class ServerBrandEvent extends Highlight {
   private final String field1;

   @Generated
   public ServerBrandEvent(String text) {
      this.field1 = text;
   }

   @Generated
   public String method1() {
      return this.field1;
   }
}
