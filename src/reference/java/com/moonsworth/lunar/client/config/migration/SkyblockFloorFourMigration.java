package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import java.util.Map.Entry;

public class SkyblockFloorFourMigration implements ConfigMigration {
   public SkyblockFloorFourMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object object, JsonObject json3) {
      if (object instanceof Skyblock) {
         JsonObject json4 = new JsonObject();
         JsonObject json5 = new JsonObject();
         boolean flag6 = false;
         if (json3.has("SKYBLOCK_HIGHLIGHT_SPIRIT_BOW")) {
            JsonObject json7 = json3.getAsJsonObject("SKYBLOCK_HIGHLIGHT_SPIRIT_BOW");
            boolean flag8 = json7.has("enabled") && json7.get("enabled").getAsBoolean();
            flag6 |= flag8;
            json5.addProperty("highlightSpiritBow", flag8);
            if (json7.has("options")) {
               JsonObject json9 = json7.getAsJsonObject("options");
               json9.entrySet().forEach(arg1x -> json5.add((String)arg1x.getKey(), (JsonElement)arg1x.getValue()));
            }

            json3.remove("SKYBLOCK_HIGHLIGHT_SPIRIT_BOW");
         }

         if (json3.has("SKYBLOCK_SPIRIT_BEAR_TIMER_HUD")) {
            JsonObject json13 = json3.getAsJsonObject("SKYBLOCK_SPIRIT_BEAR_TIMER_HUD");
            boolean flag14 = json13.has("enabled") && json13.get("enabled").getAsBoolean();
            flag6 |= flag14;
            json5.addProperty("spiritBearTimerHud", flag14);
            if (json13.has("options")) {
               JsonObject json15 = json13.getAsJsonObject("options");

               for (Entry entry11 : json15.entrySet()) {
                  String text12 = (String)entry11.getKey();
                  text12 = text12.equals("spiritBearColor") ? "spiritBearTimerColor" : text12;
                  json5.add(text12, (JsonElement)entry11.getValue());
               }
            }

            json3.remove("SKYBLOCK_SPIRIT_BEAR_TIMER_HUD");
         }

         json4.addProperty("enabled", flag6);
         json4.add("options", json5);
         json3.add("SKYBLOCK_FLOOR_FOUR", json4);
      }
   }
}
