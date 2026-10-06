package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.config.GeneralSettings;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;

public class CosmeticIdMigration implements ConfigMigration {
   public CosmeticIdMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object object, JsonObject json3) {
      if (object instanceof GeneralSettings) {
         for (CosmeticCategoryType gui2type7 : CosmeticCategoryType.values()) {
            this.method2(gui2type7.getName(), gui2type7.getShowCosmetic().getId(), json3);
         }
      }
   }

   private void method2(String text, String text2, JsonObject json3) {
      if (!text.equals(text2) && method3(text, json3)) {
         json3.add(text2, json3.get(text));
         json3.remove(text);
      }
   }

   private static boolean method3(String text, JsonObject json1) {
      return json1.has(text) && !json1.get(text).isJsonNull();
   }
}
