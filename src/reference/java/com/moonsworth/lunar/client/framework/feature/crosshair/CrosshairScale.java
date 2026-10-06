package com.moonsworth.lunar.client.framework.feature.crosshair;

import lombok.Generated;

public enum CrosshairScale implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
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
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   CrosshairScale(String text3, int number4) {
      this.id = text3;
      this.scale = number4;
   }

   @Generated
   public int getScale() {
      return this.scale;
   }
}
