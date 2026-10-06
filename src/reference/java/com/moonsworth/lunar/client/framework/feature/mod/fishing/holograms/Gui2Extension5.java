package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import lombok.Generated;

public enum Gui2Extension5 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   NONE("none"),
   SIMPLIFIED("simplified"),
   LEGAL_MAP("legalMap");

   private final String id;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   @Generated
   Gui2Extension5(String text) {
      this.id = text;
   }
}
