package com.moonsworth.lunar.client.cosmetics.gecko;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.joml.Vector3d;
import org.joml.Vector3f;

public class VectorConverter {
   public VectorConverter() {
   }

   public static Vector3d method1(double[] items0) {
      Validate.validIndex(ArrayUtils.toObject(items0), 2);
      return new Vector3d(items0[0], items0[1], items0[2]);
   }

   public static Vector3f method2(float[] items0) {
      Validate.validIndex(ArrayUtils.toObject(items0), 2);
      return new Vector3f(items0[0], items0[1], items0[2]);
   }

   public static Vector3f method3(Vector3d vector3d0) {
      return new Vector3f((float)vector3d0.x, (float)vector3d0.y, (float)vector3d0.z);
   }
}
