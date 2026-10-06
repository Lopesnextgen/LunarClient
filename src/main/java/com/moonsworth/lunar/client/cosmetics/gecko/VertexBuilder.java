package com.moonsworth.lunar.client.cosmetics.gecko;

public abstract class VertexBuilder implements VertexSink {
   private boolean field1 = false;
   private boolean field2 = false;
   private float field3;
   private float field4;
   private float field5;
   private float field6;
   private float field7;
   private float field8;
   private float field9;
   private float field10;

   public VertexBuilder() {
   }

   protected abstract void vertex(float value1, float value2, float value3, float value4, float value5, float value6, float value7, float value8);

   private void method1() {
      this.vertex(this.field3, this.field4, this.field5, this.field6, this.field7, this.field8, this.field9, this.field10);
   }

   @Override
   public void method1(float value1, float value2, float value3, float value4, float value5, float value6, float value7, float value8) {
      this.field2 = true;
      if (this.field1) {
         this.method1();
      }

      this.field3 = value1;
      this.field4 = value2;
      this.field5 = value3;
      this.field6 = value4;
      this.field7 = value5;
      this.field8 = value6;
      this.field9 = value7;
      this.field10 = value8;
      if (this.field1) {
         this.method1();
         this.field1 = false;
      }

      this.method1();
   }

   @Override
   public void method2() {
      if (this.field2) {
         this.field1 = true;
      }
   }
}
