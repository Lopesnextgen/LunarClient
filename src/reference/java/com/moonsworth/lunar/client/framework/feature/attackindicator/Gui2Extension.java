package com.moonsworth.lunar.client.framework.feature.attackindicator;

import lombok.Generated;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   VANILLA("vanilla"),
   VANILLA_ICON("vanilla_icon"),
   INDICATOR("indicator"),
   INDICATOR_DOT("indicator_dot"),
   PROGRESS("progress");

   private final String id;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   public boolean isVanilla() {
      return this == VANILLA || this == VANILLA_ICON;
   }

   public boolean isIndicator() {
      return this == INDICATOR || this == INDICATOR_DOT;
   }

   @Generated
   Gui2Extension(String text) {
      this.id = text;
   }
}
