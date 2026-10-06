package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import lombok.Generated;

public enum Gui2Extension7 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   OFF("off"),
   ICONS("icons"),
   SELF_ICON("selfIcon"),
   HEADS("heads");

   private final String id;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   @Generated
   Gui2Extension7(String text) {
      this.id = text;
   }
}
