package com.moonsworth.lunar.bridge;

import lombok.Generated;

public class Bridge_52 {
   public Bridge8Extension field1 = null;
   public int width = 0;
   public int height = 0;
   public boolean useDepth = false;
   public boolean field2 = false;
   public boolean field3 = false;

   private Bridge_52() {
   }

   public Bridge_52 method1(int number1, int value) {
      return this.method5(number1).method6(value);
   }

   public static Bridge_52 method2() {
      return new Bridge_52();
   }

   public Bridge3_24 method3() {
      return Bridge.method8().method57(this);
   }

   @Generated
   public Bridge_52 method4(Bridge8Extension bridge8) {
      this.field1 = bridge8;
      return this;
   }

   @Generated
   public Bridge_52 method5(int number1) {
      this.width = number1;
      return this;
   }

   @Generated
   public Bridge_52 method6(int number1) {
      this.height = number1;
      return this;
   }

   @Generated
   public Bridge_52 method7(boolean flag1) {
      this.useDepth = flag1;
      return this;
   }

   @Generated
   public Bridge_52 method8(boolean flag1) {
      this.field2 = flag1;
      return this;
   }

   @Generated
   public Bridge_52 method9(boolean flag1) {
      this.field3 = flag1;
      return this;
   }
}
