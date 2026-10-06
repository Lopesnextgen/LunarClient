package com.moonsworth.lunar.bridge;

public interface VertexConsumerBridge {
   int NO_OVERLAY = 655360;

   VertexConsumerBridge bridge$vertex(double value1, double value3, double value5);

   default VertexConsumerBridge method1(double value1, double value3) {
      return this.bridge$vertex(value1, value3, 0.0);
   }

   VertexConsumerBridge bridge$color(int number1, int number2, int number3, int number4);

   default VertexConsumerBridge method2(int number1) {
      int number2 = number1 >> 24 & 0xFF;
      int number3 = number1 >> 16 & 0xFF;
      int number4 = number1 >> 8 & 0xFF;
      int number5 = number1 & 0xFF;
      return this.bridge$color(number3, number4, number5, number2);
   }

   VertexConsumerBridge bridge$uv(float value1, float value2);

   VertexConsumerBridge bridge$overlayCoords(int number1, int number2);

   VertexConsumerBridge bridge$uv2(int number1, int number2);

   VertexConsumerBridge bridge$normal(float value1, float value2, float value3);

   VertexConsumerBridge bridge$vertex(MixinHelper_21 mixinhelper_211, float value2, float value3, float value4);

   VertexConsumerBridge bridge$normal(Matrix3fBridge mixinhelper2_61, float value2, float value3, float value4);

   void bridge$endVertex();

   void bridge$vertex(
      float value1,
      float value2,
      float value3,
      float value4,
      float value5,
      float value6,
      float value7,
      float value8,
      float value9,
      int number10,
      int number11,
      float value12,
      float value13,
      float value14
   );
}
