package com.moonsworth.lunar.client.chat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.chat.MessageContents;

public class StickerContents extends MessageContents {
   private final Sticker field3;

   public StickerContents(Sticker fov3$data41, List<String> list2, @Nullable MessageEmbed fov4_23) {
      super(list2, fov4_23);
      this.field3 = fov3$data41;
   }

   @Override
   public MessageContentType method2() {
      return MessageContentType.STICKER;
   }

   @Override
   public String method3() {
      return this.field3.name();
   }

   @Override
   public JsonElement provide() {
      JsonObject json1 = super.provide().getAsJsonObject();
      JsonObject json2 = new JsonObject();
      json2.addProperty("id", this.field3.id());
      json2.addProperty("url", this.field3.url());
      json2.addProperty("name", this.field3.name());
      json1.add("sticker", json2);
      return json1;
   }
}
