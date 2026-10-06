package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;

public class CrosshairOutlineMigration implements ConfigMigration {
   public CrosshairOutlineMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object obj2, JsonObject json3) {
      if (obj2 instanceof Framework7Extension framework7extension4 && framework7extension4.getId().startsWith("CROSSHAIR_")) {
         ThreadModuleDump9.findJsonObject(json3, "options").ifPresent(arg0 -> {
            JsonElement element1x = arg0.get("outlineThickness");
            if (element1x != null && element1x.isJsonPrimitive() && !arg0.has("dotOutlineThickness")) {
               arg0.add("dotOutlineThickness", element1x.deepCopy());
            }
         });
      }
   }
}
