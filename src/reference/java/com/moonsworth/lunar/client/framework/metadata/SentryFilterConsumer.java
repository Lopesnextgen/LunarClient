package com.moonsworth.lunar.client.framework.metadata;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.crash.IgnoredExceptionPatterns;
import java.util.regex.Pattern;

public class SentryFilterConsumer extends com.moonsworth.lunar.client.framework.metadata.MetadataConsumer {
   @Override
   public void method4(JsonElement var1) {
      for (JsonElement var3 : var1.getAsJsonArray()) {
         if (var3.isJsonObject()) {
            JsonObject var4 = var3.getAsJsonObject();
            String var5 = var4.get("identifier").getAsString();
            Pattern var6 = Pattern.compile(var4.get("regex").getAsString());
            IgnoredExceptionPatterns.method1(var5, var6);
         }
      }
   }
}
