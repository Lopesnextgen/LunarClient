package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.mod.hud.stopwatch.Stopwatch;
import com.moonsworth.lunar.client.util.text.TimeFormatting.TimeFormat;

public class StopwatchMigration implements ConfigMigration {
   public StopwatchMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object obj2, JsonObject json3) {
      if (obj2 instanceof Stopwatch stopwatch4 && !stopwatch4.method16()) {
         JsonObject json5 = new JsonObject();
         if (json3.has("options")) {
            JsonObject json6 = json3.getAsJsonObject("options").deepCopy();
            if (!json6.has("stopwatchKeybind")) {
               json6.addProperty("stopwatchKeybind", KeyCode.KEY_U.name());
               json6.addProperty("stopwatchKeybindShift", false);
               json6.addProperty("stopwatchKeybindControl", false);
               json6.addProperty("stopwatchKeybindAlt", false);
            }

            if (!json6.has("timeDisplayOption")) {
               json6.addProperty("timeDisplayOption", TimeFormat.STOPWATCH.id());
            }

            json6.addProperty("stopwatchName", "Stopwatch");
            json5.add("options", json6);
         }

         String[] items11 = new String[]{"x", "y", "position"};

         for (String text10 : items11) {
            if (json3.has(text10) && !json3.get(text10).isJsonNull()) {
               json5.add(text10, json3.get(text10));
            }
         }

         JsonArray array12;
         if (!json3.has("stopwatches")) {
            array12 = new JsonArray();
            json3.add("stopwatches", array12);
         } else {
            array12 = json3.getAsJsonArray("stopwatches");
         }

         String text13 = "MIGRATED_STOPWATCH";
         array12.add(text13);
         json3.add("stopwatches", array12);
         json3.add(text13, json5);
         stopwatch4.method15(true);
      }
   }
}
