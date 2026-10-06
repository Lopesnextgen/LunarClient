package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.concurrent.LazyInit;

final class MixinHelper11$Data20 extends MixinHelper11_4 {
   final double field1;
   @LazyInit
   MixinHelper11_4 field2;

   MixinHelper11$Data20(double var1) {
      this.field1 = var1;
      this.field2 = null;
   }

   MixinHelper11$Data20(double var1, MixinHelper11_4 var3) {
      this.field1 = var1;
      this.field2 = var3;
   }

   @Override
   public boolean isVertical() {
      return true;
   }

   @Override
   public boolean isHorizontal() {
      return false;
   }

   @Override
   public double slope() {
      throw new IllegalStateException();
   }

   @Override
   public double transform(double var1) {
      throw new IllegalStateException();
   }

   @Override
   public MixinHelper11_4 method5() {
      MixinHelper11_4 var1 = this.field2;
      return var1 == null ? (this.field2 = this.method2()) : var1;
   }

   @Override
   public String toString() {
      return String.format("x = %g", this.field1);
   }

   private MixinHelper11_4 method2() {
      return new MixinHelper11$Data19(0.0, this.field1, this);
   }
}
