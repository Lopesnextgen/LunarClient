package com.moonsworth.lunar.client.framework.feature.armorstatus.mixin;

import lombok.Generated;

public enum Gui2Extension4 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   VALUE("value"),
   PERCENT("percent"),
   NONE("none");

   private final String id;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   @Generated
   Gui2Extension4(String text) {
      this.id = text;
   }
}
