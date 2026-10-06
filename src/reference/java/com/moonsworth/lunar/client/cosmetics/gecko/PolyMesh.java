package com.moonsworth.lunar.client.cosmetics.gecko;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moonsworth.lunar.client.inactive.rewindhandlers.Rewindhandlers11;

public class PolyMesh {
   private Boolean field1;
   private double[] field2;
   private Rewindhandlers11 field3;
   private double[] field4;
   private double[] field5;

   public PolyMesh() {
   }

   @JsonProperty("normalized_uvs")
   public Boolean method1() {
      return this.field1;
   }

   @JsonProperty("normalized_uvs")
   public void method2(Boolean flag1) {
      this.field1 = flag1;
   }

   @JsonProperty("normals")
   public double[] method3() {
      return this.field2;
   }

   @JsonProperty("normals")
   public void method4(double[] items1) {
      this.field2 = items1;
   }

   @JsonProperty("polys")
   public Rewindhandlers11 method5() {
      return this.field3;
   }

   @JsonProperty("polys")
   public void method6(Rewindhandlers11 rewindhandlers111) {
      this.field3 = rewindhandlers111;
   }

   @JsonProperty("positions")
   public double[] method7() {
      return this.field4;
   }

   @JsonProperty("positions")
   public void method8(double[] items1) {
      this.field4 = items1;
   }

   @JsonProperty("uvs")
   public double[] method9() {
      return this.field5;
   }

   @JsonProperty("uvs")
   public void method10(double[] items1) {
      this.field5 = items1;
   }
}
