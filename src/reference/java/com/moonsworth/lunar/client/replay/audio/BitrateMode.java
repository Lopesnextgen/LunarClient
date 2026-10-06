package com.moonsworth.lunar.client.replay.audio;

import lombok.Generated;

public enum BitrateMode implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   AUTO_QUALITY("autoQuality"),
   AUTO_SIZE("autoSize"),
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
   BitrateMode(String text3) {
      this.id = text3;
   }
}
