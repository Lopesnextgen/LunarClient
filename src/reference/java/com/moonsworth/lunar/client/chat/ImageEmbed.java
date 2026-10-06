package com.moonsworth.lunar.client.chat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.time.Instant;
import org.jetbrains.annotations.Nullable;

public class ImageEmbed extends MessageEmbed {
   private final String field4;

   public ImageEmbed(String text, String text2, String text3, @Nullable Instant instant4) {
      super(text, text2, instant4);
      this.field4 = text3;
   }

   @Override
   public EmbedType method2() {
      return EmbedType.IMAGE;
   }

   @Override
   public JsonElement provide() {
      JsonObject json1 = super.provide().getAsJsonObject();
      json1.addProperty("mimeType", this.field4);
      return json1;
   }
}
