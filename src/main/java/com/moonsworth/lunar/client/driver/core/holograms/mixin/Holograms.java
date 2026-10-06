package com.moonsworth.lunar.client.driver.core.holograms.mixin;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Generated;

public class Holograms {
   @SerializedName("id")
   private final int field1;
   @SerializedName("name")
   private String name;
   @SerializedName("type")
   private String type;
   @SerializedName("metadata")
   private JsonObject field2;

   public Holograms(int var1, JsonObject jsonObject) {
      this.field1 = var1;
      this.field2 = jsonObject;
   }

   public int id() {
      return this.field1;
   }

   public JsonObject method1() {
      return this.field2;
   }

   @Generated
   public void method2(JsonObject var1) {
      this.field2 = var1;
   }
}
