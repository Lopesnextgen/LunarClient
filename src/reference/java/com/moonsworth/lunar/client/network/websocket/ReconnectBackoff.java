package com.moonsworth.lunar.client.network.websocket;

import com.google.gson.JsonObject;
import com.lunarclient.websocket.protocol.v1.WebSocketRichClosePush;
import com.moonsworth.lunar.client.account.AuthUtil;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public final class ReconnectBackoff {
   private static final long field1 = 15000L;
   private static final long field2 = 1000L;
   private final long field3;
   private long field4;
   @Nullable
   private String field5;
   private long field6;

   public ReconnectBackoff(@Nullable WebSocketRichClosePush websocketrichclosepush1, long number2) {
      if (websocketrichclosepush1 != null) {
         this.field4 = switch (websocketrichclosepush1.getReconnectDelay()) {
            case RECONNECT_DELAY_UNSPECIFIED, UNRECOGNIZED, RECONNECT_DELAY_NORMAL -> 15000L;
            case RECONNECT_DELAY_IMMEDIATE -> 1000L;
            default -> throw new IncompatibleClassChangeError();
         };
         if (!websocketrichclosepush1.getReconnectAuthenticatorJwt().isEmpty()) {
            this.field5 = websocketrichclosepush1.getReconnectAuthenticatorJwt();
         } else {
            this.field5 = null;
         }
      } else {
         this.field4 = 15000L;
         this.field5 = null;
      }

      this.field6 = System.currentTimeMillis();
      this.field3 = number2;
   }

   public ReconnectBackoff(@Nullable WebSocketRichClosePush websocketrichclosepush1) {
      this(websocketrichclosepush1, 120000L);
   }

   public boolean method1() {
      long number1 = System.currentTimeMillis() - this.field6;
      if (number1 < this.field4) {
         return false;
      }

      this.field4 = Math.min(this.field4 * 2L, this.field3);
      this.field6 = System.currentTimeMillis();
      return true;
   }

   public Optional<String> method2() {
      if (this.field5 == null) {
         return Optional.empty();
      }

      JsonObject json1 = AuthUtil.method3(this.field5);
      if (!json1.has("exp")) {
         return Optional.empty();
      }

      long number2 = json1.get("exp").getAsLong() * 1000L;
      return System.currentTimeMillis() >= number2 ? Optional.empty() : Optional.of(this.field5);
   }

   @Generated
   public void method3(@Nullable String text1) {
      this.field5 = text1;
   }
}
