package com.moonsworth.lunar.client.ui.blog;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.google.common.net.MediaType;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.driver.bridge.JsonProvider;
import lombok.Generated;

public class BlogPost implements JsonProvider {
   public static final MediaType field1 = MediaType.PNG;
   private final String field2;
   private final String imageUrl;
   private final String field3;
   private String field4 = "backgrounds/post-default-403x171.png";

   public BlogPost(String text1, String text, String text2) {
      this.field2 = text1;
      this.imageUrl = text;
      this.field3 = text2;
   }

   public String getHash() {
      return Hashing.md5().hashString(this.imageUrl, Charsets.UTF_8).toString();
   }

   public JsonElement provide() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("title", this.field2);
      json1.addProperty("imageUrl", this.field4);
      json1.addProperty("link", this.field3);
      return json1;
   }

   @Generated
   public String getTitle() {
      return this.field2;
   }

   @Generated
   public String getImageUrl() {
      return this.imageUrl;
   }

   @Generated
   public String method2() {
      return this.field3;
   }

   @Generated
   public String method3() {
      return this.field4;
   }

   @Generated
   public void method3(String text1) {
      this.field4 = text1;
   }
}
