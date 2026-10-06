package com.moonsworth.lunar.client.framework.feature.crosshair;

import lombok.Generated;

public enum CrosshairMode implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   SIMPLE("crosshairModeSimple"),
   PRESET("crosshairModePreset"),
   CUSTOM("crosshairModeCustom");

   private final String id;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   CrosshairMode(String text3) {
      this.id = text3;
   }
}
