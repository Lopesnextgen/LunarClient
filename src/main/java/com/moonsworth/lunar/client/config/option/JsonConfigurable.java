package com.moonsworth.lunar.client.config.option;

import com.google.gson.JsonObject;

public interface JsonConfigurable {
   void load(JsonObject json1);

   void method1(JsonObject json1);

   default int priority() {
      return 1000;
   }
}
