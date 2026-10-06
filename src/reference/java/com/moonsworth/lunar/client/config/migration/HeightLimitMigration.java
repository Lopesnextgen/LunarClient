package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import org.jetbrains.annotations.Nullable;

public class HeightLimitMigration implements ConfigMigration {
   private static final String[] field1 = new String[]{
      "overlayMode",
      "gradientHeight",
      "darkenColor",
      "barrierColor",
      "topFaceOnly",
      "style",
      "showTitle",
      "mapName",
      "heightLimit",
      "currentHeight",
      "distanceToHeightLimit"
   };
   private static final String[] field2 = new String[]{"HEIGHT_LIMIT_BEDWARS", "HEIGHT_LIMIT_BRIDGE", "HEIGHT_LIMIT_VANILLA", "HEIGHT_LIMIT_SERVER"};
   private static final String[] field3 = new String[]{"bedwarsHeightLimit", "bridgeHeightLimit", "vanillaCeiling"};

   public HeightLimitMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object obj2, JsonObject json3) {
      if (obj2 instanceof ModsSettings) {
         JsonObject json4 = (JsonObject)ThreadModuleDump9.findJsonObject(json3, "HEIGHT_LIMIT").orElse(null);
         if (json4 != null) {
            for (String text8 : field2) {
               if (json4.has(text8)) {
                  return;
               }
            }

            JsonObject json11 = ThreadModuleDump9.findJsonObject(json4, "options").orElse(new JsonObject());
            JsonObject json12 = new JsonObject();

            for (String text10 : field1) {
               if (json11.has(text10) && !json11.get(text10).isJsonNull()) {
                  json12.add(text10, json11.get(text10));
               }
            }

            if (json11.has("darkenBlocksAtLimit") && !json11.get("darkenBlocksAtLimit").isJsonNull()) {
               json12.addProperty("showOverlay", json11.get("darkenBlocksAtLimit").getAsBoolean());
            }

            this.method2(json4, "HEIGHT_LIMIT_BEDWARS", json11, "bedwarsHeightLimit", json12);
            this.method2(json4, "HEIGHT_LIMIT_BRIDGE", json11, "bridgeHeightLimit", json12);
            this.method2(json4, "HEIGHT_LIMIT_VANILLA", json11, "vanillaCeiling", json12);
            this.method2(json4, "HEIGHT_LIMIT_SERVER", json11, null, json12);
            json11.remove("darkenBlocksAtLimit");

            for (String text21 : field1) {
               json11.remove(text21);
            }

            for (String text22 : field3) {
               json11.remove(text22);
            }

            if (json11.entrySet().isEmpty()) {
               json4.remove("options");
            }
         }
      }
   }

   private void method2(JsonObject json1, String text2, JsonObject json3, @Nullable String text4, JsonObject json5) {
      JsonObject json6 = new JsonObject();
      if (text4 != null && json3.has(text4) && !json3.get(text4).isJsonNull() && !json3.get(text4).getAsBoolean()) {
         json6.addProperty("enabled", false);
      }

      if (!json5.entrySet().isEmpty()) {
         json6.add("options", json5.deepCopy());
      }

      if (!json6.entrySet().isEmpty()) {
         json1.add(text2, json6);
      }
   }
}
