package com.moonsworth.lunar.client.framework.feature.mod.highlight;

import java.util.Locale;
import lombok.Generated;
import org.apache.commons.lang3.text.WordUtils;

public enum SkyblockVisibility implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   ALWAYS("always"),
   SKYBLOCK_ONLY("skyBlockOnly"),
   NEVER("never");

   private final String id;

   public String id() {
      return WordUtils.capitalize(this.name().toLowerCase(Locale.ROOT).replace("_", " "));
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   SkyblockVisibility(String text3) {
      this.id = text3;
   }
}
