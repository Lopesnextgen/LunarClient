package com.moonsworth.lunar.client.network.server;

import com.google.gson.annotations.SerializedName;
import com.lunarclient.websocket.serverdiscovery.v1.ServerGameType;
import com.moonsworth.lunar.client.driver.PhosphorIconLegacy;

public class DiscoveryCard {
   @SerializedName("name")
   private final String field1;
   @SerializedName("icon")
   private final int field2;

   public DiscoveryCard(String var1, int var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   public static DiscoveryCard method1(ServerGameType var0) {
      return new DiscoveryCard(var0.getName(), method2(var0.getEmoji()));
   }

   static int method2(String var0) {
      try {
         return PhosphorIconLegacy.valueOf(var0).ordinal();
      } catch (IllegalArgumentException var2) {
         return 0;
      }
   }

   @SerializedName("name")
   public String name() {
      return this.field1;
   }

   @SerializedName("icon")
   public int method3() {
      return this.field2;
   }
}
