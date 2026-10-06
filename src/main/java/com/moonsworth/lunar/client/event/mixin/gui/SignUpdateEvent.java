package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class SignUpdateEvent extends Highlight {
   private String[] field1;

   @Generated
   public SignUpdateEvent(String[] items) {
      this.field1 = items;
   }

   @Generated
   public String[] method1() {
      return this.field1;
   }
}
