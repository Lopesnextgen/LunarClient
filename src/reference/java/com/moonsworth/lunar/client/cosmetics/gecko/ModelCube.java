package com.moonsworth.lunar.client.cosmetics.gecko;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moonsworth.lunar.client.inactive.rewindhandlers.Rewindhandlers9;

public class ModelCube {
   private Double field1;
   private Boolean field2;
   private double[] field3 = new double[]{0.0, 0.0, 0.0};
   private double[] field4 = new double[]{0.0, 0.0, 0.0};
   private double[] field5 = new double[]{0.0, 0.0, 0.0};
   private double[] field6 = new double[]{1.0, 1.0, 1.0};
   private Rewindhandlers9 field7;

   public ModelCube() {
   }

   @JsonProperty("inflate")
   public Double method1() {
      return this.field1;
   }

   @JsonProperty("inflate")
   public void method2(Double value1) {
      this.field1 = value1;
   }

   @JsonProperty("mirror")
   public Boolean method3() {
      return this.field2;
   }

   @JsonProperty("mirror")
   public void method4(Boolean flag1) {
      this.field2 = flag1;
   }

   @JsonProperty("origin")
   public double[] method5() {
      return this.field3;
   }

   @JsonProperty("origin")
   public void method6(double[] items1) {
      this.field3 = items1;
   }

   @JsonProperty("pivot")
   public double[] method7() {
      return this.field4;
   }

   @JsonProperty("pivot")
   public void method8(double[] items1) {
      this.field4 = items1;
   }

   @JsonProperty("rotation")
   public double[] method9() {
      return this.field5;
   }

   @JsonProperty("rotation")
   public void method10(double[] items1) {
      this.field5 = items1;
   }

   @JsonProperty("size")
   public double[] method11() {
      return this.field6;
   }

   @JsonProperty("size")
   public void method12(double[] items1) {
      this.field6 = items1;
   }

   @JsonProperty("uv")
   public Rewindhandlers9 method13() {
      return this.field7;
   }

   @JsonProperty("uv")
   public void method14(Rewindhandlers9 rewindhandlers91) {
      this.field7 = rewindhandlers91;
   }
}
