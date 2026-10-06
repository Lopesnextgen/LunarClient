package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.mod.render.potioneffects.PotionEffects;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;

public class PotionEffectsMigration implements ConfigMigration {
   public PotionEffectsMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object object, JsonObject json3) {
      if (object instanceof PotionEffects) {
         ThreadModuleDump9.findJsonObject(json3, "options").ifPresent(arg0 -> {
            boolean flag1x = ThreadModuleDump9.findBoolean(arg0, "minimalMode").orElse(false);
            if (flag1x) {
               arg0.addProperty("potionEffectsMode", "minimal");
            }

            ThreadModuleDump9.findJsonObject(arg0, "durationColor").ifPresent(arg1xx -> {
               arg0.remove("durationColor");
               arg0.add("infoColor", arg1xx);
            });
         });
      }
   }
}
