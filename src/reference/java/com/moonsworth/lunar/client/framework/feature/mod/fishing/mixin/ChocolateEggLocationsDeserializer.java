package com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import java.util.HashMap;
import org.joml.Vector3i;

public class ChocolateEggLocationsDeserializer implements JsonDeserializer<ChocolateEggLocations> {
   public ChocolateEggLocationsDeserializer() {
   }

   public ChocolateEggLocations method1(JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3) {
      JsonObject json4 = element1.getAsJsonObject();
      HashMap map5 = new HashMap();

      for (String text7 : json4.keySet()) {
         JsonObject json8 = json4.getAsJsonObject(text7);
         HashMap map9 = new HashMap();

         for (String text11 : json8.keySet()) {
            JsonObject json12 = json8.getAsJsonObject(text11);
            map9.put(text11, new ChocolateEggLocations.Data(this.method2(json12.get("pos").getAsString()), json12.get("message").getAsString()));
         }

         map5.put(text7, map9);
      }

      return new ChocolateEggLocations(map5);
   }

   private Vector3i method2(String text1) {
      String[] items2 = text1.split(",");
      int number3 = Integer.parseInt(items2[0]);
      int number4 = Integer.parseInt(items2[1]);
      int number5 = Integer.parseInt(items2[2]);
      return new Vector3i(number3, number4, number5);
   }
}
