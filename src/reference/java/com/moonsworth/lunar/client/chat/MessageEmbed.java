package com.moonsworth.lunar.client.chat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.websocket.conversation.v1.ConversationMessageEmbed;
import com.lunarclient.websocket.conversation.v1.ImageEmbed;
import com.lunarclient.websocket.conversation.v1.LinkPreviewEmbed;
import com.moonsworth.lunar.client.driver.bridge.JsonProvider;
import com.moonsworth.lunar.client.network.apollo.ProtoConverter;
import java.time.Instant;
import org.jetbrains.annotations.Nullable;

public abstract class MessageEmbed implements JsonProvider {
   private final String field1;
   private final String field2;
   @Nullable
   private final Instant field3;

   public MessageEmbed(String text1, String text2, @Nullable Instant instant3) {
      this.field1 = text1;
      this.field2 = text2;
      this.field3 = instant3;
   }

   @Nullable
   public static MessageEmbed method1(ConversationMessageEmbed conversationmessageembed0) {
      return switch (conversationmessageembed0.getEmbedCase()) {
         case IMAGE -> {
            ImageEmbed imageembed2 = conversationmessageembed0.getImage();
            yield new ImageEmbed(imageembed2.getRawUrl(), imageembed2.getProxiedUrl(), imageembed2.getMimeType(), ProtoConverter.method5(conversationmessageembed0.getCapturedAt()));
         }
         case LINK_PREVIEW -> {
            LinkPreviewEmbed linkpreviewembed1 = conversationmessageembed0.getLinkPreview();
            yield new LinkPreviewEmbed(
               linkpreviewembed1.getRawUrl(),
               linkpreviewembed1.getProxiedUrl(),
               linkpreviewembed1.getTitle(),
               linkpreviewembed1.getDescription(),
               linkpreviewembed1.getSiteName(),
               linkpreviewembed1.getContentType(),
               ProtoConverter.method5(conversationmessageembed0.getCapturedAt())
            );
         }
         default -> null;
      };
   }

   public abstract EmbedType method2();

   public JsonElement provide() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("type", this.method2().name());
      json1.addProperty("rawUrl", this.field1);
      json1.addProperty("proxiedUrl", this.field2);
      if (this.field3 != null) {
         json1.addProperty("capturedAtMs", this.field3.toEpochMilli());
      }

      return json1;
   }
}
