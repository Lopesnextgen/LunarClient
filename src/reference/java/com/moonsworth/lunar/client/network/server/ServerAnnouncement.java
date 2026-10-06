package com.moonsworth.lunar.client.network.server;

import com.google.gson.annotations.SerializedName;
import com.lunarclient.websocket.serverdiscovery.v1.Announcement;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import org.jetbrains.annotations.Nullable;

public class ServerAnnouncement {
   @SerializedName("text")
   private final String field1;
   @SerializedName("countDownTo")
   @Nullable
   private final Long field2;
   @SerializedName("countUpFrom")
   @Nullable
   private final Long field3;

   public ServerAnnouncement(String var1, @Nullable Long var2, @Nullable Long var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
   }

   public static ServerAnnouncement method1(Announcement var0) {
      return new ServerAnnouncement(
         var0.getText(),
         var0.hasCountDownTo() ? ThreadModuleDump66.method15(var0.getCountDownTo()) : null,
         var0.hasCountUpFrom() ? ThreadModuleDump66.method15(var0.getCountUpFrom()) : null
      );
   }

   @SerializedName("text")
   public String text() {
      return this.field1;
   }

   @SerializedName("countDownTo")
   @Nullable
   public Long method2() {
      return this.field2;
   }

   @SerializedName("countUpFrom")
   @Nullable
   public Long method3() {
      return this.field3;
   }
}
