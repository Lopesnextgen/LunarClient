package com.moonsworth.lunar.client.replay.timeline;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.moonsworth.lunar.client.replay.timeline.KeyframeProperty;
import com.moonsworth.lunar.client.replay.project.ReplayProjectManager;
import com.moonsworth.lunar.client.replay.timeline.UndoRedoManager;
import com.moonsworth.lunar.client.replay.timeline.PropertyGroup;
import com.moonsworth.lunar.client.config.migration.ConfigMigrator;
import java.util.Map.Entry;
import lombok.Generated;

public class PropertyMap extends com.moonsworth.lunar.client.replay.timeline.LinkedHashMapImpl<String, PropertyGroup> {
   public PropertyMap(UndoRedoManager nameplate21) {
      super(nameplate21);
   }

   public static class PropertyMapAdapter extends TypeAdapter<PropertyMap> {
      private final ReplayProjectManager field1;

      public void method1(JsonWriter jsonwriter1, PropertyMap linkedhashmapimpl2) {
         JsonObject json3 = new JsonObject();

         for (Entry entry5 : linkedhashmapimpl2.entrySet()) {
            json3.add((String)entry5.getKey(), this.method2((PropertyGroup)entry5.getValue()));
         }

         this.field1.method29().toJson(json3, jsonwriter1);
      }

      private JsonObject method2(PropertyGroup fishing2iterator1) {
         JsonObject json2 = new JsonObject();
         if (fishing2iterator1.isEnabled() != fishing2iterator1.method13()) {
            json2.addProperty("enabled", fishing2iterator1.isEnabled());
         }

         if (fishing2iterator1.isExtended()) {
            json2.addProperty("extended", true);
         }

         JsonObject json3 = new JsonObject();

         for (PropertyGroup fishing2iterator5 : fishing2iterator1.method11().values()) {
            JsonObject json6 = this.method2(fishing2iterator5);
            if (fishing2iterator1.method17() || !json6.isEmpty()) {
               json3.add(fishing2iterator5.type(), json6);
            }
         }

         if (!json3.isEmpty()) {
            json2.add("childProperties", json3);
         }

         JsonObject json8 = new JsonObject();

         for (KeyframeProperty fishing2loader10 : fishing2iterator1.method12().values()) {
            JsonObject json7 = new JsonObject();
            fishing2loader10.method17(json7);
            if (!json7.isEmpty() || fishing2iterator1.method18()) {
               json8.add(fishing2loader10.type(), json7);
            }
         }

         if (!json8.isEmpty()) {
            json2.add("keyframes", json8);
         }

         return json2;
      }

      public PropertyMap method3(JsonReader jsonreader1) {
         PropertyMap linkedhashmapimpl2 = new PropertyMap(this.field1.method40());
         JsonObject json3 = (JsonObject)this.field1.method29().fromJson(jsonreader1, JsonObject.class);
         ConfigMigrator.method3(linkedhashmapimpl2, json3);

         for (Entry entry5 : json3.entrySet()) {
            PropertyGroup fishing2iterator6 = this.field1.method38().method2(this.field1.method40(), (String)entry5.getKey());
            if (fishing2iterator6 != null) {
               fishing2iterator6.method19(false);
               this.method4(fishing2iterator6, ((JsonElement)entry5.getValue()).getAsJsonObject());
               linkedhashmapimpl2.method3((String)entry5.getKey(), fishing2iterator6);
            }
         }

         return linkedhashmapimpl2;
      }

      private void method4(PropertyGroup fishing2iterator1, JsonObject json2) {
         if (json2.has("enabled")) {
            fishing2iterator1.setEnabled(json2.get("enabled").getAsBoolean());
         }

         if (json2.has("extended")) {
            fishing2iterator1.method19(json2.get("extended").getAsBoolean());
         }

         if (json2.has("childProperties")) {
            JsonObject json3 = json2.getAsJsonObject("childProperties");

            for (Entry entry5 : json3.entrySet()) {
               PropertyGroup fishing2iterator6 = fishing2iterator1.method11().get(entry5.getKey());
               if (fishing2iterator6 == null) {
                  if (!fishing2iterator1.method17()) {
                     continue;
                  }

                  fishing2iterator6 = this.field1.method38().method2(this.field1.method40(), (String)entry5.getKey());
                  if (fishing2iterator6 == null) {
                     continue;
                  }

                  fishing2iterator1.method11().put((String)entry5.getKey(), fishing2iterator6);
               }

               JsonObject json7 = ((JsonElement)entry5.getValue()).getAsJsonObject();
               if (!json7.isEmpty()) {
                  this.method4(fishing2iterator6, json7);
               }
            }
         }

         if (json2.has("keyframes")) {
            JsonObject json8 = json2.getAsJsonObject("keyframes");

            for (Entry entry10 : json8.entrySet()) {
               KeyframeProperty fishing2loader11 = fishing2iterator1.method12().get(entry10.getKey());
               if (fishing2loader11 == null) {
                  if (!fishing2iterator1.method18()) {
                     continue;
                  }

                  fishing2loader11 = this.field1.method39().method2((String)entry10.getKey());
                  if (fishing2loader11 == null) {
                     continue;
                  }

                  fishing2iterator1.method12().put((String)entry10.getKey(), fishing2loader11);
               }

               JsonObject json12 = ((JsonElement)entry10.getValue()).getAsJsonObject();
               if (!json12.isEmpty()) {
                  fishing2loader11.load(json12);
               }
            }
         }
      }

      @Generated
      public PropertyMapAdapter(ReplayProjectManager rewind2_31) {
         this.field1 = rewind2_31;
      }
   }
}
