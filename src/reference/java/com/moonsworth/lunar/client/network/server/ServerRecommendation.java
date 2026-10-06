package com.moonsworth.lunar.client.network.server;

import com.google.gson.annotations.SerializedName;
import com.lunarclient.websocket.serverdiscovery.v1.GlobalAnnouncement;

public class ServerRecommendation {
   @SerializedName("announcement")
   private final ServerAnnouncement field1;
   @SerializedName("server")
   private final RecommendedServer field2;

   public ServerRecommendation(ServerAnnouncement var1, RecommendedServer var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   public static ServerRecommendation method1(GlobalAnnouncement var0) {
      return new ServerRecommendation(ServerAnnouncement.method1(var0.getAnnouncement()), RecommendedServer.method1(var0.getServer()));
   }

   @SerializedName("announcement")
   public ServerAnnouncement method2() {
      return this.field1;
   }

   @SerializedName("server")
   public RecommendedServer method3() {
      return this.field2;
   }
}
