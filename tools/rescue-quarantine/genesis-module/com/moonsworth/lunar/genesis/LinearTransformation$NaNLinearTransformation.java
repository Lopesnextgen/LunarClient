package com.moonsworth.lunar.genesis;

final class LinearTransformation$NaNLinearTransformation extends MixinHelper11_4 {
   static final LinearTransformation$NaNLinearTransformation field1 = new LinearTransformation$NaNLinearTransformation();

   private LinearTransformation$NaNLinearTransformation() {
   }

   public boolean isVertical() {
      return false;
   }

   public boolean isHorizontal() {
      return false;
   }

   public double slope() {
      return Double.NaN;
   }

   public double transform(double value1) {
      return Double.NaN;
   }

   public MixinHelper11_4 method5() {
      return this;
   }

   public String toString() {
      return "NaN";
   }
}
