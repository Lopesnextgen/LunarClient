package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import it.unimi.dsi.fastutil.Pair;
import java.util.List;

public class UhcOverlayMigration implements ConfigMigration {
   public UhcOverlayMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object obj2, JsonObject json3) {
      if (obj2 instanceof ModsSettings) {
         ThreadModuleDump9.findJsonObject(json3, "UHC_OVERLAY")
            .flatMap(arg0 -> ThreadModuleDump9.findJsonObject(arg0, "options"))
            .ifPresent(
               arg0 -> {
                  JsonArray array1x = new JsonArray();
                  String text2x = "skullScale";
                  if (arg0.has(text2x) && !arg0.get(text2x).isJsonNull()) {
                     float value3x = arg0.remove(text2x).getAsFloat();
                     arg0.addProperty("playerHeadScale", value3x);
                     array1x.add("minecraft:player_head");
                  }

                  for (Pair pair4 : List.of(
                     Pair.of("minecraft:gold_ingot", "goldIngotScale"),
                     Pair.of("minecraft:gold_nugget", "goldNuggetScale"),
                     Pair.of("minecraft:gold_ore", "goldOreScale"),
                     Pair.of("minecraft:golden_apple", "goldAppleScale")
                  )) {
                     String text5 = (String)pair4.second();
                     if (arg0.has(text5) && !arg0.get(text5).isJsonNull()) {
                        array1x.add((String)pair4.first());
                     }
                  }

                  if (!array1x.isEmpty()) {
                     arg0.add("selectedItems", array1x);
                  }
               }
            );
      }
   }
}
