package com.moonsworth.lunar.client.ui.hud;

import lombok.Generated;

public enum HudAlignment implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   TOP("top"),
   BOTTOM("bottom"),
   MIDDLE("middle"),
   LEFT("left"),
   RIGHT("right");

   private final String id;

   public String id() {
      return this.id;
   }

   @Generated
   HudAlignment(String text) {
      this.id = text;
   }
}
