package com.moonsworth.lunar.client.render.particle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonWriter;
import java.io.StringWriter;

public class JsonPrettyPrinter {
   public JsonPrettyPrinter() {
   }

   public static String jsonToPretty(JsonElement element0) {
      StringWriter writer1 = new StringWriter();
      JsonWriter jsonwriter2 = new JsonWriter(writer1);
      Gson gson3 = new GsonBuilder().setPrettyPrinting().create();
      jsonwriter2.setIndent("    ");
      gson3.toJson(element0, jsonwriter2);
      return writer1.toString();
   }
}
