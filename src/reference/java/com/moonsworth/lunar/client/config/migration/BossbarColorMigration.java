package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.mod.hud.bossbar.Bossbar;

public class BossbarColorMigration implements ConfigMigration {
   public BossbarColorMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object object, JsonObject json3) {
      if (object instanceof Bossbar) {
         if (json3.has("options")) {
            json3 = json3.getAsJsonObject("options");
            if (json3.has("barColor")) {
               if (!json3.has("customBossBar") || json3.get("customBossBar").getAsBoolean()) {
                  json3.addProperty("customBossBar", true);
               }
            }
         }
      }
   }
}
