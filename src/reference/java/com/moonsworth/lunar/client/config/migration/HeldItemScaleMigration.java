package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.mod.render.onesevenvisuals.OneSevenVisuals;
import com.moonsworth.lunar.client.util.io.ThreadModuleDump9;
import java.util.ArrayList;
import java.util.Map.Entry;

public class HeldItemScaleMigration implements ConfigMigration {
   public HeldItemScaleMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object obj2, JsonObject json3) {
      if (obj2 instanceof ModsSettings) {
         this.method5(json3);
         this.method2(json3);
      } else if (obj2 instanceof OneSevenVisuals) {
         this.method4(json3);
      }
   }

   private void method2(JsonObject json1) {
      ThreadModuleDump9.findJsonObject(json1, "OVERLAY_MOD").flatMap(arg0 -> ThreadModuleDump9.findJsonObject(arg0, "options")).ifPresent(arg2 -> {
         if (arg2.has("heldItemScale") || arg2.has("totemScale")) {
            boolean flag3 = ThreadModuleDump9.findBoolean(json1.getAsJsonObject("OVERLAY_MOD"), "enabled").orElse(false);
            JsonObject json4 = this.method6(json1, "ITEM_CUSTOMIZER");
            if (flag3) {
               this.method8(json4, true);
            }

            JsonObject json5 = this.method6(json4, "CUSTOM_HELD_ITEMS");
            if (flag3) {
               this.method8(json5, true);
            }

            JsonObject json6 = this.method6(json5, "individualConfigs");
            ThreadModuleDump9.findFloat(arg2, "heldItemScale").ifPresent(arg3x -> {
               JsonObject json4x = this.method6(json6, "global");
               this.method3(json4x, arg3x, flag3);
            });
            ThreadModuleDump9.findFloat(arg2, "totemScale").ifPresent(arg3x -> {
               JsonObject json4x = this.method6(json6, "minecraft:totem_of_undying");
               this.method3(json4x, arg3x, flag3);
            });
         }
      });
   }

   private void method3(JsonObject json1, float value2, boolean flag3) {
      if (flag3) {
         this.method8(json1, true);
      }

      this.method7(json1, "splitHands", new JsonPrimitive(true));
      JsonObject json4 = this.method6(json1, "leftHand");
      if (flag3) {
         this.method7(json4, "leftHand", new JsonPrimitive(true));
      }

      this.method7(json4, "scale", new JsonPrimitive(String.valueOf(value2)));
      JsonObject json5 = this.method6(json1, "rightHand");
      if (flag3) {
         this.method7(json5, "rightHand", new JsonPrimitive(true));
      }

      this.method7(json5, "scale", new JsonPrimitive(String.valueOf(value2)));
   }

   private void method4(JsonObject json1) {
      boolean flag2 = ThreadModuleDump9.findBoolean(json1, "enabled").orElse(true);
      JsonObject json3 = this.method6(json1, "ONE_SEVEN_ITEMS_LEGACY");
      this.method8(json3, flag2);
      JsonObject json4 = this.method6(json1, "ONE_SEVEN_ANIMATIONS_LEGACY");
      this.method8(json4, flag2);
      ThreadModuleDump9.findJsonObject(json1, "options")
         .ifPresent(
            arg3x -> {
               for (Entry entry5 : new ArrayList(arg3x.entrySet())) {
                  JsonElement element6 = (JsonElement)entry5.getValue();
                  if (element6.isJsonPrimitive() && ("1.8".equals(element6.getAsString()) || "1.7".equals(element6.getAsString()))) {
                     JsonPrimitive json9 = new JsonPrimitive("1.7".equals(element6.getAsString()));
                     String text7 = (String)entry5.getKey();
                     arg3x.remove(text7);
                     JsonObject json8;
                     if ((!text7.endsWith("Animation") || text7.equals("blockHitAnimation")) && !text7.equals("hurtCameraShake")) {
                        if (!text7.startsWith("firstPerson")
                           && !text7.equals("thirdPersonHeldItems")
                           && !text7.equals("itemTransforms")
                           && !text7.equals("blockHitAnimation")) {
                           json8 = arg3x;
                        } else {
                           json8 = this.method6(json3, "options");
                        }
                     } else {
                        json8 = this.method6(json4, "options");
                     }

                     this.method7(json8, text7, json9);
                  }
               }
            }
         );
   }

   private void method5(JsonObject json1) {
      if (json1.has("UHC_OVERLAY") && json1.get("UHC_OVERLAY").isJsonObject()) {
         JsonObject json2 = json1.remove("UHC_OVERLAY").getAsJsonObject();
         ThreadModuleDump9.findJsonObject(json2, "options").ifPresent(arg3 -> {
            JsonObject json4 = this.method6(json1, "ITEM_CUSTOMIZER");
            JsonObject json5 = this.method6(json4, "CUSTOM_DROPPED_ITEMS");
            boolean flag6 = ThreadModuleDump9.findBoolean(json2, "enabled").orElse(false);
            if (flag6) {
               this.method8(json4, flag6);
               this.method8(json5, flag6);
            }

            JsonObject json7 = this.method6(json5, "options");
            ThreadModuleDump9.findJsonArray(arg3, "selectedItems").ifPresent(arg2xx -> this.method7(json7, "selectedItems", arg2xx));

            for (Entry entry9 : arg3.entrySet()) {
               String text10 = (String)entry9.getKey();
               JsonElement element11 = (JsonElement)entry9.getValue();
               if (text10.endsWith("Scale") && element11.isJsonPrimitive()) {
                  this.method7(json7, text10, element11);
               }
            }
         });
      }
   }

   private JsonObject method6(JsonObject json1, String text2) {
      return ThreadModuleDump9.findJsonObject(json1, text2).orElseGet(() -> {
         JsonObject json2x = new JsonObject();
         json1.add(text2, json2x);
         return json2x;
      });
   }

   private void method7(JsonObject json1, String text2, JsonElement element3) {
      if (json1.has(text2)) {
         json1.remove(text2);
      }

      json1.add(text2, element3);
   }

   private void method8(JsonObject json1, boolean flag2) {
      this.method7(json1, "enabled", new JsonPrimitive(flag2));
   }
}
