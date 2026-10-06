package com.moonsworth.lunar.bridge;

public interface DrawBufferBridge {
   DrawBufferBridge method1();

   DrawBufferBridge method2(double value1, double value3, double value5);

   default DrawBufferBridge method3(MixinHelper_21 mixinhelper_211, double value2, double value4, double value6) {
      return this.method4(mixinhelper_211, (float)value2, (float)value4, (float)value6);
   }

   default DrawBufferBridge method4(MixinHelper_21 mixinhelper_211, float value2, float value3, float value4) {
      float value5 = value2;
      float value6 = value3;
      float value7 = value4;
      value2 = mixinhelper_211.bridge$getTransformX(value5, value6, value7, 1.0F);
      value3 = mixinhelper_211.bridge$getTransformY(value5, value6, value7, 1.0F);
      value4 = mixinhelper_211.bridge$getTransformZ(value5, value6, value7, 1.0F);
      this.method2(value2, value3, value4);
      return this;
   }

   default DrawBufferBridge method5(float value1, float value2) {
      return this.method2(value1, value2, 0.0);
   }

   default DrawBufferBridge method6(double value1, double value3) {
      return this.method2(value1, value3, 0.0);
   }

   DrawBufferBridge method7(int number1, int number2, int number3, int number4);

   DrawBufferBridge method8(float value1, float value2, float value3, float value4);

   default DrawBufferBridge method9(int number1) {
      int number2 = number1 >> 24 & 0xFF;
      int number3 = number1 >> 16 & 0xFF;
      int number4 = number1 >> 8 & 0xFF;
      int number5 = number1 & 0xFF;
      return this.method7(number3, number4, number5, number2);
   }

   DrawBufferBridge method10(float value1, float value2);

   DrawBufferBridge method11(int number1);

   default DrawBufferBridge method12(int number1, int number2) {
      return this.method11(LightTextureBridge.pack(number1, number2));
   }

   DrawBufferBridge method13(int number1, int number2);

   DrawBufferBridge method14(float value1, float value2, float value3);

   default DrawBufferBridge method15(Matrix3fBridge mixinhelper2_61, float value2, float value3, float value4) {
      return this.method14(
         mixinhelper2_61.bridge$getTransformX(value2, value3, value4), mixinhelper2_61.bridge$getTransformY(value2, value3, value4), mixinhelper2_61.bridge$getTransformZ(value2, value3, value4)
      );
   }

   DrawBufferBridge method16();

   void method17(BufferMode bridgetype_171);

   default void method18(BufferMode bridgetype_171) {
      this.method17(bridgetype_171);
   }

   VertexConsumerBridge method19();

   DrawBufferBridge method20(double value1, double value3, double value5);

   DrawBufferBridge method21(float value1, float value2, float value3);

   default boolean method22() {
      return true;
   }
}
