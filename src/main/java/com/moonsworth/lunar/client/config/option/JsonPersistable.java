package com.moonsworth.lunar.client.config.option;

import com.google.gson.JsonObject;

public interface JsonPersistable {
   void load(JsonObject var1);

   void method1(JsonObject var1);

   default int priority() {
      return 1000;
   }
}
