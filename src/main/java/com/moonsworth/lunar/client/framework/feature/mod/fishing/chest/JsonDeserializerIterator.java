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

public class JsonDeserializerIterator implements JsonDeserializer<Chest> {
   public Chest deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = var1.getAsJsonObject();
      HashMap var5 = new HashMap();

      for (Entry var7 : var4.entrySet()) {
         ArrayList var8 = new ArrayList();

         for (JsonElement var10 : ((JsonElement)var7.getValue()).getAsJsonArray()) {
            var8.add(this.parsePosition(var10.getAsJsonObject().get("pos").getAsString()));
         }

         var5.put((String)var7.getKey(), var8);
      }

      return new Chest(var5);
   }

   private Vector3d parsePosition(String var1) {
      String[] var2 = var1.split(",");
      double var3 = Double.parseDouble(var2[0]);
      double var5 = Double.parseDouble(var2[1]);
      double var7 = Double.parseDouble(var2[2]);
      return new Vector3d(var3, var5, var7);
   }
}
