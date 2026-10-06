package com.moonsworth.lunar.client.inactive.rewindhandlers;

import com.moonsworth.lunar.Annotation27;

public class Rewindhandlers10 {
   private Boolean field1;
   private double[] field2;
   private double[] field3;

   @Annotation27("ignore_inherited_scale")
   public Boolean method1() {
      return this.field1;
   }

   @Annotation27("ignore_inherited_scale")
   public void method2(Boolean var1) {
      this.field1 = var1;
   }

   @Annotation27("offset")
   public double[] method3() {
      return this.field2;
   }

   @Annotation27("offset")
   public void method4(double[] var1) {
      this.field2 = var1;
   }

   @Annotation27("rotation")
   public double[] method5() {
      return this.field3;
   }

   @Annotation27("rotation")
   public void method6(double[] var1) {
      this.field3 = var1;
   }
}
