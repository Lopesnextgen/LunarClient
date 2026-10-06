package com.moonsworth.lunar.client.render.particle;

import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;

public class ParticleMath {
   public ParticleMath() {
   }

   public static Vector3f method1(Matrix3f matrix3f0) {
      Matrix3f matrix3f1 = new Matrix3f(matrix3f0);
      Matrix3f matrix3f2 = new Matrix3f();
      Matrix3f matrix3f3 = new Matrix3f();
      matrix3f3.setIdentity();
      matrix3f2.setIdentity();
      matrix3f2.mul(2.0F);
      matrix3f1.add(matrix3f3);
      matrix3f1.invert();
      matrix3f1.mul(4.0F);
      matrix3f2.sub(matrix3f1);
      return new Vector3f(matrix3f2.m21, -matrix3f2.m20, matrix3f2.m10);
   }
}
