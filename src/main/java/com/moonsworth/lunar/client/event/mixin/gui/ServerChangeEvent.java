package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class ServerChangeEvent extends Highlight {
   private final boolean field1;

   @Generated
   public boolean method1() {
      return this.field1;
   }

   @Generated
   public ServerChangeEvent(boolean flag) {
      this.field1 = flag;
   }
}
