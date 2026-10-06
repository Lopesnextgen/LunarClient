package com.moonsworth.lunar.client.ui.hud;

import lombok.Generated;

public enum HudPlacement implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   TOP("top"),
   BOTTOM("bottom"),
   MIDDLE("middle"),
   LEFT("left"),
   RIGHT("right");

   private final String id;

   @Override
   public String id() {
      return this.id;
   }

   @Generated
   HudPlacement(String text) {
      this.id = text;
   }
}
