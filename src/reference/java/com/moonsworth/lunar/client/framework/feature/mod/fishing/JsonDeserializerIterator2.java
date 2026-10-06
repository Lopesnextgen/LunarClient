package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.FishingType2;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import org.joml.Vector3i;

public class JsonDeserializerIterator2 implements JsonDeserializer<com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.Fishing> {
   public com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.Fishing method1(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      if (!(var1 instanceof JsonObject var4)) {
         throw new JsonParseException(var1.toString());
      } else {
         com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.Fishing var5 = new com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin.Fishing(
            new EnumMap<>(FishingType2.class)
         );

         for (FishingType2 var9 : FishingType2.values()) {
            HashSet var10 = new HashSet();
            JsonElement var11 = var4.get(var9.getId());
            if (var11 == null) {
               throw new JsonParseException(var1.toString());
            }

            if (!(var11 instanceof JsonArray var12)) {
               throw new JsonParseException(var1.toString());
            }

            Iterator var13 = var12.iterator();

            while (true) {
               if (var13.hasNext()) {
                  JsonElement var14 = (JsonElement)var13.next();
                  if (!(var14 instanceof JsonObject var15)) {
                     throw new JsonParseException(var1.toString());
                  }

                  JsonElement var16 = var15.get("x");
                  JsonElement var17 = var15.get("y");
                  JsonElement var18 = var15.get("z");
                  if (var16 != null && var17 != null && var18 != null) {
                     if (var16.isJsonPrimitive() && var17.isJsonPrimitive() && var18.isJsonPrimitive()) {
                        Vector3i var19 = new Vector3i(var16.getAsInt(), var17.getAsInt(), var18.getAsInt());
                        var10.add(var19);
                        continue;
                     }

                     throw new JsonParseException(var1.toString());
                  }

                  throw new JsonParseException(var1.toString());
               }

               var5.method1().put(var9, var10);
               break;
            }
         }

         return var5;
      }
   }
}
