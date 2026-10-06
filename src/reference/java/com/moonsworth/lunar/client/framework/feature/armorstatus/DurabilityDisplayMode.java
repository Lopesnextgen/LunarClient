package com.moonsworth.lunar.client.framework.feature.armorstatus;

import lombok.Generated;

public enum DurabilityDisplayMode implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   VALUE("value"),
   PERCENT("percent"),
   NONE("none");

   private final String id;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   DurabilityDisplayMode(String text3) {
      this.id = text3;
   }
}
