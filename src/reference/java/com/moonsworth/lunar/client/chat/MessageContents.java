package com.moonsworth.lunar.client.chat;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.websocket.conversation.v1.ConversationImage;
import com.lunarclient.websocket.conversation.v1.ConversationMessageContents;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.chat.MessageContentType;
import com.moonsworth.lunar.client.chat.Sticker;
import com.moonsworth.lunar.client.chat.PlainTextContents;
import com.moonsworth.lunar.client.chat.SystemEvent;
import com.moonsworth.lunar.client.chat.SystemEventContents;
import com.moonsworth.lunar.client.chat.MessageEmbed;
import com.moonsworth.lunar.client.chat.StickerContents;

public abstract class MessageContents implements JsonProviderLegacy {
   private final List<String> field1;
   @Nullable
   private final MessageEmbed field2;

   public MessageContents(List<String> var1, @Nullable MessageEmbed var2) {
      this.field1 = List.copyOf(var1);
      this.field2 = var2;
   }

   @Nullable
   public static MessageContents method1(ConversationMessageContents var0) {
      ArrayList var1 = new ArrayList();

      for (ConversationImage var3 : var0.getImagesList()) {
         var1.add(var3.getUrl());
      }

      MessageEmbed var4 = var0.hasEmbed() ? MessageEmbed.method1(var0.getEmbed()) : null;
      switch (var0.getContentsCase()) {
         case PLAIN_TEXT:
            return new PlainTextContents(var0.getPlainText(), var1, var4);
         case STICKER:
            return new StickerContents(new Sticker(var0.getSticker().getId(), var0.getSticker().getUrl(), var0.getSticker().getName()), var1, var4);
         case SYSTEM:
            SystemEvent var5 = SystemEvent.method1(var0.getSystem());
            if (var5 == null) {
               return null;
            }

            return new SystemEventContents(var5, var1, var4);
         case CONTENTS_NOT_SET:
         default:
            return null;
      }
   }

   public abstract MessageContentType method2();

   public abstract String method3();

   public JsonElement provide() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("type", this.method2().name());
      JsonArray var2 = new JsonArray();
      this.field1.forEach(var2::add);
      var1.add("imageUrls", var2);
      if (this.field2 != null) {
         var1.add("embed", this.field2.provide());
      }

      return var1;
   }
}
