package com.moonsworth.lunar.client.driver;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge3_32;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class DriverContextLegacy {
   public JsonElement method1() {
      JsonObject var1 = new JsonObject();
      var1.addProperty("isMainMenu", ThreadModuleDump63.method3().bridge$getWorld() == null);
      var1.addProperty("streaming", Client.method109().method34().method3());
      if (ThreadModuleDump63.method7() != null && ThreadModuleDump63.method7().bridge$getPlayerCapabilities() != null) {
         Bridge3_32 var2 = ThreadModuleDump63.method7().bridge$getPlayerCapabilities();
         var1.addProperty("creative", var2.bridge$isCreativeMode());
      }

      this.method2(var1);
      return var1;
   }

   protected void method2(JsonObject var1) {
   }
}
