package com.moonsworth.lunar.client.driver.bridge;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.driver.bridge.JsonProvider;
import java.util.UUID;

public class StorePriceEntry implements JsonProvider {
   private final UUID field1;
   private final int field2;
   private final String field3;
   private final Integer[] field4;
   private final StorePrice field5;
   private final String field6;
   private final int field7;

   public StorePriceEntry(UUID uuid1, int value, String text, Integer[] items4, StorePrice highlight5, String text2, int number7) {
      this.field1 = uuid1;
      this.field2 = value;
      this.field3 = text;
      this.field4 = items4;
      this.field5 = highlight5;
      this.field6 = text2;
      this.field7 = number7;
   }

   @Override
   public JsonElement provide() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("id", this.field1.toString());
      json1.addProperty("name", this.field3);
      json1.addProperty("currency", this.field6);
      json1.addProperty("fractionDigits", this.field7);
      JsonObject json2 = new JsonObject();
      if (this.field5.method1() != null) {
         json2.addProperty("coins", this.field5.method1());
      }

      json2.addProperty("value", this.field5.value());
      if (this.field5.method2() != null && this.field5.method2() != this.field5.value()) {
         json2.addProperty("original", this.field5.method2());
      }

      json1.add("value", json2);
      JsonArray array3 = new JsonArray();
      if (this.field4 != null) {
         Integer[] items4 = this.field4;
         int number5 = items4.length;

         for (int index6 = 0; index6 < number5; index6++) {
            int number7 = items4[index6];
            JsonObject json8 = new JsonObject();
            json8.addProperty("id", number7);
            array3.add(json8);
         }
      }

      json1.add("cosmetics", array3);
      return json1;
   }

   public UUID id() {
      return this.field1;
   }

   public int method2() {
      return this.field2;
   }

   public String name() {
      return this.field3;
   }

   public Integer[] method3() {
      return this.field4;
   }

   public StorePrice method4() {
      return this.field5;
   }

   public String method5() {
      return this.field6;
   }

   public int method6() {
      return this.field7;
   }
}
