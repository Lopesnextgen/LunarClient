package com.moonsworth.lunar.client.framework.metadata;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.keystrokes.Keystrokes3;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class StarServerConsumer extends com.moonsworth.lunar.client.framework.metadata.MetadataConsumer {
   @Override
   public void method4(JsonElement var1) {
      ThreadModuleDump63.method4().method78().clear();

      for (JsonElement var3 : var1.getAsJsonArray()) {
         JsonObject var4 = var3.getAsJsonObject();
         String var5 = null;
         if (var4.has("resource")) {
            JsonElement var6 = var4.get("resource");
            if (!var6.isJsonNull()) {
               var5 = var6.getAsString();
            }
         }

         String var7 = var4.get("pattern").getAsString();
         ThreadModuleDump63.method4().method78().method13().add(new Keystrokes3(var7, var5));
      }
   }
}
