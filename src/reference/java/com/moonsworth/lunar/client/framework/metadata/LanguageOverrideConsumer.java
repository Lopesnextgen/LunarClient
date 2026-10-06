package com.moonsworth.lunar.client.framework.metadata;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class LanguageOverrideConsumer extends com.moonsworth.lunar.client.framework.metadata.MetadataConsumer {
   @Override
   public void method4(JsonElement var1) {
      if (var1.isJsonObject()) {
         ThreadModuleDump63.method4().method67().method22(var1.getAsJsonObject());
      }
   }
}
