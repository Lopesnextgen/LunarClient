package com.moonsworth.lunar.client.framework.feature.blockoutline;

import lombok.Generated;

public enum BlockOutlineMode implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   STATIC("outlineModeStatic"),
   RAINBOW("outlineModeRainbow"),
   BLEND("outlineModeBlend");

   private final String id;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   BlockOutlineMode(String text3) {
      this.id = text3;
   }
}
