package com.moonsworth.lunar.client.framework.metadata;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.config.override.AlertCard;
import java.util.HashMap;

public class AlertConsumer extends com.moonsworth.lunar.client.framework.metadata.MetadataConsumer {
   @Override
   public void method4(JsonElement var1) {
      if (var1.getAsJsonObject().has("active")) {
         JsonElement var2 = var1.getAsJsonObject().get("active");
         if (var2.isJsonArray()) {
            for (JsonElement var4 : var2.getAsJsonArray()) {
               this.method2(var4.getAsJsonObject());
            }
         } else {
            this.method2(var2.getAsJsonObject());
         }
      }

      HashMap var11 = new HashMap();
      if (var1.getAsJsonObject().has("colors")) {
         JsonObject var12 = var1.getAsJsonObject().get("colors").getAsJsonObject();

         for (String var5 : var12.keySet()) {
            JsonObject var6 = var12.get(var5).getAsJsonObject();
            String var7 = var6.get("default").getAsString();
            String var8 = var6.get("hover").getAsString();
            int var9 = (int)Long.parseLong(var7.substring(1), 16);
            int var10 = (int)Long.parseLong(var8.substring(1), 16);
            var11.put(var5, new AnimatedValue(var9, var10));
         }
      }

      Client.method109().method66().method9(var11);
   }

   private void method2(JsonObject var1) {
      AlertCard var2 = new AlertCard(
         var1.get("id").getAsInt(),
         var1.get("name").getAsString(),
         var1.get("text").getAsString(),
         var1.get("color").getAsString(),
         var1.get("icon").getAsString(),
         var1.get("link").getAsString(),
         var1.get("dismissable").getAsBoolean()
      );
      Client.method109().method66().method2(var2);
   }
}
