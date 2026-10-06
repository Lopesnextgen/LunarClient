package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import org.jetbrains.annotations.Nullable;

public class BedWarsHeightLimitMigration implements ConfigMigration {
   private static final String[] field1 = new String[]{"x", "y", "position"};
   private static final String field2 = "hypixel_bedwars_height_limit";
   private static final String field3 = "hypixel_bedwars_height_limit_enabled_bl";

   public BedWarsHeightLimitMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object obj2, JsonObject json3) {
      if (obj2 instanceof ModsSettings && !json3.has("HEIGHT_LIMIT")) {
         JsonObject json4 = new JsonObject();
         JsonObject json5 = (JsonObject)ThreadModuleDump9.findJsonObject(json3, "HYPIXEL_BEDWARS").orElse(null);
         boolean flag6 = this.method2(json5, "enabled");
         if (json5 != null) {
            JsonObject json7 = (JsonObject)ThreadModuleDump9.findJsonObject(json5, "HYPIXEL_BEDWARS_HEIGHT_LIMIT_CHILD").orElse(null);
            if (json7 != null) {
               flag6 = flag6 && this.method2(json7, "enabled");

               for (String text11 : field1) {
                  if (json7.has(text11) && !json7.get(text11).isJsonNull()) {
                     json4.add(text11, json7.get(text11));
                  }
               }

               ThreadModuleDump9.findJsonObject(json7, "options").ifPresent(arg1x -> json4.add("options", arg1x.deepCopy()));
               json5.remove("HYPIXEL_BEDWARS_HEIGHT_LIMIT_CHILD");
            } else {
               JsonObject json8 = (JsonObject)ThreadModuleDump9.findJsonObject(json5, "hypixel_bedwars_height_limit").orElse(null);
               if (json8 != null) {
                  flag6 = flag6 && this.method2(json8, "hypixel_bedwars_height_limit_enabled_bl");
               }
            }

            json5.remove("hypixel_bedwars_height_limit");
         }

         if (flag6) {
            json4.addProperty("enabled", true);
         }

         if (!json4.entrySet().isEmpty()) {
            json3.add("HEIGHT_LIMIT", json4);
         }
      }
   }

   private boolean method2(@Nullable JsonObject json1, String text2) {
      return json1 == null || !json1.has(text2) || json1.get(text2).isJsonNull() || json1.get(text2).getAsBoolean();
   }
}
