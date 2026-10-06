package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import lombok.Generated;

public enum BettermapVariant {
   PRIMARY(0, "primary"),
   SECONDARY(2, "secondary"),
   SPIRIT_LEAP(1, "spiritLeap");

   private final int id;
   private final String optionPrefix;

   @Generated
   BettermapVariant(int value, String text) {
      this.id = value;
      this.optionPrefix = text;
   }

   @Generated
   public int getId() {
      return this.id;
   }

   @Generated
   public String getOptionPrefix() {
      return this.optionPrefix;
   }
}
