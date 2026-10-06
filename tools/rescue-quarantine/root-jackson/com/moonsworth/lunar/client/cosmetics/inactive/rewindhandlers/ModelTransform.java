package com.moonsworth.lunar.client.cosmetics.inactive.rewindhandlers;

import com.moonsworth.lunar.Annotation27;

public class ModelTransform {
   private Boolean field1;
   private double[] field2;
   private double[] field3;

   public ModelTransform() {
   }

   @Annotation27("ignore_inherited_scale")
   public Boolean method1() {
      return this.field1;
   }

   @Annotation27("ignore_inherited_scale")
   public void method2(Boolean flag1) {
      this.field1 = flag1;
   }

   @Annotation27("offset")
   public double[] method3() {
      return this.field2;
   }

   @Annotation27("offset")
   public void method4(double[] items1) {
      this.field2 = items1;
   }

   @Annotation27("rotation")
   public double[] method5() {
      return this.field3;
   }

   @Annotation27("rotation")
   public void method6(double[] items1) {
      this.field3 = items1;
   }
}
