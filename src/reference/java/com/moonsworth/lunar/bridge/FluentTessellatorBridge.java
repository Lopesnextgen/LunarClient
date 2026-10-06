package com.moonsworth.lunar.bridge;

public interface FluentTessellatorBridge {
   FluentTessellatorBridge bridge$pos(float value1, float value2, float value3);

   default FluentTessellatorBridge method1(double value1, double value3, double value5) {
      return this.bridge$pos((float)value1, (float)value3, (float)value5);
   }

   FluentTessellatorBridge bridge$pos(MixinHelper_21 mixinhelper_211, float value2, float value3, float value4);

   FluentTessellatorBridge bridge$normal(float value1, float value2, float value3);

   FluentTessellatorBridge bridge$normal(Matrix3fBridge mixinhelper2_61, float value2, float value3, float value4);

   FluentTessellatorBridge bridge$color(float value1, float value2, float value3, float value4);

   FluentTessellatorBridge bridge$uv(float value1, float value2);

   FluentTessellatorBridge bridge$lightmap(int number1);

   default FluentTessellatorBridge method2(float value1, float value2) {
      return this.bridge$pos(value1, value2, 0.0F);
   }

   FluentTessellatorBridge bridge$endVertex();

   void bridge$end();

   default void method3() {
      this.bridge$end();
   }

   boolean bridge$isDrawing();

   void bridge$begin(DrawMode bridgetype_61, VertexFormatBridge bridge_632);

   void bridge$setTranslation(double value1, double value3, double value5);
}
