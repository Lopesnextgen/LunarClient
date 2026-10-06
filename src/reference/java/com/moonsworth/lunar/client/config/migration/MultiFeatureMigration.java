package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.client.framework.loading.ItemSetHandler;
import com.moonsworth.lunar.client.framework.metadata.ModSettingsConsumer;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.replay.timeline.PropertyMap;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.AutoTextHotkeyOption;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.mod.misc.autotextactions.AutoTextHotkey;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.mod.misc.soundchanger.SoundChanger;
import com.moonsworth.lunar.client.mod.render.crosshair.Crosshair;
import com.moonsworth.lunar.client.mod.render.itemcustomizer.ItemCustomizer;
import com.moonsworth.lunar.client.mod.render.mobsize.MobSize;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map.Entry;

public class MultiFeatureMigration implements ConfigMigration {
   public MultiFeatureMigration() {
   }

   @Override
   public void method1(ConfigIdResolver killsounds1, Object obj2, JsonObject json3) {
      if (obj2 instanceof Framework7Extension framework7extension4) {
         if (method7("options", json3)) {
            JsonObject json7 = json3.getAsJsonObject("options");
            OptionContainer framework58 = (OptionContainer)framework7extension4.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field14);
            if (framework58 != null) {
               for (ClientOption lightingextension10 : framework58.method3()) {
                  this.method2(killsounds1, lightingextension10, json7);
               }
            }

            if (obj2 instanceof AutoTextHotkey) {
               for (int index26 = 0; index26 < 50; index26++) {
                  String text34 = index26 + "hotkey";
                  if (json7.get(text34) instanceof JsonObject json11) {
                     this.method3(text34, text34, json11);
                     if (method7("text", json11) && !method7("value", json11)) {
                        this.method8("text", "value", json11, json11);
                     }
                  }
               }
            }

            if (obj2 instanceof MobSize) {
               for (Entry entry35 : json7.entrySet()) {
                  String text40 = (String)entry35.getKey();
                  if (text40.endsWith("Size")) {
                     this.method5(text40, json7);
                  }
               }
            }
         }

         if (obj2 instanceof ItemCustomizer && method7("CUSTOM_DROPPED_ITEMS", json3)) {
            JsonObject json14 = json3.getAsJsonObject("CUSTOM_DROPPED_ITEMS");
            if (method7("options", json14)) {
               JsonObject json20 = json14.getAsJsonObject("options");

               for (Entry entry36 : new HashSet(json20.entrySet())) {
                  String text41 = (String)entry36.getKey();
                  if (text41.endsWith("Scale") && entry36.getValue() instanceof JsonPrimitive json46 && json46.isString()) {
                     json20.remove(text41);
                     json20.add(text41, new JsonPrimitive(Float.parseFloat(json46.getAsString())));
                  }
               }
            }
         }

         if (obj2 instanceof Skyblock && method7("sbCommandKeyBinds", json3)) {
            JsonObject json15 = json3.getAsJsonObject("sbCommandKeyBinds");

            for (Entry entry29 : json15.entrySet()) {
               if (entry29.getValue() instanceof JsonObject json37) {
                  this.method3((String)entry29.getKey(), (String)entry29.getKey(), json37);
               }
            }
         }

         if (obj2 instanceof SoundChanger) {
            OptionContainer framework516 = (OptionContainer)framework7extension4.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field14);
            if (framework516 != null) {
               for (ClientOption lightingextension30 : framework516.method3()) {
                  this.method2(killsounds1, lightingextension30, json3);
               }
            }
         }

