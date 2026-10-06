package com.moonsworth.lunar.client.framework.feature.mod.fishing.highlight.mixin;

import lombok.Generated;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   ABOVE("above"),
   LEFT("left"),
   RIGHT("right"),
   BELOW("below");

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
