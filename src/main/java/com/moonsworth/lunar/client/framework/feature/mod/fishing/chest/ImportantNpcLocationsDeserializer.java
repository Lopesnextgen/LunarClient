package com.moonsworth.lunar.client.framework.feature.mod.fishing.chest;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import org.joml.Vector3d;

public class ImportantNpcLocationsDeserializer implements JsonDeserializer<NpcLocations> {
   public ImportantNpcLocationsDeserializer() {
   }

   public NpcLocations deserialize(JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3) {
      JsonObject json4 = element1.getAsJsonObject();
      HashMap map5 = new HashMap();

      for (Entry entry7 : json4.entrySet()) {
         ArrayList list8 = new ArrayList();

         for (JsonElement element10 : ((JsonElement)entry7.getValue()).getAsJsonArray()) {
            list8.add(this.parsePosition(element10.getAsJsonObject().get("pos").getAsString()));
         }

         map5.put((String)entry7.getKey(), list8);
      }

      return new NpcLocations(map5);
   }

   private Vector3d parsePosition(String text1) {
      String[] items2 = text1.split(",");
      double value3 = Double.parseDouble(items2[0]);
      double value5 = Double.parseDouble(items2[1]);
      double value7 = Double.parseDouble(items2[2]);
      return new Vector3d(value3, value5, value7);
   }
}
