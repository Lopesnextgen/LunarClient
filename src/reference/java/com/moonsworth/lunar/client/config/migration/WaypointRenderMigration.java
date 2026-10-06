package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.mod.render.WaypointStore;
import java.util.Map.Entry;

public class WaypointRenderMigration implements ConfigMigration {
   public WaypointRenderMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object object, JsonObject json3) {
      if (object instanceof WaypointStore) {
         try {
            for (Entry entry5 : json3.get("waypoints").getAsJsonObject().entrySet()) {
               JsonObject json6 = ((JsonElement)entry5.getValue()).getAsJsonObject();

               for (Entry entry8 : json6.entrySet()) {
                  JsonObject json9 = ((JsonElement)entry8.getValue()).getAsJsonObject();

                  for (Entry entry11 : json9.entrySet()) {
                     JsonObject json12 = ((JsonElement)entry11.getValue()).getAsJsonObject();
                     this.method2(json12);
                  }
               }
            }
         } catch (Exception exception13) {
            LunarLogger.method7("Error while loading waypoints: " + exception13, new Object[0]);
            exception13.printStackTrace();
         }
      }
   }

   private void method2(JsonObject json1) {
      JsonObject json2 = new JsonObject();
      this.method3("showBeam", json1, json2);
      this.method3("showText", json1, json2);
      this.method3("color", json1, json2);
      json1.add("renderConfig", json2);
   }

   private void method3(String text, JsonObject json2, JsonObject json3) {
      if (json2.has(text)) {
         json3.add(text, json2.remove(text));
      }
   }
}
