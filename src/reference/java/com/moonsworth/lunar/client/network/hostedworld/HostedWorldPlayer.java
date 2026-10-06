package com.moonsworth.lunar.client.network.hostedworld;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.lunarclient.websocket.hostedworld.v1.HostedWorldStatusPush.OfflinePlayer;
import com.lunarclient.websocket.hostedworld.v1.HostedWorldStatusPush.OnlinePlayer;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.driver.bridge.JsonProvider;
import com.moonsworth.lunar.client.util.text.TimeFormatting;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.network.apollo.ProtoConverter;
import java.util.ArrayList;
import java.util.UUID;
import lombok.Generated;

public final class HostedWorldPlayer implements JsonProvider {
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

   public HostedWorldPlayer(String text1, UUID uuid2, long number3, boolean flag5) {
      this.field1 = text1;
      this.field2 = uuid2;
      this.field3 = number3;
      this.field4 = flag5;
      CosmeticManager holograms126 = Ref.method4().method53();
      if (holograms126.method63().containsKey(uuid2)) {
         CosmeticManager.Data data7 = holograms126.method63().get(uuid2);
         this.field5 = ColorUtils.method11(data7.method5(), data7.method6(), data7.method7(), 1.0F);
         this.field6 = data7.method9();
      } else {
         this.field5 = 0;
         this.field6 = 0;
      }
   }

   public boolean method2() {
      if (Ref.method4().method81().method26()) {
         return this.field4;
      } else {
         return Ref.method3().bridge$getIntegratedServer() == null
            ? false
            : new ArrayList<>(Ref.method3().bridge$getIntegratedServer().bridge$getPlayers())
               .stream()
               .anyMatch(arg1 -> arg1.bridge$getUniqueID().equals(this.field2));
      }
   }

   public static HostedWorldPlayer method2(OnlinePlayer onlineplayer0, long number1) {
      return new HostedWorldPlayer(
         onlineplayer0.getPlayer().getUsername(),
         ProtoConverter.method1(onlineplayer0.getPlayer().getUuid()),
         number1,
         true,
         onlineplayer0.getLogoColor().getColor(),
         onlineplayer0.getPlusColor().getColor()
      );
   }

   public static HostedWorldPlayer method3(OfflinePlayer offlineplayer0) {
      return new HostedWorldPlayer(
         offlineplayer0.getPlayer().getUsername(),
         ProtoConverter.method1(offlineplayer0.getPlayer().getUuid()),
         ProtoConverter.method15(offlineplayer0.getLastOnline()),
         false,
         -1,
         0
      );
   }

   public boolean method4() {
      return Ref.method4().method81().method31().contains(this.field2);
   }

   public JsonElement provide() {
      FogIterator fogiterator1 = Ref.method4().method81();
      boolean flag2 = fogiterator1.method31().contains(this.field2);
      String text3 = Client.method109()
         .method67()
         .method2("gui.components", "hostedWorldLastSeenText", TimeFormatting.method1(System.currentTimeMillis() - this.field3));
      int number4;
      if (fogiterator1.method20()) {
         Bridge5Extension_5 bridge5extension_55 = Ref.method7();
         if (bridge5extension_55 != null && this.field1.equals(bridge5extension_55.bridge$getName())) {
            number4 = HostedWorldPlayer.Data.field1;
         } else if (this.field4) {
            number4 = flag2 ? HostedWorldPlayer.Data.field2 : HostedWorldPlayer.Data.field3;
         } else {
            number4 = HostedWorldPlayer.Data.field4;
         }
      } else if (fogiterator1.method37() != null && this.field1.equals(fogiterator1.method37().username())) {
         number4 = HostedWorldPlayer.Data.field1;
      } else if (this.field4) {
         number4 = HostedWorldPlayer.Data.field2;
      } else {
         number4 = HostedWorldPlayer.Data.field4;
      }

      JsonObject json7 = new JsonObject();
      json7.addProperty("username", this.field1);
      json7.addProperty("uuid", this.field2.toString());
      json7.addProperty("lastSeen", text3);
      json7.addProperty("isOnline", this.field4);
      json7.addProperty("status", number4);
      JsonObject json6 = new JsonObject();
      json6.addProperty("isLunarPlus", this.field6 != 0);
      json6.addProperty("plusColor", String.format("#%06X", 16777215 & this.field6));
      json6.addProperty("logoColor", String.format("#%06X", 16777215 & this.field5));
      json7.add("lunarPlus", json6);
      return json7;
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
   public HostedWorldPlayer(String text1, UUID uuid2, long number3, boolean flag5, int number6, int number7) {
      this.field1 = text1;
      this.field2 = uuid2;
      this.field3 = number3;
      this.field4 = flag5;
      this.field5 = number6;
      this.field6 = number7;
   }

   @Generated
   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (!(obj1 instanceof HostedWorldPlayer gui2handler2)) {
         return false;
      } else {
         UUID uuid3 = this.method5();
         UUID uuid4 = gui2handler2.method5();
         return uuid3 == null ? uuid4 == null : uuid3.equals(uuid4);
      }
   }

   @Generated
   @Override
   public int hashCode() {
      byte number1 = 59;
      byte number2 = 1;
      UUID uuid3 = this.method5();
      return number2 * 59 + (uuid3 == null ? 43 : uuid3.hashCode());
   }

   @Generated
   public void method9(long number1) {
      this.field3 = number1;
   }

   @Generated
   public void method10(boolean flag1) {
      this.field4 = flag1;
   }

   static class Data {
      static int field1 = 1;
      static int field2 = 2;
      static int field3 = 3;
      static int field4 = 4;

      Data() {
      }
   }
}
