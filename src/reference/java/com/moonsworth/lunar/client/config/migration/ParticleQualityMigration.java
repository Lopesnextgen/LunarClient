package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.client.config.PerformanceSettings;
import java.util.Map;

public class ParticleQualityMigration implements ConfigMigration {
   private static final Map<String, Integer> field1 = Map.of("offVanilla", 256, "high", 100, "medium", 48, "low", 28, "lowest", 16);

   public ParticleQualityMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object object, JsonObject json3) {
      if (object instanceof PerformanceSettings) {
         this.method2(json3, "entities");
         this.method2(json3, "tileEntities");
      }
   }

   private void method2(JsonObject json1, String text) {
      JsonElement element3 = json1.get(text);
      if (element3 != null && !element3.isJsonNull() && element3 instanceof JsonPrimitive json4 && !json4.isNumber()) {
         String text5 = json4.getAsString();
         Integer number6 = field1.get(text5);
         if (number6 != null) {
            json1.addProperty(text, number6);
         }
      }
   }
}
