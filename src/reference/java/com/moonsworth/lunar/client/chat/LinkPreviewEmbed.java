package com.moonsworth.lunar.client.chat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.time.Instant;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class LinkPreviewEmbed extends MessageEmbed {
   private final String field4;
   private final String field5;
   private final String field6;
   private final String field7;

   public LinkPreviewEmbed(String text, String text2, String text3, String text4, String text5, String text6, @Nullable Instant instant7) {
      super(text, text2, instant7);
      this.field4 = text3;
      this.field5 = text4;
      this.field6 = text5;
      this.field7 = text6;
   }

   @Override
   public EmbedType method2() {
      return EmbedType.LINK_PREVIEW;
   }

   @Override
   public JsonElement provide() {
      JsonObject json1 = super.provide().getAsJsonObject();
      json1.addProperty("title", this.field4);
      json1.addProperty("description", this.field5);
      json1.addProperty("siteName", this.field6);
      json1.addProperty("mimeType", this.field7);
      return json1;
   }

   @Generated
   public String getTitle() {
      return this.field4;
   }

   @Generated
   public String getDescription() {
      return this.field5;
   }
}
