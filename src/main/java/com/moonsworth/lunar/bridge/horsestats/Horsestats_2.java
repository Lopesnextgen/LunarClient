package com.moonsworth.lunar.bridge.horsestats;

import org.joml.Quaternionf;
import org.joml.Vector3f;

@FunctionalInterface
public interface Horsestats_2 {
   Horsestats_2 XN = var0 -> new Quaternionf().rotationX(-var0);
   Horsestats_2 XP = var0 -> new Quaternionf().rotationX(var0);
   Horsestats_2 YN = var0 -> new Quaternionf().rotationY(-var0);
   Horsestats_2 YP = var0 -> new Quaternionf().rotationY(var0);
   Horsestats_2 ZN = var0 -> new Quaternionf().rotationZ(-var0);
   Horsestats_2 ZP = var0 -> new Quaternionf().rotationZ(var0);

   static Horsestats_2 of(Vector3f var0) {
      return var1 -> new Quaternionf().rotationAxis(var1, var0);
   }

   Quaternionf rotation(float var1);

   default Quaternionf rotationDegrees(float var1) {
      return this.rotation(var1 * (float) (Math.PI / 180.0));
   }
}
