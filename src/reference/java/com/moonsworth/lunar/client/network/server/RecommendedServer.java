package com.moonsworth.lunar.client.network.server;

import com.google.gson.annotations.SerializedName;
import com.lunarclient.websocket.serverdiscovery.v1.ServerCard;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class RecommendedServer {
   @SerializedName("recommendationId")
   private final String field1;
   @SerializedName("serverMappingsId")
   private final String field2;
   @SerializedName("name")
   private final String field3;
   @SerializedName("logoUrl")
   private final String field4;
   @SerializedName("backgroundUrl")
   private final String field5;
   @SerializedName("primaryColor")
   private final String field6;
   @SerializedName("primaryAddress")
   private final String field7;
   @SerializedName("badge")
   private final String field8;
   @SerializedName("lastJoined")
   @Nullable
   private final Long field9;
   @SerializedName("gameTypes")
   private final List<DiscoveryCard> field10;
   @SerializedName("announcement")
   @Nullable
   private final ServerAnnouncement field11;
   @SerializedName("regionCodes")
   private final List<String> field12;

   public RecommendedServer(
      String var1,
      String var2,
      String var3,
      String var4,
      String var5,
      String var6,
      String var7,
      String var8,
      @Nullable Long var9,
      List<DiscoveryCard> var10,
      @Nullable ServerAnnouncement var11,
      List<String> var12
   ) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
      this.field5 = var5;
      this.field6 = var6;
      this.field7 = var7;
      this.field8 = var8;
      this.field9 = var9;
      this.field10 = var10;
      this.field11 = var11;
      this.field12 = var12;
   }

   public static RecommendedServer method1(ServerCard var0) {
      return new RecommendedServer(
         var0.getRecommendationId(),
         var0.getServerMappingsId(),
         var0.getName(),
         var0.getLogoUrl(),
         var0.getBackgroundUrl(),
         var0.hasPrimaryColor() ? method2(var0.getPrimaryColor().getColor()) : null,
         var0.getPrimaryAddress(),
         var0.getBadge().name(),
         var0.hasLastJoined() ? ThreadModuleDump66.method15(var0.getLastJoined()) : null,
         var0.getGameTypesList().stream().map(DiscoveryCard::method1).toList(),
         var0.hasAnnouncement() ? ServerAnnouncement.method1(var0.getAnnouncement()) : null,
         new ArrayList<>(var0.getRegionCodesList())
      );
   }

   private static String method2(int var0) {
      return String.format("#%06X", 16777215 & var0);
   }

   @SerializedName("recommendationId")
   public String method3() {
      return this.field1;
   }

   @SerializedName("serverMappingsId")
   public String method4() {
      return this.field2;
   }

   @SerializedName("name")
   public String name() {
      return this.field3;
   }

   @SerializedName("logoUrl")
   public String method5() {
      return this.field4;
   }

   @SerializedName("backgroundUrl")
   public String method6() {
      return this.field5;
   }

   @SerializedName("primaryColor")
   public String method7() {
      return this.field6;
   }

   @SerializedName("primaryAddress")
   public String method8() {
      return this.field7;
   }

   @SerializedName("badge")
   public String method9() {
      return this.field8;
   }

   @SerializedName("lastJoined")
   @Nullable
   public Long method10() {
      return this.field9;
   }

   @SerializedName("gameTypes")
   public List<DiscoveryCard> method11() {
      return this.field10;
   }

   @SerializedName("announcement")
   @Nullable
   public ServerAnnouncement method12() {
      return this.field11;
   }

   @SerializedName("regionCodes")
   public List<String> method13() {
      return this.field12;
   }
}
