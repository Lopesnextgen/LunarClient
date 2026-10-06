package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.mod.misc.autotextactions.AutoTextActions;
import java.util.Objects;

public class AutoTextActionsMigration implements ConfigMigration {
   public AutoTextActionsMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object object, JsonObject json3) {
      if (object instanceof AutoTextActions && json3.has("actions")) {
         for (JsonElement element6 : json3.getAsJsonArray("actions")) {
            if (element6.isJsonObject()) {
               JsonObject json7 = element6.getAsJsonObject();
               if (json7.has("action")) {
                  String text8 = json7.remove("action").getAsString();
                  if (Objects.equals(text8, "hide_msg")) {
                     json7.addProperty("hideMessage", true);
                  } else if (Objects.equals(text8, "show_title")) {
                     json7.addProperty("showTitleAction", true);
                  } else if (Objects.equals(text8, "hide_and_show_title")) {
                     json7.addProperty("hideMessage", true);
                     json7.addProperty("showTitleAction", true);
                  }
               }
            }
         }
      }
   }
}
