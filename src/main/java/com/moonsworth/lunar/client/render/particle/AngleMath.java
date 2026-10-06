package com.moonsworth.lunar.client.render.particle;

public class AngleMath {
   public AngleMath() {
   }

   public static float wrapDegrees(float value0) {
      value0 %= 360.0F;
      if (value0 >= 180.0F) {
         value0 -= 360.0F;
      }

      if (value0 < -180.0F) {
         value0 += 360.0F;
      }

      return value0;
   }

   public static double wrapDegrees(double value0) {
      value0 %= 360.0;
      if (value0 >= 180.0) {
         value0 -= 360.0;
      }

      if (value0 < -180.0) {
         value0 += 360.0;
      }

      return value0;
   }

   public static int wrapDegrees(int value) {
      value %= 360;
      if (value >= 180) {
         value -= 360;
      }

      if (value < -180) {
         value += 360;
      }

      return value;
   }
}
