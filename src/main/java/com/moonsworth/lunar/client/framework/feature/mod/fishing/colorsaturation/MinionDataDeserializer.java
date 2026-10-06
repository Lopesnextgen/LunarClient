package com.moonsworth.lunar.client.framework.feature.mod.fishing.colorsaturation;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map.Entry;

public class MinionDataDeserializer implements JsonDeserializer<MinionXpData> {
   public MinionDataDeserializer() {
   }

   public MinionXpData deserialize(JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3) {
      JsonObject json4 = element1.getAsJsonObject();
      HashMap map5 = new HashMap();

      for (Entry entry7 : json4.getAsJsonObject("categories").entrySet()) {
         map5.put((String)entry7.getKey(), ((JsonElement)entry7.getValue()).getAsString());
      }

      HashMap map9 = new HashMap();

      for (Entry entry8 : json4.getAsJsonObject("xp").entrySet()) {
         map9.put((String)entry8.getKey(), ((JsonElement)entry8.getValue()).getAsDouble());
      }

      return new MinionXpData(map5, map9);
   }
}
