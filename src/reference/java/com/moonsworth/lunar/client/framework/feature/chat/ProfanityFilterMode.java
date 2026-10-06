package com.moonsworth.lunar.client.framework.feature.chat;

import lombok.Generated;
import org.apache.commons.lang3.text.WordUtils;

public enum ProfanityFilterMode implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   CUSTOM("custom"),
   OFF("off"),
   NORMAL("normal"),
   HIGH("high");

   private final String id;

   public String id() {
      return WordUtils.capitalize(this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]));
   }

   @Generated
   ProfanityFilterMode(String text3) {
      this.id = text3;
   }
}
