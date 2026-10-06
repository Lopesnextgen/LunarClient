package com.moonsworth.lunar.client.framework.feature.mod.fishing.rewindhandlers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.ItemRarity;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Map.Entry;

public class SeaCreatureRegistryDeserializer implements JsonDeserializer<SeaCreatureRegistry> {
   public SeaCreatureRegistryDeserializer() {
   }

   public SeaCreatureRegistry method1(JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3) {
      JsonObject json4 = element1.getAsJsonObject();
      ArrayList list5 = new ArrayList();

      for (Entry entry7 : json4.entrySet()) {
         JsonObject json8 = ((JsonElement)entry7.getValue()).getAsJsonObject();
         EnumSet set9 = EnumSet.noneOf(RewindhandlersType.class);

         for (JsonElement element11 : json8.getAsJsonArray("requirements")) {
            try {
               set9.add(RewindhandlersType.valueOf(element11.getAsString()));
            } catch (IllegalArgumentException illegalargumentexception13) {
            }
         }

         list5.add(
            new SeaCreature(
               (String)entry7.getKey(),
               json8.get("name").getAsString(),
               json8.get("spawnMessage").getAsString(),
               ItemRarity.valueOf(json8.get("rarity").getAsString()),
               set9
            )
         );
      }

      return new SeaCreatureRegistry(list5);
   }
}
