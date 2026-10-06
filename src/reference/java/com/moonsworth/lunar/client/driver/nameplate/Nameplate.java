package com.moonsworth.lunar.client.driver.nameplate;

import com.google.gson.annotations.SerializedName;

public class Nameplate {
   @SerializedName("x")
   private final float field1;
   @SerializedName("y")
   private final float field2;
   @SerializedName("width")
   private final float field3;
   @SerializedName("height")
   private final float field4;

   public Nameplate(float var1, float var2, float var3, float var4) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = var4;
   }

   @SerializedName("x")
   public float x() {
      return this.field1;
   }

   @SerializedName("y")
   public float y() {
      return this.field2;
   }

   @SerializedName("width")
   public float method1() {
      return this.field3;
   }

   @SerializedName("height")
   public float method2() {
      return this.field4;
   }
}
