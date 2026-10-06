package com.moonsworth.lunar.client.driver.waypoint;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import com.moonsworth.lunar.client.framework.feature.waypoints.mixin.WaypointImporter;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.io.IOException;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class WaypointImportJsApi implements DriverGuiExtension {
   private static final WaypointStore field1 = Client.method109().method48();

   public WaypointImportJsApi() {
   }

   @Nullable
   @Override
   public JsonElement method128() {
      return null;
   }

   @Override
   public JsonElement provide() {
      JsonArray array1 = new JsonArray();

      for (WaypointImporter gui2loader3 : field1.getDecoders()) {
         array1.add(gui2loader3.provide());
      }

      return array1;
   }

   @CallbackJS("searchForWaypoints")
   public static void method2() {
      Ref.method3().bridge$submit(() -> {
         for (WaypointImporter gui2loader1 : field1.getDecoders()) {
            BackgroundExecutor.method6().execute(() -> {
               JsonObject json1x = gui2loader1.provide().getAsJsonObject();

               try {
                  JsonObject json2 = method4(gui2loader1);
                  if (json2 != null) {
                     json1x.add("decoded", json2);
                  }
               } catch (Exception exception3) {
                  LunarLogger.method8("Waypoints", exception3.getMessage(), new Object[0]);
               }

               DriverViewportLegacy.method50().method23(DriverViewportLegacy.method50().method55().method13(), "waypoints:searchResult", json1x);
            });
         }
      });
   }

   @CallbackJS("importWaypoints")
   public static String method3(String text0) {
      for (WaypointImporter gui2loader2 : field1.getDecoders()) {
         if (gui2loader2.getName().equals(text0)) {
            JsonObject json3 = method4(gui2loader2);
            if (json3 != null) {
               return json3.toString();
            }
         }
      }

      return null;
   }

   private static JsonObject method4(WaypointImporter gui2loader0) {
      try {
         List list1 = gui2loader0.method3();
         JsonObject json2 = Ref.method4().method48().method21(list1);
         JsonObject json3 = new JsonObject();
         Ref.method4().method48().method17(json3, list1);
         json3.add("context", json2);
         return json3;
      } catch (IOException exception4) {
         exception4.printStackTrace();
         return null;
      }
   }
}
