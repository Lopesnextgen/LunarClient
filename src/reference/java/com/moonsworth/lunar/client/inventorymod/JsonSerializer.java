package com.moonsworth.lunar.client.inventorymod;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.moonsworth.lunar.bridge.Bridge3_22;
import java.lang.reflect.Type;

public class JsonSerializer implements com.google.gson.JsonSerializer<Bridge3_22> {
   public JsonElement method1(Bridge3_22 var1, Type var2, JsonSerializationContext var3) {
      JsonObject var4 = new JsonObject();
      var4.addProperty("title", var1.bridge$getTitle());
      Throwable var5 = var1.bridge$getCause();
      var4.addProperty("message", var5.getClass().getName() + ": " + var5.getMessage());
      JsonArray var6 = new JsonArray();

      for (StackTraceElement var10 : var5.getStackTrace()) {
         var6.add(new JsonPrimitive(var10.toString()));
      }

      var4.add("stacktrace", var6);
      return var4;
   }
}
