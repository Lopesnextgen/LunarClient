package com.moonsworth.lunar.bridge;

public interface BufferBuilderBridge {
   default void method1(int number1) {
      float value2 = (number1 >> 24 & 0xFF) / 255.0F;
      float value3 = (number1 >> 16 & 0xFF) / 255.0F;
      float value4 = (number1 >> 8 & 0xFF) / 255.0F;
      float value5 = (number1 & 0xFF) / 255.0F;
      this.method2(value3, value4, value5, value2);
   }

   void method2(float value1, float value2, float value3, float value4);

   void method3(double value1, double value3, double value5, double value7, double value9, double value11);

   void method4(double value1, double value3, double value5, double value7, double value9, double value11, int number13, int number14);

   void method5(double value1, double value3, double value5, double value7, double value9, double value11, float value13, float value14, float value15, float value16);

   void method6(
      double value1,
      double value3,
      double value5,
      double value7,
      double value9,
      double value11,
      float value13,
      float value14,
      float value15,
      float value16,
      float value17,
      float value18,
      float value19,
      float value20
   );

   void end();

   BufferBuilderBridge method7(double value1, double value3, double value5);

   BufferBuilderBridge method8(MixinHelper_21 mixinhelper_211, double value2, double value4, double value6);

   BufferBuilderBridge method9(float value1, float value2, float value3, float value4);

   default BufferBuilderBridge method10(int number1) {
      return this.method9((number1 >> 16 & 0xFF) / 255.0F, (number1 >> 8 & 0xFF) / 255.0F, (number1 & 0xFF) / 255.0F, (number1 >> 24 & 0xFF) / 255.0F);
   }

   BufferBuilderBridge method11(float value1, float value2, float value3);

   BufferBuilderBridge method12(Matrix3fBridge mixinhelper2_61, float value2, float value3, float value4);

   void endVertex();
}
