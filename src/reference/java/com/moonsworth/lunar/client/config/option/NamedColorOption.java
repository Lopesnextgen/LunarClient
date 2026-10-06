package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import org.apache.commons.lang3.text.WordUtils;

public enum NamedColorOption implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   OFF,
   DARK_BLUE,
   DARK_GREEN,
   DARK_AQUA,
   DARK_RED,
   DARK_PURPLE,
   GOLD,
   GRAY,
   DARK_GRAY,
   BLUE,
   GREEN,
   AQUA,
   RED,
   LIGHT_PURPLE,
   YELLOW,
   WHITE;

   @Override
   public String toString() {
      return WordUtils.capitalize(this.name().toLowerCase().replace("_", " "));
   }

   public String id() {
      return this.name();
   }

   public AdventureChatFormatting getColor() {
      return this == OFF ? AdventureChatFormatting.RESET : AdventureChatFormatting.valueOf(this.name());
   }
}
