package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.fishing;

import lombok.Generated;

public enum Gui2Extension2 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   HORIZONTAL("horizontal"),
   VERTICAL("vertical");

   private final String id;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   @Generated
   Gui2Extension2(String text) {
      this.id = text;
   }

   @Generated
   public String getId() {
      return this.id;
   }
}
