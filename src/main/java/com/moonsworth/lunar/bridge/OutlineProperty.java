package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum OutlineProperty {
   NONE("none"),
   IS_OUTLINE("is_outline"),
   AFFECTS_OUTLINE("affects_outline");

   private final String name;

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   OutlineProperty(String text) {
      this.name = text;
   }
}
