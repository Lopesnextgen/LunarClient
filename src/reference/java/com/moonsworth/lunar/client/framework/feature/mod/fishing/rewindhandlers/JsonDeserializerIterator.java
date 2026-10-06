package com.moonsworth.lunar.client.framework.feature.mod.fishing.rewindhandlers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.GuiType3;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Map.Entry;

public class JsonDeserializerIterator implements JsonDeserializer<Rewindhandlers2> {
   public Rewindhandlers2 method1(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = var1.getAsJsonObject();
      ArrayList var5 = new ArrayList();

      for (Entry var7 : var4.entrySet()) {
         JsonObject var8 = ((JsonElement)var7.getValue()).getAsJsonObject();
         EnumSet var9 = EnumSet.noneOf(RewindhandlersType.class);

         for (JsonElement var11 : var8.getAsJsonArray("requirements")) {
            try {
               var9.add(RewindhandlersType.valueOf(var11.getAsString()));
            } catch (IllegalArgumentException var13) {
            }
         }

         var5.add(
            new Rewindhandlers(
               (String)var7.getKey(),
               var8.get("name").getAsString(),
               var8.get("spawnMessage").getAsString(),
               GuiType3.valueOf(var8.get("rarity").getAsString()),
               var9
            )
         );
      }

      return new Rewindhandlers2(var5);
   }
}
