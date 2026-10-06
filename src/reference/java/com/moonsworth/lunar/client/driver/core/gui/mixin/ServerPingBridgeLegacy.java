package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;

public class ServerPingBridgeLegacy implements DriverGuiExtensionLegacy {
   @CallbackJS("ping")
   public static void method2(String var0) {
      Client.method109().method104().method1(var0).whenComplete((var1, var2) -> {
         Object var3;
         if (var2 != null) {
            JsonObject var4 = new JsonObject();
            var4.addProperty("status", "error");
            var4.addProperty("address", var0);
            var4.addProperty("ping", -1);
            var4.addProperty("playersOnline", -1);
            var4.addProperty("playersMax", -1);
            var4.addProperty("error", "Unable to ping server.");
            var3 = var4;
         } else {
            var3 = ThreadModuleDump48.field22.toJsonTree(var1);
         }

         ThreadModuleDump63.method3().bridge$submit(() -> method2(var0, var3));
      });
   }

   private static void method2(String var0, JsonElement var1) {
      DriverViewportLegacy var2 = DriverViewportLegacy.method50();
      if (var2 != null && var2.method55().method13() != null) {
         var2.method23(var2.method55().method13(), "serverPing:result", var1);
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
