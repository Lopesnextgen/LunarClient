package com.moonsworth.lunar.client.replay.timeline;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.moonsworth.lunar.client.replay.project.ReplayProjectManager;
import com.moonsworth.lunar.client.replay.project.PathUtils;
import java.io.File;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import lombok.Generated;

public class MediaPoolAdapter extends TypeAdapter<MediaPool> {
   private final ReplayProjectManager field1;

   public void method1(JsonWriter jsonwriter1, MediaPool highlight_42) {
      JsonObject json3 = new JsonObject();
      JsonObject json4 = new JsonObject();

      for (Entry entry6 : highlight_42.field5.entrySet()) {
         String text7 = PathUtils.method2((File)entry6.getValue(), this.field1.method32());
         json4.addProperty(((UUID)entry6.getKey()).toString(), text7);
      }

      json3.add("media", json4);
      JsonObject json10 = new JsonObject();

      for (Entry entry13 : highlight_42.field6.entrySet()) {
         json10.addProperty(((UUID)entry13.getKey()).toString(), (String)entry13.getValue());
      }

      json3.add("folders", json10);
      JsonObject json12 = new JsonObject();

      for (Entry entry8 : highlight_42.field7.entrySet()) {
         json12.addProperty(((UUID)entry8.getKey()).toString(), ((UUID)entry8.getValue()).toString());
      }

      json3.add("parents", json12);
      JsonObject json15 = new JsonObject();

      for (Entry entry9 : highlight_42.field8.entrySet()) {
         json15.addProperty(((UUID)entry9.getKey()).toString(), (String)entry9.getValue());
      }

      json3.add("names", json15);
      this.field1.method29().toJson(json3, jsonwriter1);
   }

   public MediaPool method2(JsonReader jsonreader1) {
      MediaPool highlight_42 = new MediaPool();
      JsonObject json3 = (JsonObject)this.field1.method29().fromJson(jsonreader1, JsonObject.class);
      if (json3 == null) {
         return highlight_42;
      }

      if (json3.has("media") && json3.get("media").isJsonObject()) {
         this.method3(highlight_42, json3.getAsJsonObject("media"));
         this.method4(json3, "folders", highlight_42.field6);
         this.method4(json3, "names", highlight_42.field8);
         if (json3.has("parents") && json3.get("parents").isJsonObject()) {
            for (Entry entry5 : json3.getAsJsonObject("parents").entrySet()) {
               try {
                  highlight_42.field7.put(UUID.fromString((String)entry5.getKey()), UUID.fromString(((JsonElement)entry5.getValue()).getAsString()));
               } catch (Exception exception7) {
                  exception7.printStackTrace();
               }
            }
         }
      } else {
         this.method3(highlight_42, json3);
      }

      return highlight_42;
   }

   private void method3(MediaPool highlight_41, JsonObject json2) {
      for (Entry entry4 : json2.entrySet()) {
         File file5 = this.field1.method32().toPath().resolve(((JsonElement)entry4.getValue()).getAsString()).toFile();

         try {
            highlight_41.field5.put(UUID.fromString((String)entry4.getKey()), file5);
         } catch (Exception exception7) {
            exception7.printStackTrace();
         }
      }
   }

   private void method4(JsonObject json1, String text2, Map<UUID, String> map3) {
      if (json1.has(text2) && json1.get(text2).isJsonObject()) {
         for (Entry entry5 : json1.getAsJsonObject(text2).entrySet()) {
            try {
               map3.put(UUID.fromString((String)entry5.getKey()), ((JsonElement)entry5.getValue()).getAsString());
            } catch (Exception exception7) {
               exception7.printStackTrace();
            }
         }
      }
   }

   @Generated
   public MediaPoolAdapter(ReplayProjectManager rewind2_31) {
      this.field1 = rewind2_31;
   }
}
