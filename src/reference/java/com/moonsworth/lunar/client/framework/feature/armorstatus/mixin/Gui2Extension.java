package com.moonsworth.lunar.client.framework.feature.armorstatus.mixin;

import lombok.Generated;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   VERTICAL("vertical"),
   HORIZONTAL("horizontal");

   private final String id;

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
