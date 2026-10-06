package com.moonsworth.lunar.client.network.server;

import com.google.gson.annotations.SerializedName;
import com.lunarclient.websocket.serverdiscovery.v1.ServerModalDetails;

public class SocialLinks {
   @SerializedName("description")
   private final String field1;
   @SerializedName("twitter")
   private final String field2;
   @SerializedName("discord")
   private final String field3;
   @SerializedName("youtube")
   private final String field4;
   @SerializedName("instagram")
   private final String field5;
   @SerializedName("twitch")
   private final String field6;
   @SerializedName("telegram")
   private final String field7;
   @SerializedName("reddit")
   private final String field8;
   @SerializedName("tiktok")
   private final String field9;
   @SerializedName("facebook")
   private final String field10;

   public SocialLinks(String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10) {
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
   }

   public static SocialLinks method1(ServerModalDetails var0) {
      return new SocialLinks(
         var0.getDescription(),
         var0.getTwitter(),
         var0.getDiscord(),
         var0.getYoutube(),
         var0.getInstagram(),
         var0.getTwitch(),
         var0.getTelegram(),
         var0.getReddit(),
         var0.getTiktok(),
         var0.getFacebook()
      );
   }

   @SerializedName("description")
   public String description() {
      return this.field1;
   }

   @SerializedName("twitter")
   public String twitter() {
      return this.field2;
   }

   @SerializedName("discord")
   public String discord() {
      return this.field3;
   }

   @SerializedName("youtube")
   public String youtube() {
      return this.field4;
   }

   @SerializedName("instagram")
   public String instagram() {
      return this.field5;
   }

   @SerializedName("twitch")
   public String twitch() {
      return this.field6;
   }

   @SerializedName("telegram")
   public String method2() {
      return this.field7;
   }

   @SerializedName("reddit")
   public String method3() {
      return this.field8;
   }

   @SerializedName("tiktok")
   public String method4() {
      return this.field9;
   }

   @SerializedName("facebook")
   public String method5() {
      return this.field10;
   }
}
