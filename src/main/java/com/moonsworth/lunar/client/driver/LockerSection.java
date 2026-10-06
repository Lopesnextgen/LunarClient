package com.moonsworth.lunar.client.driver;

import lombok.Generated;

public enum LockerSection {
   COSMETICS("COSMETICS"),
   EMOTES("EMOTES"),
   OUTFITS("OUTFITS"),
   SPRAYS("SPRAYS"),
   LUNARPLUS("LUNARPLUS"),
   BADGES("BADGES"),
   SKIN_CHANGER("SKIN_CHANGER");

   final String name;

   @Override
   public String toString() {
      return this.name;
   }

   @Generated
   LockerSection(String text) {
      this.name = text;
   }
}
