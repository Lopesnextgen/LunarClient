package com.moonsworth.lunar.client.framework.feature.mod.fishing.colorsaturation;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map.Entry;

public class JsonDeserializerIterator implements JsonDeserializer<Colorsaturation> {
   public Colorsaturation deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = var1.getAsJsonObject();
      HashMap var5 = new HashMap();

      for (Entry var7 : var4.getAsJsonObject("categories").entrySet()) {
         var5.put((String)var7.getKey(), ((JsonElement)var7.getValue()).getAsString());
      }

      HashMap var9 = new HashMap();

      for (Entry var8 : var4.getAsJsonObject("xp").entrySet()) {
         var9.put((String)var8.getKey(), ((JsonElement)var8.getValue()).getAsDouble());
      }

      return new Colorsaturation(var5, var9);
   }
}
