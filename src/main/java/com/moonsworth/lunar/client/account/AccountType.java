package com.moonsworth.lunar.client.account;

import com.lunarclient.websocket.handshake.v1.MinecraftIdentity.Type;
import lombok.Generated;

public enum AccountType {
   @Deprecated
   MOJANG("Mojang", Type.TYPE_MOJANG),
   XBOX("Xbox", Type.TYPE_MICROSOFT);

   private final String formatted;
   private final Type protobufType;

   @Generated
   AccountType(String text, Type type) {
      this.formatted = text;
      this.protobufType = type;
   }

   @Generated
   public String getFormatted() {
      return this.formatted;
   }

   @Generated
   public Type getProtobufType() {
      return this.protobufType;
   }
}
