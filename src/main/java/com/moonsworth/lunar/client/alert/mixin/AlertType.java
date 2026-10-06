package com.moonsworth.lunar.client.alert.mixin;

import lombok.Generated;

public enum AlertType {
   SERVER(false),
   CLIENT_INTERNAL(true),
   CLIENT_REMOTE(true),
   CLIENT_CRITERIA(true),
   CLIENT_OVERRIDE(true);

   private final boolean client;

   @Generated
   AlertType(boolean flag) {
      this.client = flag;
   }

   @Generated
   public boolean isClient() {
      return this.client;
   }
}
