package com.moonsworth.lunar.client.driver.core;

import lombok.Generated;

public enum DriverFieldTypeLegacy {
   BUTTON("button"),
   RANGE("range"),
   SLIDER("slider"),
   CHECKBOX("checkbox"),
   TOGGLE("toggle"),
   DATE("date"),
   PASSWORD("password"),
   RADIO("radio"),
   TEXT("text"),
   TEXT_AREA("textarea"),
   PLAYERS("players"),
   COLOR_PICKER("color_picker"),
   DROPDOWN("dropdown"),
   KEYBIND("keybind"),
   MULTI_NUMBER("multi_number"),
   MODIFIABLE_LIST("modifiable_list"),
   PACKS("packs"),
   INVENTORY_SLOTS("inventory_slots"),
   CATEGORY("category"),
   CROSSHAIR_PREVIEW("crosshair_preview"),
   CROSSHAIR_PRESET_LIST("crosshair_preset_list"),
   CROSSHAIR_DRAW("crosshair_draw"),
   UNKNOWN("unknown");

   final String id;

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   DriverFieldTypeLegacy(String text) {
      this.id = text;
   }
}
