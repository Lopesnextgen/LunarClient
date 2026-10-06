package com.moonsworth.lunar.bridge;

public interface Bridge_8 {
   void translate(double var1, double var3, double var5);

   void scale(float var1, float var2, float var3);

   default void method1(float var1, float var2) {
      this.scale(var1, var2, 1.0F);
   }

   default void method2(float var1, float var2, float var3, float var4) {
      this.method4((float)Math.toDegrees(var1), var2, var3, var4);
   }

   default void method3(float var1, float var2, float var3) {
      this.method5((float)Math.toDegrees(var1), (float)Math.toDegrees(var2), (float)Math.toDegrees(var3));
   }

   default void method4(float var1, float var2, float var3, float var4) {
      this.method5(var1 * var2, var1 * var3, var1 * var4);
   }

   void method5(float var1, float var2, float var3);
}
