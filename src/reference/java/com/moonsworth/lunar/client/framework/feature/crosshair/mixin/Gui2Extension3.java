package com.moonsworth.lunar.client.framework.feature.crosshair.mixin;

import lombok.Generated;

public enum Gui2Extension3 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   SMALL("small", 1),
   NORMAL("normal", 2),
   LARGE("large", 3),
   AUTO("auto", 4);

   private final String id;
   private final int scale;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   @Generated
   Gui2Extension3(String text, int value) {
      this.id = text;
      this.scale = value;
   }

   @Generated
   public int getScale() {
      return this.scale;
   }
}
