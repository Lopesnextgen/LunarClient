package com.moonsworth.lunar.client.driver.hologram;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Generated;

public class HologramEntry {
   @SerializedName("id")
   private final int field1;
   @SerializedName("name")
   private String name;
   @SerializedName("type")
   private String type;
   @SerializedName("metadata")
   private JsonObject field2;

   public HologramEntry(int value, JsonObject json2) {
      this.field1 = value;
      this.field2 = json2;
   }

   public int id() {
      return this.field1;
   }

   public JsonObject method1() {
      return this.field2;
   }

   @Generated
   public void method2(JsonObject json1) {
      this.field2 = json1;
   }
}
