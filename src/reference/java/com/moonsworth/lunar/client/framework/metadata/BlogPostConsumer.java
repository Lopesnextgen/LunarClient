package com.moonsworth.lunar.client.framework.metadata;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.gui.blog.BlogPost;

public class BlogPostConsumer extends com.moonsworth.lunar.client.framework.metadata.MetadataConsumer {
   @Override
   public void method4(JsonElement var1) {
      for (JsonElement var3 : var1.getAsJsonArray()) {
         JsonObject var4 = var3.getAsJsonObject();
         BlogPost var5 = new BlogPost(var4.get("title").getAsString(), var4.get("image").getAsString(), var4.get("link").getAsString());
         Client.method109().method65().method4(var5);
      }

      Client.method109().method65().method2();
      Client.method109().method65().method5();
   }
}
