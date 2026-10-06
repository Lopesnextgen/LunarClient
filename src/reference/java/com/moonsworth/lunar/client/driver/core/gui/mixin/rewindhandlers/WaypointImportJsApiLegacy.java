package com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import com.moonsworth.lunar.client.framework.feature.waypoints.mixin.Gui2Loader;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.io.IOException;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class WaypointImportJsApiLegacy implements DriverGuiExtensionLegacy {
   private static final WaypointStore field1 = Client.method109().method48();

   @Nullable
   @Override
   public JsonElement method128() {
      return null;
   }

   @Override
   public JsonElement provide() {
      JsonArray var1 = new JsonArray();

      for (Gui2Loader var3 : field1.getDecoders()) {
         var1.add(var3.provide());
      }

      return var1;
   }

   @CallbackJS("searchForWaypoints")
   public static void method2() {
      ThreadModuleDump63.method3().bridge$submit(() -> {
         for (Gui2Loader var1 : field1.getDecoders()) {
            ThreadModuleDump37.method6().execute(() -> {
               JsonObject var1x = var1.provide().getAsJsonObject();

               try {
                  JsonObject var2 = method4(var1);
                  if (var2 != null) {
                     var1x.add("decoded", var2);
                  }
               } catch (Exception var3) {
                  Slayer.method8("Waypoints", var3.getMessage(), new Object[0]);
               }

               DriverViewportLegacy.method50().method23(DriverViewportLegacy.method50().method55().method13(), "waypoints:searchResult", var1x);
            });
         }
      });
   }

   @CallbackJS("importWaypoints")
   public static String method3(String var0) {
      for (Gui2Loader var2 : field1.getDecoders()) {
         if (var2.getName().equals(var0)) {
            JsonObject var3 = method4(var2);
            if (var3 != null) {
               return var3.toString();
            }
         }
      }

      return null;
   }

   private static JsonObject method4(Gui2Loader var0) {
      try {
         List var1 = var0.method3();
         JsonObject var2 = ThreadModuleDump63.method4().method48().method21(var1);
         JsonObject var3 = new JsonObject();
         ThreadModuleDump63.method4().method48().method17(var3, var1);
         var3.add("context", var2);
         return var3;
      } catch (IOException var4) {
         var4.printStackTrace();
         return null;
      }
   }
}
