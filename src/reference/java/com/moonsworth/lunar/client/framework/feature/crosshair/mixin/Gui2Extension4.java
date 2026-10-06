package com.moonsworth.lunar.client.framework.feature.crosshair.mixin;

import lombok.Generated;

public enum Gui2Extension4 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   CROSS("cross"),
   CIRCLE("circle"),
   ARROW("arrow"),
   TRIANGLE("triangle"),
   SQUARE("square"),
   DOT("dot"),
   CIRCLE_DOT("circleDot"),
   X("x");

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
