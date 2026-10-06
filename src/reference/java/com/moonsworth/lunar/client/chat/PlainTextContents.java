package com.moonsworth.lunar.client.chat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.chat.MessageContents;

public class PlainTextContents extends MessageContents {
   private final String field3;

   public PlainTextContents(String text1, List<String> list2, @Nullable MessageEmbed fov4_23) {
      super(list2, fov4_23);
      this.field3 = text1;
   }

   @Override
   public MessageContentType method2() {
      return MessageContentType.PLAIN_TEXT;
   }

   @Override
   public String method3() {
      return this.field3;
   }

   @Override
   public JsonElement provide() {
      JsonObject json1 = super.provide().getAsJsonObject();
      json1.addProperty("plainText", this.field3);
      return json1;
   }
}
