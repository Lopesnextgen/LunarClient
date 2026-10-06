package com.moonsworth.lunar.client.network.server;

import com.google.gson.annotations.SerializedName;
import com.lunarclient.websocket.serverdiscovery.v1.ServerCard;
import com.lunarclient.websocket.serverdiscovery.v1.ServerSection;
import com.lunarclient.websocket.serverdiscovery.v1.ServerSection.CardSize;
import java.util.ArrayList;
import java.util.List;

public class DiscoverySection {
   @SerializedName("id")
   private final String field1;
   @SerializedName("name")
   private final String field2;
   @SerializedName("icon")
   private final int field3;
   @SerializedName("showNotInterested")
   private final boolean field4;
   @SerializedName("cardSize")
   private final String field5;
   @SerializedName("cards")
   private final List<RecommendedServer> field6;
   @SerializedName("description")
   private final String field7;

   public DiscoverySection(String var1, String var2, int var3, boolean var4, String var5, List<RecommendedServer> var6, String var7) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
      this.field5 = var5;
      this.field6 = var6;
      this.field7 = var7;
   }

   public static DiscoverySection method1(ServerSection var0) {
      ArrayList var1 = new ArrayList();

      for (ServerCard var3 : var0.getCardsList()) {
         var1.add(RecommendedServer.method1(var3));
      }

      return new DiscoverySection(
         var0.getId(),
         var0.getName(),
         DiscoveryCard.method2(var0.getEmoji()),
         var0.getShowNotInterested(),
         method2(var0.getCardSize()),
         var1,
         var0.getDescription()
      );
   }

   private static String method2(CardSize var0) {
      return switch (var0) {
         case CARD_SIZE_SMALL -> "sm";
         default -> "md";
      };
   }

   @SerializedName("id")
   public String id() {
      return this.field1;
   }

   @SerializedName("name")
   public String name() {
      return this.field2;
   }

   @SerializedName("icon")
   public int method3() {
      return this.field3;
   }

   @SerializedName("showNotInterested")
   public boolean method4() {
      return this.field4;
   }

   @SerializedName("cardSize")
   public String method5() {
      return this.field5;
   }

   @SerializedName("cards")
   public List<RecommendedServer> method6() {
      return this.field6;
   }

   @SerializedName("description")
   public String description() {
      return this.field7;
   }
}
