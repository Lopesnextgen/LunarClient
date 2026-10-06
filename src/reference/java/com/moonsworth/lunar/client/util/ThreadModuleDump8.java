package com.moonsworth.lunar.client.util;

import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class ThreadModuleDump8 {
   public static Matrix4f toJoml(javax.vecmath.Matrix4f var0) {
      Matrix4f var1 = new Matrix4f();
      var1.m00(var0.m00);
      var1.m01(var0.m10);
      var1.m02(var0.m20);
      var1.m03(var0.m30);
      var1.m10(var0.m01);
      var1.m11(var0.m11);
      var1.m12(var0.m21);
      var1.m13(var0.m31);
      var1.m20(var0.m02);
      var1.m21(var0.m12);
      var1.m22(var0.m22);
      var1.m23(var0.m32);
      var1.m30(var0.m03);
      var1.m31(var0.m13);
      var1.m32(var0.m23);
      var1.m33(var0.m33);
      return var1;
   }

   public static Matrix3f toJoml(javax.vecmath.Matrix3f var0) {
      Matrix3f var1 = new Matrix3f();
      var1.m00(var0.m00);
      var1.m01(var0.m10);
      var1.m02(var0.m20);
      var1.m10(var0.m01);
      var1.m11(var0.m11);
      var1.m12(var0.m21);
      var1.m20(var0.m02);
      var1.m21(var0.m12);
      var1.m22(var0.m22);
      return var1;
   }

   public static javax.vecmath.Matrix4f toVecmath(Matrix4f var0) {
      javax.vecmath.Matrix4f var1 = new javax.vecmath.Matrix4f();
      var1.m00 = var0.m00();
      var1.m01 = var0.m10();
      var1.m02 = var0.m20();
      var1.m03 = var0.m30();
      var1.m10 = var0.m01();
      var1.m11 = var0.m11();
      var1.m12 = var0.m21();
      var1.m13 = var0.m31();
      var1.m20 = var0.m02();
      var1.m21 = var0.m12();
      var1.m22 = var0.m22();
      var1.m23 = var0.m32();
      var1.m30 = var0.m03();
      var1.m31 = var0.m13();
      var1.m32 = var0.m23();
      var1.m33 = var0.m33();
      return var1;
   }
}
