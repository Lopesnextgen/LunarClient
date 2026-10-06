package com.moonsworth.lunar.client.config.override;

import lombok.Generated;

public enum OverrideSource {
   SERVER(false),
   CLIENT_INTERNAL(true),
   CLIENT_REMOTE(true),
   CLIENT_CRITERIA(true),
   CLIENT_OVERRIDE(true);

   private final boolean client;

   @Generated
   OverrideSource(boolean flag) {
      this.client = flag;
   }

   @Generated
   public boolean isClient() {
      return this.client;
   }
}
