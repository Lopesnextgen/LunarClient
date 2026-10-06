package com.moonsworth.lunar.client.driver.bridge;

import com.google.gson.JsonElement;

public interface JsonProvider {
   JsonElement provide();

   default JsonProvider method1() {
      return new JsonProvider.CachedJsonProvider(this);
   }

   class CachedJsonProvider implements JsonProvider {
      private final JsonElement field1;

      private CachedJsonProvider(JsonProvider gui21) {
         this.field1 = gui21.provide();
      }

      @Override
      public JsonElement provide() {
         return this.field1;
      }
   }
}
