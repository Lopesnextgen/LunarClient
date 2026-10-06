package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.replay.timeline.KeyframeProperty;
import com.moonsworth.lunar.client.replay.timeline.PropertyMap;
import com.moonsworth.lunar.client.replay.timeline.PropertyGroup;
import com.moonsworth.lunar.client.config.option.ClientOption;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

public class ConfigEntryMigration {
   private static final String[] field1 = new String[]{"x", "y", "position"};

   public ConfigEntryMigration() {
   }

   public static void method1(ConfigIdResolver killsounds0, Framework7Extension framework7extension1, JsonObject json2) {
      String text3 = killsounds0.method1(framework7extension1);
      if (!json2.has(text3)) {
         JsonObject json4 = new JsonObject();
         if (json2.has("enabled") && !json2.get("enabled").isJsonNull()) {
            json4.add("enabled", json2.get("enabled"));
         }

         if (framework7extension1.ICOOHRIORIOOIIRRIHHOOIOHHCORIR(ModTraits.field1)) {
            for (String text8 : field1) {
               if (json2.has(text8) && !json2.get(text8).isJsonNull()) {
                  json4.add(text8, json2.get(text8));
               }
            }
         }

         if (json2.has("options")) {
            JsonObject json11 = json2.getAsJsonObject("options");
            JsonObject json12 = new JsonObject();
            OptionContainer framework513 = (OptionContainer)framework7extension1.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field14);
            if (framework513 != null) {
               for (ClientOption lightingextension9 : framework513.method2()) {
                  String text10 = killsounds0.method3(lightingextension9);
                  if (json11.has(text10) && !json11.get(text10).isJsonNull()) {
                     json12.add(text10, json11.get(text10));
                  }
               }
            }

            json4.add("options", json12);
         }

         json2.add(text3, json4);
      }
   }

   public static void method2(ConfigIdResolver killsounds0, PropertyMap linkedhashmapimpl1, JsonObject json2, BiConsumer<ClientOption<?>, JsonObject> biconsumer3) {
      for (Entry entry5 : json2.entrySet()) {
         PropertyGroup fishing2iterator6 = (PropertyGroup)linkedhashmapimpl1.get(entry5.getKey());
         if (fishing2iterator6 != null) {
            JsonElement element7 = (JsonElement)entry5.getValue();
            if (element7.isJsonObject()) {
               method3(fishing2iterator6, element7.getAsJsonObject(), biconsumer3);
            }
         }
      }
   }

   private static void method3(PropertyGroup fishing2iterator0, JsonObject json1, BiConsumer<ClientOption<?>, JsonObject> biconsumer2) {
      if (json1.has("childProperties")) {
         JsonObject json3 = json1.getAsJsonObject("childProperties");

         for (Entry entry5 : json3.entrySet()) {
            PropertyGroup fishing2iterator6 = (PropertyGroup)fishing2iterator0.method11().get(entry5.getKey());
            if (fishing2iterator6 != null && ((JsonElement)entry5.getValue()).isJsonObject()) {
               method3(fishing2iterator6, ((JsonElement)entry5.getValue()).getAsJsonObject(), biconsumer2);
            }
         }
      }

      if (json1.has("keyframes")) {
         JsonObject json11 = json1.getAsJsonObject("keyframes");

         for (Entry entry13 : json11.entrySet()) {
            KeyframeProperty fishing2loader14 = (KeyframeProperty)fishing2iterator0.method12().get(entry13.getKey());
            if (fishing2loader14 != null && ((JsonElement)entry13.getValue()).isJsonObject()) {
               JsonObject json7 = ((JsonElement)entry13.getValue()).getAsJsonObject();
               if (json7.has("values")) {
                  JsonObject json8 = json7.getAsJsonObject("values");

                  for (Entry entry10 : json8.entrySet()) {
                     if (((JsonElement)entry10.getValue()).isJsonObject()) {
                        biconsumer2.accept(fishing2loader14.getOption(), ((JsonElement)entry10.getValue()).getAsJsonObject());
                     }
                  }
               }
            }
         }
      }
   }
}
