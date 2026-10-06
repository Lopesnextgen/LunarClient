package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.mod.hud.bossbar.Bossbar;

public class BossbarPositionMigration implements ConfigMigration {
   public BossbarPositionMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object object, JsonObject json3) {
      if (object instanceof Bossbar && json3.has("y")) {
         double value4 = json3.get("y").getAsDouble();
         if (value4 == 40.0) {
            json3.addProperty("y", 12.0);
         }
      }
   }
}
