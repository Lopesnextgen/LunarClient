package com.moonsworth.lunar.client.driver.core.holograms;

import com.google.gson.annotations.SerializedName;
import lombok.Generated;

public class HologramElementLegacy {
   @SerializedName("position")
   private HologramBoundsLegacy field1;
   @SerializedName("id")
   private final String field2;
   @SerializedName("scrollAmount")
   private float field3;

   @Generated
   public HologramBoundsLegacy method1() {
      return this.field1;
   }

   @Generated
   public String getId() {
      return this.field2;
   }

   @Generated
   public float method2() {
      return this.field3;
   }

   @Generated
   public void method3(HologramBoundsLegacy var1) {
      this.field1 = var1;
   }

   @Generated
   public void method4(float var1) {
      this.field3 = var1;
   }

   @Generated
   public HologramElementLegacy(String var1) {
      this.field2 = var1;
   }
}
