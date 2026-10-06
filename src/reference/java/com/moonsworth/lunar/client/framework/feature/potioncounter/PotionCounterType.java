package com.moonsworth.lunar.client.framework.feature.potioncounter;

import lombok.Generated;

public enum PotionCounterType implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   POTION("potion"),
   SOUP("soup");

   private final String id;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   PotionCounterType(String text3) {
      this.id = text3;
   }
}
