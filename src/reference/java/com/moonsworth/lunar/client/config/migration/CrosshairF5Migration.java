package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.client.config.GeneralSettings;
import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;

public class CrosshairF5Migration implements ConfigMigration {
   private Boolean field1 = null;

   public CrosshairF5Migration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object object, JsonObject json3) {
      if (this.field1 != null && object instanceof ModsSettings) {
         this.method2(this.method2(json3, "CROSSHAIR"), "options").add("showInF5", new JsonPrimitive(this.field1));
      }

      if (object instanceof GeneralSettings && json3.has("showInF5")) {
         this.field1 = json3.remove("showInF5").getAsBoolean();
      }

      if (object instanceof Framework7Extension framework7extension4 && framework7extension4.getId().startsWith("CROSSHAIR_")) {
         ThreadModuleDump9.findJsonObject(json3, "options").ifPresent(arg0 -> ThreadModuleDump9.findInt(arg0, "dotSize").ifPresent(arg1x -> {
            arg0.remove("dotSize");
            arg0.addProperty("dotSize", Math.max(0, arg1x - 2));
         }));
      }
   }

   private JsonObject method2(JsonObject json1, String text) {
      return ThreadModuleDump9.findJsonObject(json1, text).orElseGet(() -> {
         JsonObject json2x = new JsonObject();
         json1.add(text, json2x);
         return json2x;
      });
   }
}
