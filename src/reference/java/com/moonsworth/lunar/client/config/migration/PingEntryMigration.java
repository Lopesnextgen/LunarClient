package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.mod.hud.ping.Ping;

public class PingEntryMigration implements ConfigMigration {
   public PingEntryMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object object, JsonObject json3) {
      if (object instanceof Ping ping4) {
         ConfigEntryMigration.method1(killsounds1, ping4.method16(), json3);
      }
   }
}
