package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.client.mod.hud.directionhud.DirectionHud;
import com.moonsworth.lunar.client.mod.hud.itemcounter.ItemCounter;
import com.moonsworth.lunar.client.mod.hud.scoreboard.Scoreboard;
import com.moonsworth.lunar.client.mod.render.crosshair.Crosshair;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import org.jspecify.annotations.Nullable;

public class HudOptionMigration implements ConfigMigration {
   public HudOptionMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object obj2, JsonObject json3) {
      if (obj2 instanceof Scoreboard scoreboard4) {
         this.method2(killsounds1, scoreboard4, json3);
      } else if (obj2 instanceof ItemCounter) {
         this.method3(json3);
      } else if (obj2 instanceof DirectionHud) {
         this.method4(json3);
      } else if (obj2 instanceof Crosshair crosshair5) {
         this.method5(killsounds1, crosshair5, json3);
      }
   }

   private void method2(ConfigIdResolver killsounds1, Scoreboard scoreboard2, JsonObject json3) {
      if (json3.has("options")) {
         JsonObject json4 = json3.getAsJsonObject("options");
         String text5 = killsounds1.method3(scoreboard2.method14());
         String text6 = killsounds1.method3(scoreboard2.method13());
         if (!json4.has(text5) && json4.has(text6)) {
            json4.add(text5, json4.get(text6));
         }
      }
   }

   private void method3(JsonObject json1) {
      if (json1.has("options")) {
         JsonObject json2 = json1.getAsJsonObject("options");
         if (json2.has("scale")) {
            Double value3 = this.method8(json2, "scale");
            if (value3 != null) {
               json2.addProperty("childScale", value3);
            }
         }
      }
   }

   private void method4(JsonObject json1) {
      if (json1.has("options")) {
         JsonObject json2 = json1.getAsJsonObject("options");
         if (!json2.has("hudStyle") && json2.has("useLegacyStyle")) {
            boolean flag3 = json2.remove("useLegacyStyle").getAsBoolean();
            if (flag3) {
               json2.addProperty("hudStyle", "legacy");
            }
         }
      }
   }

   private void method5(ConfigIdResolver killsounds1, Crosshair crosshair2, JsonObject json3) {
      if (json3.has("options")) {
         JsonObject json4 = json3.remove("options").getAsJsonObject();
         String text5 = null;
         if (json3.has("custom_crosshair")) {
            text5 = json3.remove("custom_crosshair").getAsString();
         }

         String text6 = "crosshairColorVanilla";
         if (json4.has("crosshairColor")) {
            text6 = json4.remove("crosshairColor").getAsString();
         }

         json4.addProperty(killsounds1.method3(crosshair2.field8.method28()), "crosshairColorVanilla".equals(text6));
         if (json4.has("crosshairSize")) {
            Double value7 = this.method8(json4, "crosshairSize");
            if (value7 != null) {
               json4.addProperty("crosshairWidth", value7.intValue());
               json4.addProperty("crosshairHeight", value7.intValue());
            }
         }

         if (json4.has("crosshairThickness")) {
            Double value10 = this.method8(json4, "crosshairThickness");
            if (value10 != null) {
               json4.addProperty("dotSize", value10.intValue() * 3);
            }
         }

         JsonObject json11 = ThreadModuleDump9.takeJsonObjectOrNull(json4, "friendlyColor");
         JsonObject json8 = ThreadModuleDump9.takeJsonObjectOrNull(json4, "enemyColor");
         if ("crosshairColorDynamic".equals(text6)) {
            if (json11 != null) {
               JsonObject json9 = json4.deepCopy();
               this.method6(json11, json9);
               json3.add(killsounds1.method1(crosshair2.field9), this.method7(json9, text5));
            }

            if (json8 != null) {
               JsonObject json12 = json4.deepCopy();
               this.method6(json8, json12);
               json3.add(killsounds1.method1(crosshair2.field10), this.method7(json12, text5));
            }
         }

         json3.add(killsounds1.method1(crosshair2.field8), this.method7(json4.deepCopy(), text5));
      }
   }

   private void method6(JsonObject json1, JsonObject json2) {
      if (json2.has("color")) {
         json2.remove("color");
      }

      json2.add("color", json1);
   }

   private JsonObject method7(JsonObject json1, String text2) {
      JsonObject json3 = new JsonObject();
      json3.addProperty("enabled", true);
      json3.add("options", json1);
      if (text2 != null) {
         json3.addProperty("customCrosshair", text2);
      }

      return json3;
   }

   private @Nullable Double method8(JsonObject json1, String text2) {
      JsonElement element3 = json1.remove(text2);
      if (element3 instanceof JsonPrimitive json4) {
         return json4.isString() ? Double.parseDouble(element3.getAsString()) : element3.getAsDouble();
      } else {
         return null;
      }
   }
}
