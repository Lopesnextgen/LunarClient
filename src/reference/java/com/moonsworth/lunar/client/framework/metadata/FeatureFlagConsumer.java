package com.moonsworth.lunar.client.framework.metadata;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashMap;

public class FeatureFlagConsumer extends com.moonsworth.lunar.client.framework.metadata.MetadataConsumer {
   @Override
   public void method4(JsonElement var1) {
      HashMap var2 = new HashMap();

      for (FeatureFlag var6 : FeatureFlag.values()) {
         var2.put(var6.getIdentifier().toLowerCase(), var6);
      }

      for (JsonElement var11 : var1.getAsJsonArray()) {
         if (var11.isJsonObject()) {
            JsonObject var12 = var11.getAsJsonObject();
            String var13 = var12.get("identifier").getAsString().toLowerCase();
            boolean var7 = var12.get("value").getAsBoolean();
            FeatureFlag var8 = (FeatureFlag)var2.get(var13);
            if (var8 != null) {
               boolean var9 = !LunarBuildData.field4 || var7;
               var8.setValue(var9);
               if (var8.getDynamicReset() != null) {
                  ThreadModuleDump63.method3().bridge$submit(() -> var8.getDynamicReset().accept(var9));
               }
            }
         }
      }
   }
}
