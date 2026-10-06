package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.mod.render.timechanger.TimeChanger;

public class TimeChangerMigration implements ConfigMigration {
   public TimeChangerMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object object, JsonObject json3) {
      if (object instanceof TimeChanger && json3.has("options")) {
         json3 = json3.getAsJsonObject("options");
         String text4 = "timeChangerTime";
         if (json3.has(text4) && !json3.get(text4).isJsonNull()) {
            int number5 = json3.get(text4).getAsInt();
            json3.addProperty(text4, number5 + 24000);
         }
      }
   }
}
