package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import lombok.Generated;

public enum MapStyle implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   LEGAL_MAP("legalMap"),
   HYPIXEL("hypixel"),
   CUSTOM("custom");

   private final String id;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   MapStyle(String text3) {
      this.id = text3;
   }
}
