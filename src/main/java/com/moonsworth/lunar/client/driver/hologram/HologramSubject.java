package com.moonsworth.lunar.client.driver.hologram;

import lombok.Generated;

public enum HologramSubject {
   PLAYER("PLAYER"),
   COSMETIC("COSMETIC");

   final String id;

   @Override
   public String toString() {
      return this.id;
   }

   @Generated
   HologramSubject(String text) {
      this.id = text;
   }
}
