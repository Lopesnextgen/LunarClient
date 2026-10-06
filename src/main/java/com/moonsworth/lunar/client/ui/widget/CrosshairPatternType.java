package com.moonsworth.lunar.client.ui.widget;

import lombok.Generated;

enum CrosshairPatternType {
   NONE("none"),
   HORIZONTAL("horizontal"),
   VERTICAL("vertical"),
   QUADRANT("quadrant");

   private final String id;

   @Generated
   CrosshairPatternType(String text) {
      this.id = text;
   }
}
