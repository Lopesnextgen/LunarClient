package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.mod.render.lighting.Lighting;

public class FullBrightMigration implements ConfigMigration {
   public FullBrightMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object object, JsonObject json3) {
      if (object instanceof Lighting) {
         if (json3.has("options")) {
            json3 = json3.getAsJsonObject("options");
            if (json3.has("fullBright")) {
               try {
                  KeyCode.valueOf(json3.get("fullBright").getAsString());
               } catch (IllegalArgumentException | NullPointerException illegalargumentexception5) {
                  return;
               }

               this.method2("", json3);
               this.method2("Shift", json3);
               this.method2("Control", json3);
               this.method2("Alt", json3);
            }
         }
      }
   }

   private void method2(String text, JsonObject json2) {
      String text3 = "fullBright" + text;
      if (json2.has(text3)) {
         String text4 = "fullBrightToggle" + text;
         json2.add(text4, json2.remove(text3));
      }
   }
}
