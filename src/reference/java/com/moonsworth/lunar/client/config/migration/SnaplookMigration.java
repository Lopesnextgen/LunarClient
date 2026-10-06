package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.mod.movement.snaplook.Snaplook;
import java.util.Objects;

public class SnaplookMigration implements ConfigMigration {
   public SnaplookMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object object, JsonObject json3) {
      if (object instanceof Snaplook && json3.has("options")) {
         json3 = json3.getAsJsonObject("options");
         String text4 = "snaplookKey";
         if (json3.has(text4) && !json3.get(text4).isJsonNull()) {
            String text5 = "mode";
            String text6;
            if (json3.has(text5) && !json3.get(text5).isJsonNull()) {
               text6 = json3.get(text5).getAsString();
            } else {
               text6 = "ThirdPerson";
            }

            if (Objects.equals(text6, "Forward")) {
               json3.add("forwardPersonKey", json3.get(text4));
               json3.addProperty("thirdPersonKey", "KEY_NONE");
            } else {
               json3.add("thirdPersonKey", json3.get(text4));
               json3.addProperty("forwardPersonKey", "KEY_NONE");
            }
         }
      }
   }
}
