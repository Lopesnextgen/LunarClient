package com.moonsworth.lunar.client.framework.feature.chat;

import lombok.Generated;
import org.apache.commons.lang3.text.WordUtils;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   CUSTOM("custom"),
   OFF("off"),
   NORMAL("normal"),
   HIGH("high");

   private final String id;

   public String id() {
      return WordUtils.capitalize(this.method1(this.id, new Object[0]));
   }

   @Generated
   Gui2Extension(String text) {
      this.id = text;
   }
}
