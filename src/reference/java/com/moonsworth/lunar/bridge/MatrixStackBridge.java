package com.moonsworth.lunar.bridge;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;

public class MatrixStackBridge {
   private final List<Matrix4f> field1;
   private Matrix4f field2;
   private final Matrix4f field3 = new Matrix4f();
   private final AxisAngle4f field4 = new AxisAngle4f();
   private final Vector3f field5 = new Vector3f();

   public MatrixStackBridge() {
      this.field1 = new ArrayList<>();
      this.field2 = new Matrix4f();
      this.method1();
   }

   public MatrixStackBridge(Matrix4f matrix4f1) {
      this.field1 = new ArrayList<>();
      this.field2 = matrix4f1;
   }

   public Matrix4f getMatrix() {
      return this.field2;
   }

   public void method1() {
      this.field2.setIdentity();
   }

   public void method2(Matrix4f matrix4f1) {
      this.field2 = matrix4f1;
   }

   public void method3() {
      this.field1.add((Matrix4f)this.field2.clone());
   }

   public void method4() {
      if (this.field1.isEmpty()) {
         this.method1();
      } else {
         this.field2 = this.field1.remove(this.field1.size() - 1);
      }
   }

   public void method5(double value1, double value3, double value5, double value7, double value9, double value11) {
      this.field3.setIdentity();
      this.field3.m00 = (float)(2.0 / (value3 - value1));
      this.field3.m11 = (float)(2.0 / (value7 - value5));
      this.field3.m22 = (float)(-2.0 / (value11 - value9));
      this.field3.m03 = (float)(-(value3 + value1) / (value3 - value1));
      this.field3.m13 = (float)(-(value7 + value5) / (value7 - value5));
      this.field3.m23 = (float)(-(value11 + value9) / (value11 - value9));
      this.field2.mul(this.field3);
   }

   public void method6(float value1, float value2, float value3, float value4) {
      this.field4.angle = (float)Math.toRadians(value1);
      this.field4.x = value2;
      this.field4.y = value3;
      this.field4.z = value4;
      double value5 = Math.sqrt(value2 * value2 + value3 * value3 + value4 * value4);
      float value7 = (float)(1.0 / value5);
      this.field4.x *= value7;
      this.field4.y *= value7;
      this.field4.z *= value7;
      this.field3.setIdentity();
      this.field3.setRotation(this.field4);
      this.field2.mul(this.field3);
   }

   public void scale(float value1, float value2, float value3) {
      this.field3.setIdentity();
      this.field3.m00 = value1;
      this.field3.m11 = value2;
      this.field3.m22 = value3;
      this.field2.mul(this.field3);
   }

   public void method7(float value1, float value2, float value3) {
      this.field5.set(value1, value2, value3);
      this.field3.setIdentity();
      this.field3.setTranslation(this.field5);
      this.field2.mul(this.field3);
   }

   public void method8(FloatBuffer floatbuffer1) {
      int index2 = floatbuffer1.position();
      this.field3.m00 = floatbuffer1.get(index2);
      this.field3.m01 = floatbuffer1.get(index2 + 1);
      this.field3.m02 = floatbuffer1.get(index2 + 2);
      this.field3.m03 = floatbuffer1.get(index2 + 3);
      this.field3.m10 = floatbuffer1.get(index2 + 4);
      this.field3.m11 = floatbuffer1.get(index2 + 5);
      this.field3.m12 = floatbuffer1.get(index2 + 6);
      this.field3.m13 = floatbuffer1.get(index2 + 7);
      this.field3.m20 = floatbuffer1.get(index2 + 8);
      this.field3.m21 = floatbuffer1.get(index2 + 9);
      this.field3.m22 = floatbuffer1.get(index2 + 10);
      this.field3.m23 = floatbuffer1.get(index2 + 11);
      this.field3.m30 = floatbuffer1.get(index2 + 12);
      this.field3.m31 = floatbuffer1.get(index2 + 13);
      this.field3.m32 = floatbuffer1.get(index2 + 14);
      this.field3.m33 = floatbuffer1.get(index2 + 15);
      this.field2.mul(this.field3);
   }
}
