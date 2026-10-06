package com.moonsworth.lunar.client.framework.feature.tps;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map.Entry;

public class TpsDeserializer implements JsonDeserializer<Tps> {
   public TpsDeserializer() {
   }

   public Tps method1(JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3) {
      if (!element1.isJsonObject()) {
         return new Tps(new String[0], new HashMap<>());
      }

      JsonObject json4 = element1.getAsJsonObject();
      String[] items5 = new String[0];
      HashMap map6 = new HashMap();

      for (Entry entry8 : json4.entrySet()) {
         if (((JsonElement)entry8.getValue()).isJsonArray() && ((String)entry8.getKey()).equals("__values__")) {
            items5 = (String[])jsondeserializationcontext3.deserialize((JsonElement)entry8.getValue(), String[].class);
         } else if (((JsonElement)entry8.getValue()).isJsonObject()) {
            Tps tps9 = (Tps)jsondeserializationcontext3.deserialize((JsonElement)entry8.getValue(), Tps.class);

            for (String text13 : ((String)entry8.getKey()).split("\\|")) {
               map6.put(text13, tps9);
            }
         }
      }

      return new Tps(items5, map6);
   }
}
