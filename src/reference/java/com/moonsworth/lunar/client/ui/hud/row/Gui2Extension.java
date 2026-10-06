package com.moonsworth.lunar.client.ui.hud.row;

import lombok.Generated;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   LEFT("alignLeft"),
   CENTER("alignCenter"),
   RIGHT("alignRight");

   private final String id;

   @Override
   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   @Generated
   Gui2Extension(String text) {
      this.id = text;
   }
}
