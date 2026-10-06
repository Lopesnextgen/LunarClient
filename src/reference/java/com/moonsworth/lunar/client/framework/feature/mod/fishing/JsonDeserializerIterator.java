package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import java.lang.reflect.Type;
import java.util.HashMap;
import toxi.util.datatypes.UndirectedGraph;

public class JsonDeserializerIterator implements JsonDeserializer<Fishing> {
   public Fishing method1(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      UndirectedGraph var4 = new UndirectedGraph();
      if (!(var1 instanceof JsonObject var5)) {
         throw new JsonParseException(var1.toString());
      } else if (!(var5.get("nodes") instanceof JsonObject var7)) {
         throw new JsonParseException(var1.toString());
      } else {
         HashMap var8 = new HashMap();

         for (String var10 : var7.keySet()) {
            if (!(var7.get(var10) instanceof JsonObject var12)) {
               throw new JsonParseException(var1.toString());
            }

            Fishing2 var13 = Fishing2.method3(var12);
            if (var13 == null) {
               throw new JsonParseException(var1.toString());
            }

            var8.put(var13.getUuid(), var13);
            var4.add(var13);
         }

         if (!(var5.get("links") instanceof JsonObject var22)) {
            throw new JsonParseException(var1.toString());
         } else {
            for (String var24 : var22.keySet()) {
               if (!var8.containsKey(var24)) {
                  throw new JsonParseException(var1.toString());
               }

               Fishing2 var25 = (Fishing2)var8.get(var24);
               JsonElement var14 = var22.get(var24);
               if (!(var14 instanceof JsonArray)) {
                  throw new JsonParseException(var1.toString());
               }

               for (JsonElement var17 : (JsonArray)var14) {
                  if (!(var17 instanceof JsonPrimitive var18)) {
                     throw new JsonParseException(var1.toString());
                  }

                  if (!var18.isString()) {
                     throw new JsonParseException(var1.toString());
                  }

                  String var19 = var18.getAsString();
                  if (!var8.containsKey(var19)) {
                     throw new JsonParseException(var1.toString());
                  }

                  Fishing2 var20 = (Fishing2)var8.get(var19);
                  var4.connect(var25, var20);
               }
            }

            return new Fishing(var4);
         }
      }
   }
}
