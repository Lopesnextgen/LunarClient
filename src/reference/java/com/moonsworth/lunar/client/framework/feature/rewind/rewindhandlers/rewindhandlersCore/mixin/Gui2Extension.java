package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.mixin;

import lombok.Generated;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   POSITION("positionOnly"),
   POS_ROT_BODY("posRotBody"),
   POS_ROT_HEAD("posRotHead");

   private final String id;

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.method1(this.id, new Object[0]);
   }

   @Generated
   Gui2Extension(String text) {
      this.id = text;
   }
}
