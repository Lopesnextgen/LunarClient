package com.moonsworth.lunar.client.config.option;

import lombok.Generated;

public enum OverrideTriState implements OptionEnumValue {
   DISABLED("disabled"),
   FORCE_ON("forceOn"),
   FORCE_OFF("forceOff");

   private final String id;

   @Override
   public String id() {
      return this.id;
   }

   @Generated
   OverrideTriState(String text) {
      this.id = text;
   }
}
