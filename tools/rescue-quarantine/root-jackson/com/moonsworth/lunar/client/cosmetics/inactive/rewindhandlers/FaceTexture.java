package com.moonsworth.lunar.client.cosmetics.inactive.rewindhandlers;

import com.moonsworth.lunar.Annotation27;

public class FaceTexture {
   private String field1;
   private double[] field2;
   private double[] field3;

   public FaceTexture() {
   }

   @Annotation27("material_instance")
   public String method1() {
      return this.field1;
   }

   @Annotation27("material_instance")
   public void method2(String text1) {
      this.field1 = text1;
   }

   @Annotation27("uv")
   public double[] method3() {
      return this.field2;
   }

   @Annotation27("uv")
   public void method4(double[] items1) {
      this.field2 = items1;
   }

   @Annotation27("uv_size")
   public double[] method5() {
      return this.field3;
   }

   @Annotation27("uv_size")
   public void method6(double[] items1) {
      this.field3 = items1;
   }
}
