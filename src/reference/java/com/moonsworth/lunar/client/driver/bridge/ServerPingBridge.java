package com.moonsworth.lunar.client.driver.bridge;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;

public class ServerPingBridge implements DriverGuiExtension {
   public ServerPingBridge() {
   }

   @CallbackJS("ping")
   public static void method2(String text0) {
      Client.method109().method104().method1(text0).whenComplete((arg1, arg2) -> {
         Object obj3;
         if (arg2 != null) {
            JsonObject json4 = new JsonObject();
            json4.addProperty("status", "error");
            json4.addProperty("address", text0);
            json4.addProperty("ping", -1);
            json4.addProperty("playersOnline", -1);
            json4.addProperty("playersMax", -1);
            json4.addProperty("error", "Unable to ping server.");
            obj3 = json4;
         } else {
            obj3 = LunarConstants.field22.toJsonTree(arg1);
         }

         Ref.method3().bridge$submit(() -> method2(text0, obj3));
      });
   }

   private static void method2(String text0, JsonElement element1) {
      DriverViewportLegacy highlight3iterator2 = DriverViewportLegacy.method50();
      if (highlight3iterator2 != null && highlight3iterator2.method55().method13() != null) {
         highlight3iterator2.method23(highlight3iterator2.method55().method13(), "serverPing:result", element1);
      }
   }

   @Override
   public JsonElement provide() {
      return null;
   }

   @Override
   public JsonElement method128() {
      return null;
   }
}
