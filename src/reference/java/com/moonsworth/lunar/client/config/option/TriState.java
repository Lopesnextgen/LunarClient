package com.moonsworth.lunar.client.config.option;

import lombok.Generated;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;

public enum TriState implements OptionEnumValue {
   DISABLED("disabled"),
   FORCE_ON("forceOn"),
   FORCE_OFF("forceOff");

   private final String id;

   @Override
   public String id() {
      return this.id;
   }

   @Generated
   TriState(String text) {
      this.id = text;
   }
}
