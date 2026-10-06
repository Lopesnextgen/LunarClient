package com.moonsworth.lunar.client.framework.metadata;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.Client;

public class LinkOverrideConsumer extends com.moonsworth.lunar.client.framework.metadata.MetadataConsumer {
   @Override
   public void method4(JsonElement var1) {
      JsonObject var2 = var1.getAsJsonObject();

      for (String var4 : var2.keySet()) {
         Client.method109().method64().method3().put(var4, var2.get(var4).getAsString());
      }
   }
}
