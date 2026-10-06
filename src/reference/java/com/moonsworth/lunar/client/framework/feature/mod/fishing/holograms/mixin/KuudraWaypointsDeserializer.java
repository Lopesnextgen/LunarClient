package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.mixin;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import org.joml.Vector3i;

public class KuudraWaypointsDeserializer implements JsonDeserializer<KuudraWaypoints> {
   public KuudraWaypointsDeserializer() {
   }

   public KuudraWaypoints method1(JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3) {
      JsonObject json4 = element1.getAsJsonObject();
      HashMap map5 = new HashMap();
      JsonObject json6 = json4.getAsJsonObject("pearl");

      for (Entry entry8 : json6.entrySet()) {
         String text9 = (String)entry8.getKey();
         JsonObject json10 = ((JsonElement)entry8.getValue()).getAsJsonObject();
         Vector3i vector3i11 = this.method2(json10.get("pos").getAsString());
         ArrayList list12 = new ArrayList();

         for (JsonElement element14 : json10.getAsJsonArray("waypoints")) {
            Vector3i vector3i15 = this.method2(element14.getAsString());
            list12.add(vector3i15);
         }

         ArrayList list21 = new ArrayList();

         for (JsonElement element24 : json10.getAsJsonArray("second")) {
            Vector3i vector3i16 = this.method2(element24.getAsString());
            list21.add(vector3i16);
         }

         KuudraPearlWaypoint holograms23 = new KuudraPearlWaypoint(vector3i11, list12, list21);
         map5.put(text9, holograms23);
      }

      ArrayList list17 = new ArrayList();

      for (JsonElement element20 : json4.getAsJsonArray("stun")) {
         list17.add(this.method2(element20.getAsString()));
      }

      return new KuudraWaypoints(map5, list17);
   }

   private Vector3i method2(String text1) {
      String[] items2 = text1.split(",");
      int number3 = Integer.parseInt(items2[0]);
      int number4 = Integer.parseInt(items2[1]);
      int number5 = Integer.parseInt(items2[2]);
      return new Vector3i(number3, number4, number5);
   }
}
