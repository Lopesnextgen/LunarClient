package com.moonsworth.lunar.client.config.option;

import lombok.Generated;

public enum AdvancedOptionFlag {
   ADVANCED("ADVANCED"),
   ADVANCED("ADVANCED");

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
   AdvancedOptionFlag(String var3) {
      this.id = var3;
   }
}
