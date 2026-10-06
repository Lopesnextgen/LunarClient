package com.moonsworth.lunar.client.chat;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.websocket.conversation.v1.LunarEmoji;
import com.lunarclient.websocket.conversation.v1.LunarEmojiCategory;
import com.lunarclient.websocket.conversation.v1.LunarSticker;
import com.lunarclient.websocket.conversation.v1.LunarStickerPack;
import com.moonsworth.lunar.client.driver.bridge.JsonProvider;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class StickerStore implements JsonProvider {
   private int field1;
   private int field2;
   private int field3;
   private final List<LunarStickerPack> field4 = new ArrayList<>();
   private final List<LunarEmojiCategory> field5 = new ArrayList<>();

   public StickerStore() {
   }

   public void method1(int number1, int value, int value2, List<LunarStickerPack> list, List<LunarEmojiCategory> list2) {
      this.field1 = number1;
      this.field2 = value;
      this.field3 = value2;
      this.field4.clear();
      this.field4.addAll(list);
      this.field5.clear();
      this.field5.addAll(list2);
   }

   public void clear() {
      this.field1 = 0;
      this.field2 = 0;
      this.field3 = 0;
      this.field4.clear();
      this.field5.clear();
   }

   public JsonElement provide() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("participantLimit", this.field1);
      json1.addProperty("totalConversations", this.field2);
      json1.addProperty("maxMessageLength", this.field3);
      JsonArray array2 = new JsonArray();
      this.field4.forEach(arg2x -> array2.add(this.method2(arg2x)));
      json1.add("stickerPacks", array2);
      JsonArray array3 = new JsonArray();
      this.field5.forEach(arg2x -> array3.add(this.method4(arg2x)));
      json1.add("emojiCategories", array3);
      return json1;
   }

   private JsonObject method2(LunarStickerPack lunarstickerpack1) {
      JsonObject json2 = new JsonObject();
      json2.addProperty("id", lunarstickerpack1.getId());
      json2.addProperty("name", lunarstickerpack1.getName());
      json2.addProperty("iconUrl", lunarstickerpack1.getIconUrl());
      JsonArray array3 = new JsonArray();
      lunarstickerpack1.getStickersList().forEach(arg2x -> array3.add(this.method3(arg2x)));
      json2.add("stickers", array3);
      return json2;
   }

   private JsonObject method3(LunarSticker lunarsticker1) {
      JsonObject json2 = new JsonObject();
      json2.addProperty("id", lunarsticker1.getId());
      json2.addProperty("url", lunarsticker1.getUrl());
      json2.addProperty("name", lunarsticker1.getName());
      return json2;
   }

   private JsonObject method4(LunarEmojiCategory lunaremojicategory1) {
      JsonObject json2 = new JsonObject();
      json2.addProperty("id", lunaremojicategory1.getId());
      json2.addProperty("name", lunaremojicategory1.getName());
      json2.addProperty("iconUrl", lunaremojicategory1.getIconUrl());
      JsonArray array3 = new JsonArray();
      lunaremojicategory1.getEmojisList().forEach(arg2x -> array3.add(this.method5(arg2x)));
      json2.add("emojis", array3);
      return json2;
   }

   private JsonObject method5(LunarEmoji lunaremoji1) {
      JsonObject json2 = new JsonObject();
      json2.addProperty("id", lunaremoji1.getId());
      json2.addProperty("url", lunaremoji1.getUrl());
      json2.addProperty("name", lunaremoji1.getName());
      return json2;
   }

   @Generated
   public int getParticipantLimit() {
      return this.field1;
   }

   @Generated
   public int getTotalConversations() {
      return this.field2;
   }

   @Generated
   public int getMaxMessageLength() {
      return this.field3;
   }

   @Generated
   public List<LunarStickerPack> method6() {
      return this.field4;
   }

   @Generated
   public List<LunarEmojiCategory> method7() {
      return this.field5;
   }

   @Generated
   public void method8(int number1) {
      this.field1 = number1;
   }

   @Generated
   public void method9(int number1) {
      this.field2 = number1;
   }

   @Generated
   public void method10(int number1) {
      this.field3 = number1;
   }
}
