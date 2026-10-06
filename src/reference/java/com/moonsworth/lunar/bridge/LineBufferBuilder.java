package com.moonsworth.lunar.bridge;

public class LineBufferBuilder implements BufferBuilderBridge {
   private final FluentTessellatorBridge field1;
   private final float field2;
   private boolean field3;
   private float field4;
   private float field5;
   private float field6;
   private float field7;
   private boolean field8;

   public LineBufferBuilder(FluentTessellatorBridge bridge4_111, float value2) {
      this.field1 = bridge4_111;
      this.field2 = value2;
      this.field3 = false;
      this.method1(VertexFormats.field1);
   }

   private void method1(VertexFormatBridge bridge_631) {
      this.field1.bridge$begin(DrawMode.LINES, bridge_631);
      this.field8 = Bridge.method42().method33();
   }

   public void method2(float value1, float value2, float value3, float value4) {
      if (!this.field3) {
         this.field3 = true;
         Bridge.method42().method71(this.field2);
         this.field1.bridge$end();
         this.method1(VertexFormats.field3);
      }

      this.field4 = value1;
      this.field5 = value2;
      this.field6 = value3;
      this.field7 = value4;
   }

   public void method3(double value1, double value3, double value5, double value7, double value9, double value11) {
      this.method5(value1, value3, value5, value7, value9, value11, this.field4, this.field5, this.field6, this.field7);
   }

   public void method4(double value1, double value3, double value5, double value7, double value9, double value11, int value, int value2) {
      float value15 = (value >> 24 & 0xFF) / 255.0F;
      float value16 = (value >> 16 & 0xFF) / 255.0F;
      float value17 = (value >> 8 & 0xFF) / 255.0F;
      float value18 = (value & 0xFF) / 255.0F;
      float value19 = (value2 >> 24 & 0xFF) / 255.0F;
      float value20 = (value2 >> 16 & 0xFF) / 255.0F;
      float value21 = (value2 >> 8 & 0xFF) / 255.0F;
      float value22 = (value2 & 0xFF) / 255.0F;
      this.method6(value1, value3, value5, value7, value9, value11, value16, value17, value18, value15, value20, value21, value22, value19);
   }

   public void method5(double value1, double value3, double value5, double value7, double value9, double value11, float value13, float value14, float value15, float value16) {
      this.method6(value1, value3, value5, value7, value9, value11, value13, value14, value15, value16, value13, value14, value15, value16);
   }

   public void method6(
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
   ) {
      this.field1.method1(value1, value3, value5);
      if (this.field3) {
         this.field1.bridge$color(value13, value14, value15, value16);
      }

      this.field1.bridge$endVertex();
      this.field1.method1(value7, value9, value11);
      if (this.field3) {
         this.field1.bridge$color(value17, value18, value19, value20);
      }

      this.field1.bridge$endVertex();
   }

   public void end() {
      RenderSystemBridge bridge121 = Bridge.method42();
      bridge121.method71(this.field2);
      bridge121.method32();
      this.field1.bridge$end();
      if (this.field8) {
         bridge121.method31();
      }
   }

   public BufferBuilderBridge method7(double value1, double value3, double value5) {
      this.field1.method1(value1, value3, value5);
      return this;
   }

   public BufferBuilderBridge method8(MixinHelper_21 mixinhelper_211, double value2, double value4, double value) {
      this.field1.bridge$pos(mixinhelper_211, (float)value2, (float)value4, (float)value);
      return this;
   }

   public BufferBuilderBridge method9(float value1, float value2, float value3, float value4) {
      if (this.field3) {
         this.field1.bridge$color(value1, value2, value3, value4);
      }

      return this;
   }

   public BufferBuilderBridge method11(float value1, float value2, float value3) {
      this.field1.bridge$normal(value1, value2, value3);
      return this;
   }

   public BufferBuilderBridge method12(Matrix3fBridge mixinhelper2_61, float value2, float value3, float value4) {
      this.field1.bridge$normal(mixinhelper2_61, value2, value3, value4);
      return this;
   }

   public void endVertex() {
      this.field1.bridge$endVertex();
   }
}
