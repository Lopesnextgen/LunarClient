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

public class GlaciteTunnelsDeserializer implements JsonDeserializer<GlaciteTunnelGraph> {
   public GlaciteTunnelsDeserializer() {
   }

   public GlaciteTunnelGraph method1(JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3) {
      UndirectedGraph undirectedgraph4 = new UndirectedGraph();
      if (!(element1 instanceof JsonObject json5)) {
         throw new JsonParseException(element1.toString());
      } else if (!(json5.get("nodes") instanceof JsonObject json7)) {
         throw new JsonParseException(element1.toString());
      } else {
         HashMap map8 = new HashMap();

         for (String text10 : json7.keySet()) {
            if (!(json7.get(text10) instanceof JsonObject json12)) {
               throw new JsonParseException(element1.toString());
            }

            Fishing2 fishing213 = Fishing2.method3(json12);
            if (fishing213 == null) {
               throw new JsonParseException(element1.toString());
            }

            map8.put(fishing213.getUuid(), fishing213);
            undirectedgraph4.add(fishing213);
         }

         if (!(json5.get("links") instanceof JsonObject json22)) {
            throw new JsonParseException(element1.toString());
         } else {
            for (String text24 : json22.keySet()) {
               if (!map8.containsKey(text24)) {
                  throw new JsonParseException(element1.toString());
               }

               Fishing2 fishing225 = (Fishing2)map8.get(text24);
               JsonElement element14 = json22.get(text24);
               if (!(element14 instanceof JsonArray)) {
                  throw new JsonParseException(element1.toString());
               }

               for (JsonElement element17 : (JsonArray)element14) {
                  if (!(element17 instanceof JsonPrimitive json18)) {
                     throw new JsonParseException(element1.toString());
                  }

                  if (!json18.isString()) {
                     throw new JsonParseException(element1.toString());
                  }

                  String text19 = json18.getAsString();
                  if (!map8.containsKey(text19)) {
                     throw new JsonParseException(element1.toString());
                  }

                  Fishing2 fishing220 = (Fishing2)map8.get(text19);
                  undirectedgraph4.connect(fishing225, fishing220);
               }
            }

            return new GlaciteTunnelGraph(undirectedgraph4);
         }
      }
   }
}
