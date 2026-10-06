package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.concurrent.LazyInit;

final class MixinHelper11$Data19 extends MixinHelper11_4 {
   final double field1;
   final double field2;
   @LazyInit
   MixinHelper11_4 field3;

   MixinHelper11$Data19(double var1, double var3) {
      this.field1 = var1;
      this.field2 = var3;
      this.field3 = null;
   }

   MixinHelper11$Data19(double var1, double var3, MixinHelper11_4 var5) {
      this.field1 = var1;
      this.field2 = var3;
      this.field3 = var5;
   }

   @Override
   public boolean isVertical() {
      return false;
   }

   @Override
   public boolean isHorizontal() {
      return this.field1 == 0.0;
   }

   @Override
   public double slope() {
      return this.field1;
   }

   @Override
   public double transform(double var1) {
      return var1 * this.field1 + this.field2;
   }

   @Override
   public MixinHelper11_4 method5() {
      MixinHelper11_4 var1 = this.field3;
      return var1 == null ? (this.field3 = this.method2()) : var1;
   }

   @Override
   public String toString() {
      return String.format("y = %g * x + %g", this.field1, this.field2);
   }

   private MixinHelper11_4 method2() {
      return this.field1 != 0.0
         ? new MixinHelper11$Data19(1.0 / this.field1, -1.0 * this.field2 / this.field1, this)
         : new MixinHelper11$Data20(this.field2, this);
   }
}
