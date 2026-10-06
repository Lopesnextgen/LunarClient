package com.moonsworth.lunar.bridge;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;

public class Bridge_53 {
   private final List<Matrix4f> field1;
   private Matrix4f field2;
   private final Matrix4f field3 = new Matrix4f();
   private final AxisAngle4f field4 = new AxisAngle4f();
   private final Vector3f field5 = new Vector3f();

   public Bridge_53() {
      this.field1 = new ArrayList<>();
      this.field2 = new Matrix4f();
      this.method1();
   }

   public Bridge_53(Matrix4f var1) {
      this.field1 = new ArrayList<>();
      this.field2 = var1;
   }

   public Matrix4f getMatrix() {
      return this.field2;
   }

   public void method1() {
      this.field2.setIdentity();
   }

   public void method2(Matrix4f var1) {
      this.field2 = var1;
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

   public void method5(double var1, double var3, double var5, double var7, double var9, double var11) {
      this.field3.setIdentity();
      this.field3.m00 = (float)(2.0 / (var3 - var1));
      this.field3.m11 = (float)(2.0 / (var7 - var5));
      this.field3.m22 = (float)(-2.0 / (var11 - var9));
      this.field3.m03 = (float)(-(var3 + var1) / (var3 - var1));
      this.field3.m13 = (float)(-(var7 + var5) / (var7 - var5));
      this.field3.m23 = (float)(-(var11 + var9) / (var11 - var9));
      this.field2.mul(this.field3);
   }

   public void method6(float var1, float var2, float var3, float var4) {
      this.field4.angle = (float)Math.toRadians(var1);
      this.field4.x = var2;
      this.field4.y = var3;
      this.field4.z = var4;
      double var5 = Math.sqrt(var2 * var2 + var3 * var3 + var4 * var4);
      float var7 = (float)(1.0 / var5);
      this.field4.x *= var7;
      this.field4.y *= var7;
      this.field4.z *= var7;
      this.field3.setIdentity();
      this.field3.setRotation(this.field4);
      this.field2.mul(this.field3);
   }

   public void scale(float var1, float var2, float var3) {
      this.field3.setIdentity();
      this.field3.m00 = var1;
      this.field3.m11 = var2;
      this.field3.m22 = var3;
      this.field2.mul(this.field3);
   }

   public void method7(float var1, float var2, float var3) {
      this.field5.set(var1, var2, var3);
      this.field3.setIdentity();
      this.field3.setTranslation(this.field5);
      this.field2.mul(this.field3);
   }

   public void method8(FloatBuffer var1) {
      int var2 = var1.position();
      this.field3.m00 = var1.get(var2);
      this.field3.m01 = var1.get(var2 + 1);
      this.field3.m02 = var1.get(var2 + 2);
      this.field3.m03 = var1.get(var2 + 3);
      this.field3.m10 = var1.get(var2 + 4);
      this.field3.m11 = var1.get(var2 + 5);
      this.field3.m12 = var1.get(var2 + 6);
      this.field3.m13 = var1.get(var2 + 7);
      this.field3.m20 = var1.get(var2 + 8);
      this.field3.m21 = var1.get(var2 + 9);
      this.field3.m22 = var1.get(var2 + 10);
      this.field3.m23 = var1.get(var2 + 11);
      this.field3.m30 = var1.get(var2 + 12);
      this.field3.m31 = var1.get(var2 + 13);
      this.field3.m32 = var1.get(var2 + 14);
      this.field3.m33 = var1.get(var2 + 15);
      this.field2.mul(this.field3);
   }
}
