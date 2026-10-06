package com.moonsworth.lunar.client.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.websocket.friend.v1.FriendRequest;
import com.moonsworth.lunar.client.Gui2Handler2;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump34;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import java.time.Instant;
import java.util.UUID;
import lombok.Generated;

public class EntityRenderer6 implements JsonProviderLegacy {
   private final UUID uuid;
   private final String field1;
   private Instant field2;
   private int field3;
   private int field4;
   private Gui2Handler2 field5;
   private String rank;

   @Override
   public JsonElement provide() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("uuid", this.uuid.toString());
      var1.addProperty("username", this.field1);
      if (this.field5 != null) {
         var1.add("badge", this.field5.method2());
      }

      if (this.rank != null) {
         var1.addProperty("rank", this.rank);
      }

      var1.addProperty("timeSent", ThreadModuleDump34.method5(this.field2));
      var1.addProperty("timeSentMs", this.field2.getEpochSecond() * 1000L);
      JsonObject var2 = new JsonObject();
      var2.addProperty("isLunarPlus", this.field4 != 0);
      var2.addProperty("plusColor", String.format("#%06X", 16777215 & this.field4));
      var2.addProperty("logoColor", String.format("#%06X", 16777215 & this.field3));
      var1.add("lunarPlus", var2);
      return var1;
   }

   public static EntityRenderer6 method1(FriendRequest friendRequest) {
      return new EntityRenderer6(
         ThreadModuleDump66.method1(friendRequest.getPlayer().getUuid()),
         friendRequest.getPlayer().getUsername(),
         ThreadModuleDump66.method5(friendRequest.getSentAt()),
         friendRequest.getPlayerLogoColor().getColor(),
         friendRequest.getPlayerPlusColor().getColor(),
         friendRequest.getPlayerBadgeId() > 0
            ? (Gui2Handler2)ThreadModuleDump63.method4().method95().method2().get(friendRequest.getPlayerBadgeId())
            : null,
         friendRequest.getPlayerRankName()
      );
   }

   @Generated
   public UUID getUuid() {
      return this.uuid;
   }

   @Generated
   public String getUsername() {
      return this.field1;
   }

   @Generated
   public Instant method3() {
      return this.field2;
   }

   @Generated
   public int method4() {
      return this.field3;
   }

   @Generated
   public int method5() {
      return this.field4;
   }

   @Generated
   public Gui2Handler2 method6() {
      return this.field5;
   }

   @Generated
   public String getRank() {
      return this.rank;
   }

   @Generated
   public EntityRenderer6(UUID var1, String var2, Instant instant, int value, int value2, Gui2Handler2 handler, String text) {
      this.uuid = var1;
      this.field1 = var2;
      this.field2 = instant;
      this.field3 = value;
      this.field4 = value2;
      this.field5 = handler;
      this.rank = text;
   }
}
