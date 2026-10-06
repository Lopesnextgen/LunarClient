package com.moonsworth.lunar.client.driver.core.holograms;

import lombok.Generated;

public enum HologramSubjectLegacy {
   PLAYER("PLAYER"),
   COSMETIC("COSMETIC");

   final String id;

   @Override
   public String toString() {
      return this.id;
   }

   @Generated
   HologramSubjectLegacy(String text) {
      this.id = text;
   }
}
