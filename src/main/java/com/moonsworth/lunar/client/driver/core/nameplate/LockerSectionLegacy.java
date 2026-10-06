package com.moonsworth.lunar.client.driver.core.nameplate;

import lombok.Generated;

public enum LockerSectionLegacy {
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
   LockerSectionLegacy(String text) {
      this.name = text;
   }
}
