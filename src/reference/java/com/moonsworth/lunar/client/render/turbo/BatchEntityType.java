package com.moonsworth.lunar.client.render.turbo;

import lombok.Generated;

public enum BatchEntityType {
   ENTITIES("Entity"),
   ENTITIES("BlockEntity");

   private final String langKey;

   @Generated
   public String getLangKey() {
      return this.langKey;
   }

   @Generated
   BatchEntityType(String var3) {
      this.langKey = var3;
   }
}
