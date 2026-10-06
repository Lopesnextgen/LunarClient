package com.moonsworth.lunar.client.driver.hologram;

import com.google.gson.annotations.SerializedName;
import lombok.Generated;

public class HologramElement {
   @SerializedName("position")
   private HologramBounds field1;
   @SerializedName("id")
   private final String field2;
   @SerializedName("scrollAmount")
   private float field3;

   @Generated
   public HologramBounds method1() {
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
   public void method3(HologramBounds holograms51) {
      this.field1 = holograms51;
   }

   @Generated
   public void method4(float value) {
      this.field3 = value;
   }

   @Generated
   public HologramElement(String text) {
      this.field2 = text;
   }
}
