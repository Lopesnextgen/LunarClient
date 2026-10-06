package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class JsonDeserializerIterator2 implements JsonDeserializer<Holograms10> {
   public Holograms10 method1(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      JsonObject var4 = var1.getAsJsonObject();
      String var5 = var4.get("skyBlockYearQuestion").getAsString();
      String var6 = var4.get("totalFairySoulsQuestion").getAsString();
      String var7 = var4.get("totalFairySoulsAnswer").getAsString();
      Pattern var8 = Pattern.compile(var4.get("success").getAsString());
      Pattern var9 = Pattern.compile(var4.get("failure").getAsString());
      HashSet var10 = new HashSet();

      for (JsonElement var13 : var4.get("questions").getAsJsonArray()) {
         JsonObject var14 = var13.getAsJsonObject();
         Pattern var15 = Pattern.compile(var14.get("question").getAsString());
         JsonElement var16 = var14.get("answerSet");
         JsonElement var17 = var14.get("answerMap");
         if (var17 == null && var16 == null) {
            throw new JsonParseException("no answers specified for question");
         }

         if (var17 == null) {
            JsonArray var22 = var16.getAsJsonArray();
            HashSet var23 = new HashSet();

            for (JsonElement var26 : var22) {
               var23.add(var26.getAsString());
            }

            var10.add(new Holograms8_2(var15, var23));
         } else {
            HashMap var18 = new HashMap();
            JsonObject var19 = var17.getAsJsonObject();

            for (Entry var21 : var19.entrySet()) {
               var18.put((String)var21.getKey(), ((JsonElement)var21.getValue()).getAsString());
            }

            int var24 = var14.get("group").getAsInt();
            var10.add(new Holograms8_2(var15, var18, var24));
         }
      }

      return new Holograms10(var5, var6, var7, var8, var9, var10);
   }
}
