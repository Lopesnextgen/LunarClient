package com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.CoordinatesType;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.lang.reflect.Type;
import java.util.Map.Entry;

public class JsonDeserializerIterator implements JsonDeserializer<com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.Coordinates> {
   public com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.Coordinates deserialize(
      JsonElement var1, Type var2, JsonDeserializationContext var3
   ) {
      JsonObject var4 = var1.getAsJsonObject();
      Object2IntOpenHashMap var5 = new Object2IntOpenHashMap();

      for (Entry var7 : var4.getAsJsonObject("skills").entrySet()) {
         CoordinatesType var8 = CoordinatesType.of((String)var7.getKey());
         var5.put(var8, ((JsonElement)var7.getValue()).getAsInt());
      }

      Object2IntOpenHashMap var9 = new Object2IntOpenHashMap();

      for (Entry var11 : var4.getAsJsonObject("skills").entrySet()) {
         var9.put((String)var11.getKey(), ((JsonElement)var11.getValue()).getAsInt());
      }

      return new com.moonsworth.lunar.client.framework.feature.mod.fishing.coordinates.mixin.Coordinates(var5, var9);
   }
}
