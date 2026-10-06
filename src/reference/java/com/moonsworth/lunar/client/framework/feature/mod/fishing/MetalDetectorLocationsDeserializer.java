package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.MetalDetectorTreasureType;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import org.joml.Vector3i;

public class MetalDetectorLocationsDeserializer implements JsonDeserializer<com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.MetalDetectorTreasureMap> {
   public MetalDetectorLocationsDeserializer() {
   }

   public com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.MetalDetectorTreasureMap method1(JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3) {
      if (!(element1 instanceof JsonObject json4)) {
         throw new JsonParseException(element1.toString());
      } else {
         com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.MetalDetectorTreasureMap fishing5 = new com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.MetalDetectorTreasureMap(
            new EnumMap<>(MetalDetectorTreasureType.class)
         );

         for (MetalDetectorTreasureType fishingtype29 : MetalDetectorTreasureType.values()) {
            HashSet set10 = new HashSet();
            JsonElement element11 = json4.get(fishingtype29.getId());
            if (element11 == null) {
               throw new JsonParseException(element1.toString());
            }

            if (!(element11 instanceof JsonArray array12)) {
               throw new JsonParseException(element1.toString());
            }

            Iterator iterator13 = array12.iterator();

            while (true) {
               if (iterator13.hasNext()) {
                  JsonElement element14 = (JsonElement)iterator13.next();
                  if (!(element14 instanceof JsonObject json15)) {
                     throw new JsonParseException(element1.toString());
                  }

                  JsonElement element16 = json15.get("x");
                  JsonElement element17 = json15.get("y");
                  JsonElement element18 = json15.get("z");
                  if (element16 != null && element17 != null && element18 != null) {
                     if (element16.isJsonPrimitive() && element17.isJsonPrimitive() && element18.isJsonPrimitive()) {
                        Vector3i vector3i19 = new Vector3i(element16.getAsInt(), element17.getAsInt(), element18.getAsInt());
                        set10.add(vector3i19);
                        continue;
                     }

                     throw new JsonParseException(element1.toString());
                  }

                  throw new JsonParseException(element1.toString());
               }

               fishing5.method1().put(fishingtype29, set10);
               break;
            }
         }

         return fishing5;
      }
   }
}
