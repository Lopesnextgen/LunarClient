package com.moonsworth.lunar.client.cosmetics.gecko;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TextureMesh {
   private double[] field1;
   private double[] field2;
   private double[] field3;
   private double[] field4;
   private String texture;

   public TextureMesh() {
   }

   @JsonProperty("local_pivot")
   public double[] method1() {
      return this.field1;
   }

   @JsonProperty("local_pivot")
   public void method2(double[] items1) {
      this.field1 = items1;
   }

   @JsonProperty("position")
   public double[] method3() {
      return this.field2;
   }

   @JsonProperty("position")
   public void method4(double[] items1) {
      this.field2 = items1;
   }

   @JsonProperty("rotation")
   public double[] method5() {
      return this.field3;
   }

   @JsonProperty("rotation")
   public void method6(double[] items1) {
      this.field3 = items1;
   }

   @JsonProperty("scale")
   public double[] method7() {
      return this.field4;
   }

   @JsonProperty("scale")
   public void method8(double[] items1) {
      this.field4 = items1;
   }

   @JsonProperty("texture")
   public String getTexture() {
      return this.texture;
   }

   @JsonProperty("texture")
   public void setTexture(String text1) {
      this.texture = text1;
   }
}
