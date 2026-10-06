package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;

public class OverlayEnabledMigration implements ConfigMigration {
   public OverlayEnabledMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object obj2, JsonObject json3) {
      if (obj2 instanceof OverlayMod) {
         if (json3.has("enabled")) {
            return;
         }

         ThreadModuleDump9.findJsonObject(json3, "options").ifPresent(arg1x -> {
            if (!arg1x.isEmpty()) {
               json3.add("enabled", new JsonPrimitive(true));
            }
         });
      }
   }
}
