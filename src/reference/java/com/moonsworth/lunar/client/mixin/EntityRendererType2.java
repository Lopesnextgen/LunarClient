package com.moonsworth.lunar.client.mixin;

import lombok.Generated;

public enum EntityRendererType2 {
   READY("disconnected"),
   READY("ready");

   private final String id;

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   EntityRendererType2(String var3) {
      this.id = var3;
   }
}
