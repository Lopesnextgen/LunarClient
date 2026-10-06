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

public class QuizKeyDeserializer implements JsonDeserializer<QuizKey> {
   public QuizKeyDeserializer() {
   }

   public QuizKey method1(JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3) {
      JsonObject json4 = element1.getAsJsonObject();
      String text5 = json4.get("skyBlockYearQuestion").getAsString();
      String text6 = json4.get("totalFairySoulsQuestion").getAsString();
      String text7 = json4.get("totalFairySoulsAnswer").getAsString();
      Pattern pattern8 = Pattern.compile(json4.get("success").getAsString());
      Pattern pattern9 = Pattern.compile(json4.get("failure").getAsString());
      HashSet set10 = new HashSet();

      for (JsonElement element13 : json4.get("questions").getAsJsonArray()) {
         JsonObject json14 = element13.getAsJsonObject();
         Pattern pattern15 = Pattern.compile(json14.get("question").getAsString());
         JsonElement element16 = json14.get("answerSet");
         JsonElement element17 = json14.get("answerMap");
         if (element17 == null && element16 == null) {
            throw new JsonParseException("no answers specified for question");
         }

         if (element17 == null) {
            JsonArray array22 = element16.getAsJsonArray();
            HashSet set23 = new HashSet();

            for (JsonElement element26 : array22) {
               set23.add(element26.getAsString());
            }

            set10.add(new ChatFilterRule(pattern15, set23));
         } else {
            HashMap map18 = new HashMap();
            JsonObject json19 = element17.getAsJsonObject();

            for (Entry entry21 : json19.entrySet()) {
               map18.put((String)entry21.getKey(), ((JsonElement)entry21.getValue()).getAsString());
            }

            int number24 = json14.get("group").getAsInt();
            set10.add(new ChatFilterRule(pattern15, map18, number24));
         }
      }

      return new QuizKey(text5, text6, text7, pattern8, pattern9, set10);
   }
}
