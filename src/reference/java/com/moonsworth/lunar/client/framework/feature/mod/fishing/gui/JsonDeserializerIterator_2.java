package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class JsonDeserializerIterator_2 implements JsonDeserializer<Gui2_2> {
   public Gui2_2 method1(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = var1.getAsJsonObject();
      JsonObject var5 = var4.getAsJsonObject("skyMart");
      Map var6 = this.method2(var4.getAsJsonObject("bits"));
      Map var7 = this.method2(var5.getAsJsonObject("farmingEssentials"));
      Map var8 = this.method2(var5.getAsJsonObject("farmingTools"));
      Map var9 = this.method2(var5.getAsJsonObject("barnSkins"));
      Map var10 = this.method2(var5.getAsJsonObject("greenhouseSkins"));
      Map var11 = this.method2(var5.getAsJsonObject("pests"));
      Map var12 = this.method2(var4.getAsJsonObject("chocolate"));
      return new Gui2_2(var6, var7, var8, var9, var10, var11, var12);
   }

   private Map<String, Gui3_2> method2(JsonObject var1) {
      HashMap var2 = new HashMap();

      for (Entry var4 : var1.entrySet()) {
         String var5 = (String)var4.getKey();
         JsonObject var6 = ((JsonElement)var4.getValue()).getAsJsonObject();

         Gui3$Type var7;
         try {
            var7 = Gui3$Type.valueOf(var6.get("type").getAsString());
         } catch (IllegalArgumentException var13) {
            var7 = Gui3$Type.ITEM;
         }

         Gui_3 var8 = null;
         if (var6.has("skin")) {
            JsonObject var9 = var6.getAsJsonObject("skin");
            var8 = new Gui_3(var9.get("id").getAsString(), var9.get("texture").getAsString(), var9.get("signature").getAsString());
         }

         ArrayList var14 = new ArrayList();
         if (var6.has("requiredItems")) {
            for (JsonElement var11 : var6.getAsJsonArray("requiredItems")) {
               JsonObject var12 = var11.getAsJsonObject();
               var14.add(new Gui5(var12.get("id").getAsString(), var12.has("amount") ? var12.get("amount").getAsInt() : 1));
            }
         }

         Gui3_2 var15 = new Gui3_2(
            var5,
            var6.has("skyblockId") ? var6.get("skyblockId").getAsString() : var5,
            var6.get("price").getAsDouble(),
            var7,
            var6.has("displayName") ? var6.get("displayName").getAsString() : null,
            this.method3(var6),
            var8,
            var14
         );
         var2.put(var5, var15);
      }

      return var2;
   }

   private String method3(JsonObject var1) {
      return var1.has("itemModern") ? var1.get("itemModern").getAsString() : null;
   }
}
