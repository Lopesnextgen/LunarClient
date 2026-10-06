package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.mod.render.blockoutline.BlockOutline;
import com.moonsworth.lunar.client.util.math.ColorUtils;

public class BlockOutlineMigration implements ConfigMigration {
   public BlockOutlineMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object object, JsonObject json3) {
      if (object instanceof BlockOutline && json3.has("options")) {
         json3 = json3.getAsJsonObject("options");
         String text4 = "blockOverlayColor";
         if (json3.has(text4) && !json3.get(text4).isJsonNull()) {
            JsonObject json5 = json3.get(text4).getAsJsonObject();
            if (json5.has("value")) {
               int number6 = json5.get("value").getAsInt();
               number6 = ColorUtils.method18(number6, 0.25F);
               json5.addProperty("value", number6);
            }
         }
      }
   }
}
