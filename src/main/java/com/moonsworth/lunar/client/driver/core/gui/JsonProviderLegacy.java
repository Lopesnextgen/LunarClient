package com.moonsworth.lunar.client.driver.core.gui;

import com.google.gson.JsonElement;

public interface JsonProviderLegacy {
   JsonElement provide();

   default JsonProviderLegacy method1() {
      return new JsonProviderLegacy.Data2(this);
   }

   class Data2 implements JsonProviderLegacy {
      private final JsonElement field1;

      private Data2(JsonProviderLegacy gui2) {
         this.field1 = gui2.provide();
      }

      @Override
      public JsonElement provide() {
         return this.field1;
      }
   }
}
