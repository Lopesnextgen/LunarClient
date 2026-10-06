package com.moonsworth.lunar.client.framework.crash;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.moonsworth.lunar.bridge.CrashReportBridge;
import java.lang.reflect.Type;

public class ThrowableJsonSerializer implements com.google.gson.JsonSerializer<CrashReportBridge> {
   public ThrowableJsonSerializer() {
   }

   public JsonElement method1(CrashReportBridge bridge3_221, Type type2, JsonSerializationContext jsonserializationcontext3) {
      JsonObject json4 = new JsonObject();
      json4.addProperty("title", bridge3_221.bridge$getTitle());
      Throwable exception5 = bridge3_221.bridge$getCause();
      json4.addProperty("message", exception5.getClass().getName() + ": " + exception5.getMessage());
      JsonArray array6 = new JsonArray();

      for (StackTraceElement stacktraceelement10 : exception5.getStackTrace()) {
         array6.add(new JsonPrimitive(stacktraceelement10.toString()));
      }

      json4.add("stacktrace", array6);
      return json4;
   }
}
