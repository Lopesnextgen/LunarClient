package com.moonsworth.lunar.client.network.websocket;

import lombok.Generated;

public enum ConnectionState {
   DISCONNECTED("disconnected"),
   READY("ready");

   private final String id;

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   ConnectionState(String text) {
      this.id = text;
   }
}
