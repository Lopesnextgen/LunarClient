package com.moonsworth.lunar.client.cosmetics.gecko;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MaterialInstance {
   private String field1;
   private double[] field2;
   private double[] field3;

   @JsonProperty("material_instance")
   public String method1() {
      return this.field1;
   }

   @JsonProperty("material_instance")
   public void method2(String var1) {
      this.field1 = var1;
   }

   @JsonProperty("uv")
   public double[] method3() {
      return this.field2;
   }

   @JsonProperty("uv")
   public void method4(double[] var1) {
      this.field2 = var1;
   }

   @JsonProperty("uv_size")
   public double[] method5() {
      return this.field3;
   }

   @JsonProperty("uv_size")
   public void method6(double[] var1) {
      this.field3 = var1;
   }
}
