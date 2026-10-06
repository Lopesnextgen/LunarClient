package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map.Entry;
import org.joml.Vector3i;

public class FairySoulLocationsDeserializer implements JsonDeserializer<FairySoulLocations> {
   public FairySoulLocationsDeserializer() {
   }

   public FairySoulLocations method1(JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3) {
      EnumMap map4 = new EnumMap<>(SkyblockIsland.class);

      for (Entry entry6 : element1.getAsJsonObject().entrySet()) {
         SkyblockIsland gui2extension37;
         try {
            gui2extension37 = SkyblockIsland.valueOf((String)entry6.getKey());
         } catch (IllegalArgumentException illegalargumentexception12) {
            continue;
         }

         HashSet set8 = new HashSet();

         for (JsonElement element10 : ((JsonElement)entry6.getValue()).getAsJsonArray()) {
            JsonObject json11 = element10.getAsJsonObject();
            set8.add(new Vector3i(json11.get("x").getAsInt(), json11.get("y").getAsInt(), json11.get("z").getAsInt()));
         }

         map4.put(gui2extension37, set8);
      }

      return new FairySoulLocations(map4);
   }
}
