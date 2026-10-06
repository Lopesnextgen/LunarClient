package com.moonsworth.lunar.client.config.option;

import lombok.Generated;

public enum OptionFlag {
   ADVANCED("ADVANCED"),
   DUPLICATE_KEY("DUPLICATE_KEY");

   final String id;

   @Override
   public String toString() {
      return this.id;
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   OptionFlag(String text) {
      this.id = text;
   }
}
