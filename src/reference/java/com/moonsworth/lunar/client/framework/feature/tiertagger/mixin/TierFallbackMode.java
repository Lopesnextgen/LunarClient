package com.moonsworth.lunar.client.framework.feature.tiertagger.mixin;

import lombok.Generated;

public enum TierFallbackMode implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   SELECTED_ONLY("selectedOnly"),
   HIGHEST_FALLBACK("highestFallback"),
   HIGHEST_ALWAYS("highestAlways");

   private final String id;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   TierFallbackMode(String text3) {
      this.id = text3;
   }
}
