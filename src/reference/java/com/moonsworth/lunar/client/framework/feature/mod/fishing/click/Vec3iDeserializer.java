package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import java.lang.reflect.Type;

public class Vec3iDeserializer implements JsonDeserializer<Vec3iBridge> {
   public Vec3iDeserializer() {
   }

   public Vec3iBridge deserialize(JsonElement element1, Type type2, JsonDeserializationContext jsondeserializationcontext3) {
      if (!element1.isJsonPrimitive()) {
         throw new JsonParseException("Expected a string");
      }

      JsonPrimitive json4 = element1.getAsJsonPrimitive();
      if (!json4.isString()) {
         throw new JsonParseException("Expected a string");
      }

      String[] items5 = json4.getAsString().split(",");
      if (items5.length != 3) {
         throw new JsonParseException("Expected 3 comma separated integers");
      }

      int number6 = Integer.parseInt(items5[0]);
      int number7 = Integer.parseInt(items5[1]);
      int number8 = Integer.parseInt(items5[2]);
      return Bridge.method8().method4(number6, number7, number8);
   }
}
