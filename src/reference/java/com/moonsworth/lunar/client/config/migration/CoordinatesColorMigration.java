package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.mod.hud.coordinates.CoordinatesHudEntry;

public class CoordinatesColorMigration implements ConfigMigration {
   public CoordinatesColorMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object obj2, JsonObject json3) {
      if (obj2 instanceof CoordinatesHudEntry) {
         if (json3.has("options")) {
            json3 = json3.getAsJsonObject("options");
            String text5 = "labelColor";
            if (json3.has(text5) && !json3.get(text5).isJsonNull() && !json3.has("bracketColor")) {
               json3.add("bracketColor", json3.getAsJsonObject(text5));
            }
         }
      } else if (obj2 instanceof Framework7Extension framework7extension4 && framework7extension4.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field1) instanceof TypedHudRenderer && json3.has("options")
         )
       {
         json3 = json3.getAsJsonObject("options");
         String text8 = "textColor";
         if (json3.has(text8) && !json3.get(text8).isJsonNull() && !json3.has("bracketColor")) {
            json3.add("bracketColor", json3.getAsJsonObject(text8));
         }
      }
   }
}
