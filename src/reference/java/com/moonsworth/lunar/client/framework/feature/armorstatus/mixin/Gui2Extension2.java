package com.moonsworth.lunar.client.framework.feature.armorstatus.mixin;

import lombok.Generated;

public enum Gui2Extension2 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   TOP("top"),
   BOTTOM("bottom"),
   RIGHT("right"),
   LEFT("left");

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
