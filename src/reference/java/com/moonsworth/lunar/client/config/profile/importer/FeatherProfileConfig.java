package com.moonsworth.lunar.client.config.profile.importer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public final class FeatherProfileConfig {
   private static final String field1 = "**ArbitraryData**";
   private final Map<String, FeatherProfileConfig.Data> modData;

   private FeatherProfileConfig(Map<String, FeatherProfileConfig.Data> map1) {
      this.modData = map1;
   }

   public static FeatherProfileConfig parse(JsonObject json0) {
      LinkedHashMap map1 = new LinkedHashMap();

      for (Entry entry3 : json0.entrySet()) {
         if (((JsonElement)entry3.getValue()).isJsonObject()) {
            map1.put((String)entry3.getKey(), parseData(((JsonElement)entry3.getValue()).getAsJsonObject()));
         }
      }

      return new FeatherProfileConfig(map1);
   }

   public static FeatherProfileConfig.Data wrap(JsonObject json0) {
      return parseData(json0);
   }

   private static FeatherProfileConfig.Data parseData(JsonObject json0) {
      LinkedHashMap map1 = new LinkedHashMap();
      JsonObject json2 = null;

      for (Entry entry4 : json0.entrySet()) {
         String text5 = (String)entry4.getKey();
         if (text5.equals("**ArbitraryData**")) {
            if (((JsonElement)entry4.getValue()).isJsonObject()) {
               json2 = ((JsonElement)entry4.getValue()).getAsJsonObject();
            }
         } else if (!((JsonElement)entry4.getValue()).isJsonObject() && !((JsonElement)entry4.getValue()).isJsonNull()) {
            map1.put(text5, ((JsonElement)entry4.getValue()).getAsString());
         }
      }

      return new FeatherProfileConfig.Data(map1, json2);
   }

   public Set<String> getModIds() {
      return Collections.unmodifiableSet(this.modData.keySet());
   }

   public boolean has(String text1) {
      return this.modData.containsKey(text1);
   }

   public FeatherProfileConfig.Data getModData(String text1) {
      return this.modData.get(text1);
   }

   public static final class Data {
      private final Map<String, String> settings;
      private final JsonObject field1;

      Data(Map<String, String> map1) {
         this(map1, null);
      }

      Data(Map<String, String> map1, JsonObject json2) {
         this.settings = map1;
         this.ARBITRARY_DATA_KEY = json2;
      }

      public JsonObject parse() {
         return this.ARBITRARY_DATA_KEY;
      }

      public FeatherProfileConfig.Data wrap(String text1) {
         LinkedHashMap map2 = new LinkedHashMap();
         this.settings.forEach((arg2x, arg3) -> {
            if (arg2x.startsWith(text1)) {
               map2.put(arg2x.substring(text1.length()), arg3);
            }
         });
         return new FeatherProfileConfig.Data(map2);
      }

      public boolean has(String text1) {
         return this.settings.containsKey(text1);
      }

      public String parseData(String text1, String text2) {
         String text3 = this.settings.get(text1);
         return text3 == null ? text2 : text3;
      }

      public boolean getModIds() {
         return this.getModData("enabled", false);
      }

      public boolean getModData(String text1, boolean flag) {
         String text3 = this.settings.get(text1);
         return text3 == null ? flag : Boolean.parseBoolean(text3);
      }

      public float method6(String text1, float value) {
         String text3 = this.settings.get(text1);
         if (text3 != null && !text3.isEmpty()) {
            try {
               return Float.parseFloat(text3);
            } catch (NumberFormatException numberformatexception5) {
               return value;
            }
         } else {
            return value;
         }
      }

      public Integer method7(String text1) {
         String text2 = this.settings.get(text1);
         if (text2 != null && !text2.isEmpty() && !text2.equals("0")) {
            int index3 = text2.indexOf(44);
            if (index3 >= 0) {
               text2 = text2.substring(0, index3);
            }

            try {
               int number4 = Integer.parseInt(text2.trim());
               return number4 == 0 ? null : number4;
            } catch (NumberFormatException numberformatexception5) {
               return null;
            }
         } else {
            return null;
         }
      }

      public ParsedProfileColor method8(String text1) {
         return ParsedProfileColor.parse(this.settings.get(text1));
      }

      public String method9() {
         return this.settings.get("hudAnchor");
      }

      public float method10() {
         return this.method6("hudRelativeX", 0.0F);
      }

      public float method11() {
         return this.method6("hudRelativeY", 0.0F);
      }

      public float method12() {
         return this.method6("hudScale", 1.0F);
      }
   }
}
