package com.moonsworth.lunar.client.mixin;

import com.google.gson.JsonObject;
import com.lunarclient.websocket.protocol.v1.WebSocketRichClosePush;
import com.moonsworth.lunar.client.account.AuthUtil;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public final class EntityRenderer3 {
   private static final long field1 = 15000L;
   private static final long field2 = 1000L;
   private final long field3;
   private long field4;
   @Nullable
   private String field5;
   private long field6;

   public EntityRenderer3(@Nullable WebSocketRichClosePush var1, long var2) {
      if (var1 != null) {
         this.field4 = switch (var1.getReconnectDelay()) {
            case RECONNECT_DELAY_UNSPECIFIED, UNRECOGNIZED, RECONNECT_DELAY_NORMAL -> 15000L;
            case RECONNECT_DELAY_IMMEDIATE -> 1000L;
            default -> throw new IncompatibleClassChangeError();
         };
         if (!var1.getReconnectAuthenticatorJwt().isEmpty()) {
            this.field5 = var1.getReconnectAuthenticatorJwt();
         } else {
            this.field5 = null;
         }
      } else {
         this.field4 = 15000L;
         this.field5 = null;
      }

      this.field6 = System.currentTimeMillis();
      this.field3 = var2;
   }

   public EntityRenderer3(@Nullable WebSocketRichClosePush var1) {
      this(var1, 120000L);
   }

   public boolean method1() {
      long var1 = System.currentTimeMillis() - this.field6;
      if (var1 < this.field4) {
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

      JsonObject var1 = AuthUtil.method3(this.field5);
      if (!var1.has("exp")) {
         return Optional.empty();
      }

      long var2 = var1.get("exp").getAsLong() * 1000L;
      return System.currentTimeMillis() >= var2 ? Optional.empty() : Optional.of(this.field5);
   }

   @Generated
   public void method3(@Nullable String var1) {
      this.field5 = var1;
   }
}