         if (obj2 instanceof Crosshair) {
            for (Entry entry23 : json3.entrySet()) {
               if (((String)entry23.getKey()).startsWith("CROSSHAIR_")
                  && entry23.getValue() instanceof JsonObject json31
                  && json31.get("options") instanceof JsonObject json38) {
                  this.method8("help_box_open", "crosshairDraw", json38, json38);
               }
            }
         }
      } else if (obj2 instanceof ItemSetHandler foghandler5) {
         for (Object obj24 : foghandler5.method13()) {
            if (obj24 instanceof ClientOption lightingextension32) {
               this.method2(killsounds1, lightingextension32, json3);
            }
         }
      } else if (obj2 instanceof ModSettingsConsumer) {
         for (Framework7Extension framework7extension25 : Ref.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
            String text33 = killsounds1.method1(framework7extension25);
            if (method7(text33, json3)) {
               OptionContainer framework539 = (OptionContainer)framework7extension25.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field14);
               if (framework539 != null) {
                  JsonObject json45 = json3.getAsJsonObject(text33);

                  for (ClientOption lightingextension48 : framework539.method3()) {
                     this.method2(killsounds1, lightingextension48, json45);
                  }
               }
            }
         }
      } else if (obj2 instanceof PropertyMap linkedhashmapimpl6) {
         ConfigEntryMigration.method2(killsounds1, linkedhashmapimpl6, json3, (arg2x, arg3x) -> this.method2(killsounds1, arg2x, arg3x));
      }
   }

   private void method2(ConfigIdResolver killsounds1, ClientOption<?> lightingextension2, JsonObject json3) {
      if (lightingextension2 instanceof ModifierKeybindOption) {
         String text4 = killsounds1.method3(lightingextension2);
         this.method3(text4, lightingextension2.getId(), json3);
      } else if (lightingextension2 instanceof SimpleKeybindOption) {
         String text6 = killsounds1.method3(lightingextension2);
         if (method7(text6, json3)) {
            String text5 = json3.get(text6).getAsString().toUpperCase();
            if (!text5.startsWith("KEY_")) {
               text5 = "KEY_" + text5;
            }

            json3.addProperty(text6, text5);
         }
      } else if (lightingextension2 instanceof AutoTextHotkeyOption) {
         if (method7("text", json3) && !method7("value", json3)) {
            this.method8("text", "value", json3, json3);
         }
      } else {
         this.method6(killsounds1, lightingextension2, json3);
      }
   }

   private void method3(String text1, String text2, JsonObject json3) {
      JsonObject json4 = new JsonObject();
      if (json3.has(text1)) {
         JsonElement element5 = json3.get(text1);
         if (element5.isJsonObject()) {
            json3 = element5.getAsJsonObject();
         }
      }

      this.method8(text2, "value", json3, json4);
      this.method8(text2 + "Shift", "shift", json3, json4);
      this.method8(text2 + "Alt", "alt", json3, json4);
      this.method8(text2 + "Control", "control", json3, json4);
      if (!json4.isEmpty()) {
         json3.add(text1, json4);
      }
   }

   private void method4(ConfigIdResolver killsounds1, ClientOption<?> lightingextension2, JsonObject json3) {
      this.method5(killsounds1.method3(lightingextension2), json3);
   }

   private void method5(String text1, JsonObject json2) {
      if (method7(text1, json2)) {
         JsonElement element3 = json2.get(text1);
         if (element3.isJsonPrimitive() && element3.getAsJsonPrimitive().isString()) {
            try {
               double value4 = Double.parseDouble(element3.getAsString());
               json2.remove(text1);
               json2.addProperty(text1, value4);
            } catch (Exception exception6) {
            }
         }
      }
   }

   private void method6(ConfigIdResolver killsounds1, ClientOption<?> lightingextension2, JsonObject json3) {
      String text4 = lightingextension2.getId();
      if (method7(text4, json3)) {
         JsonElement element5 = json3.get(text4);
         if (element5.isJsonPrimitive() && element5.getAsJsonPrimitive().isString()) {
            if (lightingextension2.getDefaultValue() instanceof Boolean) {
               try {
                  boolean flag13 = Boolean.parseBoolean(element5.getAsString());
                  json3.remove(text4);
                  json3.addProperty(text4, flag13);
               } catch (Exception exception11) {
               }
            } else if (lightingextension2.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(OptionTraits.field7)) {
               try {
                  double value14 = Double.parseDouble(element5.getAsString());
                  json3.remove(text4);
                  json3.addProperty(text4, value14);
               } catch (Exception exception10) {
               }
            }
         } else if (element5.isJsonObject()) {
            Collection list6 = lightingextension2.method22();
            if (list6 != null) {
               JsonObject json7 = element5.getAsJsonObject();

               for (ClientOption lightingextension9 : list6) {
                  this.method6(killsounds1, lightingextension9, json7);
               }
            }
         }
      }

      for (ClientOption lightingextension15 : lightingextension2.getChildren()) {
         this.method6(killsounds1, lightingextension15, json3);
      }
   }

   private static boolean method7(String text0, JsonObject json1) {
      return json1.has(text0) && !json1.get(text0).isJsonNull();
   }

   private void method8(String text1, String text2, JsonObject json3, JsonObject json4) {
      if (method7(text1, json3)) {
         json4.add(text2, json3.remove(text1));
      }
   }
}
