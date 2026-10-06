package com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.CoordinatesType;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.lang.reflect.Type;
import java.util.Map.Entry;

public class MaxLevelsDeserializer implements JsonDeserializer<com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.MaxLevels> {
   public MaxLevelsDeserializer() {
   }

   public com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.MaxLevels method1(
      JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3
   ) {
      JsonObject json4 = element1.getAsJsonObject();
      Object2IntOpenHashMap object2intopenhashmap5 = new Object2IntOpenHashMap();

      for (Entry entry7 : json4.getAsJsonObject("skills").entrySet()) {
         CoordinatesType coordinatestype8 = CoordinatesType.of((String)entry7.getKey());
         object2intopenhashmap5.put(coordinatestype8, ((JsonElement)entry7.getValue()).getAsInt());
      }

      Object2IntOpenHashMap object2intopenhashmap9 = new Object2IntOpenHashMap();

      for (Entry entry11 : json4.getAsJsonObject("skills").entrySet()) {
         object2intopenhashmap9.put((String)entry11.getKey(), ((JsonElement)entry11.getValue()).getAsInt());
      }

      return new com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.MaxLevels(object2intopenhashmap5, object2intopenhashmap9);
   }
}
