package com.moonsworth.lunar.bridge.minecraft;

import org.joml.Quaternionf;
import org.joml.Vector3f;

@FunctionalInterface
public interface RotationOperator {
   RotationOperator XN = arg0 -> new Quaternionf().rotationX(-arg0);
   RotationOperator XP = arg0 -> new Quaternionf().rotationX(arg0);
   RotationOperator YN = arg0 -> new Quaternionf().rotationY(-arg0);
   RotationOperator YP = arg0 -> new Quaternionf().rotationY(arg0);
   RotationOperator ZN = arg0 -> new Quaternionf().rotationZ(-arg0);
   RotationOperator ZP = arg0 -> new Quaternionf().rotationZ(arg0);

   static RotationOperator of(Vector3f vector3f0) {
      return arg1 -> new Quaternionf().rotationAxis(arg1, vector3f0);
   }

   Quaternionf rotation(float value1);

   default Quaternionf rotationDegrees(float value1) {
      return this.rotation(value1 * (float) (Math.PI / 180.0));
   }
}
