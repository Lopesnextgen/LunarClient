package com.moonsworth.lunar.client.coordinates;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.lunarclient.websocket.hostedworld.v1.HostedWorldStatusPush.OfflinePlayer;
import com.lunarclient.websocket.hostedworld.v1.HostedWorldStatusPush.OnlinePlayer;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump11;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import java.util.ArrayList;
import java.util.UUID;
import lombok.Generated;

public final class Gui2Handler implements JsonProviderLegacy {
   @SerializedName("username")
   private final String field1;
   @SerializedName("uuid")
   private final UUID field2;
   @SerializedName("lastOnlineMillis")
   private long field3;
   @SerializedName("isOnline")
   private boolean field4;
   @SerializedName("logoColor")
   private final int field5;
   @SerializedName("plusColor")
   private final int field6;

   public Gui2Handler(String var1, UUID var2, long var3, boolean var5) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var5;
      CosmeticManager var6 = ThreadModuleDump63.method4().method53();
      if (var6.method63().containsKey(var2)) {
         CosmeticManager.Data var7 = var6.method63().get(var2);
         this.field5 = ThreadModuleDump23.method11(var7.method5(), var7.method6(), var7.method7(), 1.0F);
         this.field6 = var7.method9();
      } else {
         this.field5 = 0;
         this.field6 = 0;
      }
   }

   public boolean method2() {
      if (ThreadModuleDump63.method4().method81().method26()) {
         return this.field4;
      } else {
         return ThreadModuleDump63.method3().bridge$getIntegratedServer() == null
            ? false
            : new ArrayList<>(ThreadModuleDump63.method3().bridge$getIntegratedServer().bridge$getPlayers())
               .stream()
               .anyMatch(var1 -> var1.bridge$getUniqueID().equals(this.field2));
      }
   }

   public static Gui2Handler method2(OnlinePlayer var0, long var1) {
      return new Gui2Handler(
         var0.getPlayer().getUsername(),
         ThreadModuleDump66.method1(var0.getPlayer().getUuid()),
         var1,
         true,
         var0.getLogoColor().getColor(),
         var0.getPlusColor().getColor()
      );
   }

   public static Gui2Handler method3(OfflinePlayer var0) {
      return new Gui2Handler(
         var0.getPlayer().getUsername(),
         ThreadModuleDump66.method1(var0.getPlayer().getUuid()),
         ThreadModuleDump66.method15(var0.getLastOnline()),
         false,
         -1,
         0
      );
   }

   public boolean method4() {
      return ThreadModuleDump63.method4().method81().method31().contains(this.field2);
   }

   @Override
   public JsonElement provide() {
      FogIterator var1 = ThreadModuleDump63.method4().method81();
      boolean var2 = var1.method31().contains(this.field2);
      String var3 = Client.method109()
         .method67()
         .method2("gui.components", "hostedWorldLastSeenText", ThreadModuleDump11.formatDuration(System.currentTimeMillis() - this.field3));
      int var4;
      if (var1.method20()) {
         Bridge5Extension_5 var5 = ThreadModuleDump63.method7();
         if (var5 != null && this.field1.equals(var5.bridge$getName())) {
            var4 = Gui2Handler.Data.field1;
         } else if (this.field4) {
            var4 = var2 ? Gui2Handler.Data.field2 : Gui2Handler.Data.field3;
         } else {
            var4 = Gui2Handler.Data.field4;
         }
      } else if (var1.method37() != null && this.field1.equals(var1.method37().username())) {
         var4 = Gui2Handler.Data.field1;
      } else if (this.field4) {
         var4 = Gui2Handler.Data.field2;
      } else {
         var4 = Gui2Handler.Data.field4;
      }

      JsonObject var7 = new JsonObject();
      var7.addProperty("username", this.field1);
      var7.addProperty("uuid", this.field2.toString());
      var7.addProperty("lastSeen", var3);
      var7.addProperty("isOnline", this.field4);
      var7.addProperty("status", var4);
      JsonObject var6 = new JsonObject();
      var6.addProperty("isLunarPlus", this.field6 != 0);
      var6.addProperty("plusColor", String.format("#%06X", 16777215 & this.field6));
      var6.addProperty("logoColor", String.format("#%06X", 16777215 & this.field5));
      var7.add("lunarPlus", var6);
      return var7;
   }

   @Generated
   public String getUsername() {
      return this.field1;
   }

   @Generated
   public UUID method5() {
      return this.field2;
   }

   @Generated
   public long method6() {
      return this.field3;
   }

   @Generated
   public int method7() {
      return this.field5;
   }

   @Generated
   public int method8() {
      return this.field6;
   }

   @Generated
   public Gui2Handler(String var1, UUID var2, long var3, boolean var5, int var6, int var7) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var5;
      this.field5 = var6;
      this.field6 = var7;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Gui2Handler var2)) {
         return false;
      } else {
         UUID var3 = this.method5();
         UUID var4 = var2.method5();
         return var3 == null ? var4 == null : var3.equals(var4);
      }
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      byte var2 = 1;
      UUID var3 = this.method5();
      return var2 * 59 + (var3 == null ? 43 : var3.hashCode());
   }

   @Generated
   public void method9(long var1) {
      this.field3 = var1;
   }

   @Generated
   public void method10(boolean var1) {
      this.field4 = var1;
   }

   static class Data {
      static int field1 = 1;
      static int field2 = 2;
      static int field3 = 3;
      static int field4 = 4;
   }
}
