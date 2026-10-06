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

public class JsonDeserializerIterator implements JsonDeserializer<Holograms2> {
   public Holograms2 method1(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = var1.getAsJsonObject();
      HashMap var5 = new HashMap();
      JsonObject var6 = var4.getAsJsonObject("pearl");

      for (Entry var8 : var6.entrySet()) {
         String var9 = (String)var8.getKey();
         JsonObject var10 = ((JsonElement)var8.getValue()).getAsJsonObject();
         Vector3i var11 = this.method2(var10.get("pos").getAsString());
         ArrayList var12 = new ArrayList();

         for (JsonElement var14 : var10.getAsJsonArray("waypoints")) {
            Vector3i var15 = this.method2(var14.getAsString());
            var12.add(var15);
         }

         ArrayList var21 = new ArrayList();

         for (JsonElement var24 : var10.getAsJsonArray("second")) {
            Vector3i var16 = this.method2(var24.getAsString());
            var21.add(var16);
         }

         Holograms var23 = new Holograms(var11, var12, var21);
         var5.put(var9, var23);
      }

      ArrayList var17 = new ArrayList();

      for (JsonElement var20 : var4.getAsJsonArray("stun")) {
         var17.add(this.method2(var20.getAsString()));
      }

      return new Holograms2(var5, var17);
   }

   private Vector3i method2(String var1) {
      String[] var2 = var1.split(",");
      int var3 = Integer.parseInt(var2[0]);
      int var4 = Integer.parseInt(var2[1]);
      int var5 = Integer.parseInt(var2[2]);
      return new Vector3i(var3, var4, var5);
   }
}
