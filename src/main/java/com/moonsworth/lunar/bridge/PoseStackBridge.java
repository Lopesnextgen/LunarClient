package com.moonsworth.lunar.bridge;

public interface PoseStackBridge {
   void translate(double value1, double value3, double value5);

   void scale(float value1, float value2, float value3);

   default void method1(float value1, float value2) {
      this.scale(value1, value2, 1.0F);
   }

   default void method2(float value1, float value2, float value3, float value4) {
      this.method4((float)Math.toDegrees(value1), value2, value3, value4);
   }

   default void method3(float value1, float value2, float value3) {
      this.method5((float)Math.toDegrees(value1), (float)Math.toDegrees(value2), (float)Math.toDegrees(value3));
   }

   default void method4(float value1, float value2, float value3, float value4) {
      this.method5(value1 * value2, value1 * value3, value1 * value4);
   }

   void method5(float value1, float value2, float value3);
}
