package com.moonsworth.lunar.client.framework.feature.crosshair;

import lombok.Generated;

public enum CrosshairShape implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
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
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   CrosshairShape(String text3) {
      this.id = text3;
   }
}
