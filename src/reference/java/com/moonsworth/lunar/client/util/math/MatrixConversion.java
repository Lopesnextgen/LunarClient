package com.moonsworth.lunar.client.util.math;

import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class MatrixConversion {
   public MatrixConversion() {
   }

   public static Matrix4f method1(javax.vecmath.Matrix4f matrix4f0) {
      Matrix4f matrix4f1 = new Matrix4f();
      matrix4f1.m00(matrix4f0.m00);
      matrix4f1.m01(matrix4f0.m10);
      matrix4f1.m02(matrix4f0.m20);
      matrix4f1.m03(matrix4f0.m30);
      matrix4f1.m10(matrix4f0.m01);
      matrix4f1.m11(matrix4f0.m11);
      matrix4f1.m12(matrix4f0.m21);
      matrix4f1.m13(matrix4f0.m31);
      matrix4f1.m20(matrix4f0.m02);
      matrix4f1.m21(matrix4f0.m12);
      matrix4f1.m22(matrix4f0.m22);
      matrix4f1.m23(matrix4f0.m32);
      matrix4f1.m30(matrix4f0.m03);
      matrix4f1.m31(matrix4f0.m13);
      matrix4f1.m32(matrix4f0.m23);
      matrix4f1.m33(matrix4f0.m33);
      return matrix4f1;
   }

   public static Matrix3f method2(javax.vecmath.Matrix3f matrix3f0) {
      Matrix3f matrix3f1 = new Matrix3f();
      matrix3f1.m00(matrix3f0.m00);
      matrix3f1.m01(matrix3f0.m10);
      matrix3f1.m02(matrix3f0.m20);
      matrix3f1.m10(matrix3f0.m01);
      matrix3f1.m11(matrix3f0.m11);
      matrix3f1.m12(matrix3f0.m21);
      matrix3f1.m20(matrix3f0.m02);
      matrix3f1.m21(matrix3f0.m12);
      matrix3f1.m22(matrix3f0.m22);
      return matrix3f1;
   }

   public static javax.vecmath.Matrix4f method3(Matrix4f matrix4f0) {
      javax.vecmath.Matrix4f matrix4f1 = new javax.vecmath.Matrix4f();
      matrix4f1.m00 = matrix4f0.m00();
      matrix4f1.m01 = matrix4f0.m10();
      matrix4f1.m02 = matrix4f0.m20();
      matrix4f1.m03 = matrix4f0.m30();
      matrix4f1.m10 = matrix4f0.m01();
      matrix4f1.m11 = matrix4f0.m11();
      matrix4f1.m12 = matrix4f0.m21();
      matrix4f1.m13 = matrix4f0.m31();
      matrix4f1.m20 = matrix4f0.m02();
      matrix4f1.m21 = matrix4f0.m12();
      matrix4f1.m22 = matrix4f0.m22();
      matrix4f1.m23 = matrix4f0.m32();
      matrix4f1.m30 = matrix4f0.m03();
      matrix4f1.m31 = matrix4f0.m13();
      matrix4f1.m32 = matrix4f0.m23();
      matrix4f1.m33 = matrix4f0.m33();
      return matrix4f1;
   }
}
