package com.moonsworth.lunar.client.driver;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.PlayerCapabilitiesBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.Ref;

public class DriverContext {
   public DriverContext() {
   }

   public JsonElement method1() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("isMainMenu", Ref.method3().bridge$getWorld() == null);
      json1.addProperty("streaming", Client.method109().method34().method3());
      if (Ref.method7() != null && Ref.method7().bridge$getPlayerCapabilities() != null) {
         PlayerCapabilitiesBridge bridge3_322 = Ref.method7().bridge$getPlayerCapabilities();
         json1.addProperty("creative", bridge3_322.bridge$isCreativeMode());
      }

      this.method2(json1);
      return json1;
   }

   protected void method2(JsonObject json1) {
   }
}
