package com.moonsworth.lunar.client.framework.feature.tiertagger.mixin;

import lombok.Generated;

public enum Gui2Extension2 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   SELECTED_ONLY("selectedOnly"),
   HIGHEST_FALLBACK("highestFallback"),
   HIGHEST_ALWAYS("highestAlways");

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
}
