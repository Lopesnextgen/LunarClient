package com.moonsworth.lunar.client.render.particle;

public class Interpolations {
   public Interpolations() {
   }

   public static float lerp(float value0, float value1, float value2) {
      return value0 + (value1 - value0) * value2;
   }

   public static float lerpYaw(float value0, float value1, float value2) {
      value0 = AngleMath.wrapDegrees(value0);
      value1 = AngleMath.wrapDegrees(value1);
      return lerp(value0, normalizeYaw(value0, value1), value2);
   }

   public static double cubicHermite(double value0, double value2, double value4, double value6, double value8) {
      double value10 = -0.5 * value0 + 1.5 * value2 - 1.5 * value4 + 0.5 * value6;
      double value12 = value0 - 2.5 * value2 + 2.0 * value4 - 0.5 * value6;
      double value14 = -0.5 * value0 + 0.5 * value4;
      return ((value10 * value8 + value12) * value8 + value14) * value8 + value2;
   }

   public static double cubicHermiteYaw(float value0, float value1, float value2, float value3, float value4) {
      value0 = AngleMath.wrapDegrees(value0);
      value1 = AngleMath.wrapDegrees(value1);
      value2 = AngleMath.wrapDegrees(value2);
      value3 = AngleMath.wrapDegrees(value3);
      value1 = normalizeYaw(value0, value1);
      value2 = normalizeYaw(value1, value2);
      value3 = normalizeYaw(value2, value3);
      return cubicHermite(value0, value1, value2, value3, value4);
   }

   public static float cubic(float value0, float value1, float value2, float value3, float value4) {
      float value5 = value3 - value2 - value0 + value1;
      float value6 = value0 - value1 - value5;
      float value7 = value2 - value0;
      return ((value5 * value4 + value6) * value4 + value7) * value4 + value1;
   }

   public static float cubicYaw(float value0, float value1, float value2, float value3, float value4) {
      value0 = AngleMath.wrapDegrees(value0);
      value1 = AngleMath.wrapDegrees(value1);
      value2 = AngleMath.wrapDegrees(value2);
      value3 = AngleMath.wrapDegrees(value3);
      value1 = normalizeYaw(value0, value1);
      value2 = normalizeYaw(value1, value2);
      value3 = normalizeYaw(value2, value3);
      return cubic(value0, value1, value2, value3, value4);
   }

   public static float bezierX(float value0, float value1, float value2, float value3) {
      float value4 = value2;
      float value5 = bezier(0.0F, value0, value1, 1.0F, value2);
      float value6 = Math.copySign(0.1F, value2 - value5);

      while (Math.abs(value2 - value5) > value3) {
         float value7 = value6;
         value4 += value6;
         value5 = bezier(0.0F, value0, value1, 1.0F, value4);
         if (Math.copySign(value6, value2 - value5) != value7) {
            value6 *= -0.25F;
         }
      }

      return value4;
   }

   public static float bezierX(float value0, float value1, float value2) {
      return bezierX(value0, value1, value2, 5.0E-4F);
   }

   public static float bezier(float value0, float value1, float value2, float value3, float value4) {
      float value5 = lerp(value0, value1, value4);
      float value6 = lerp(value1, value2, value4);
      float value7 = lerp(value2, value3, value4);
      float value8 = lerp(value5, value6, value4);
      float value9 = lerp(value6, value7, value4);
      return lerp(value8, value9, value4);
   }

   public static float normalizeYaw(float value0, float value1) {
      float value2 = value0 - value1;
      if (!(value2 > 180.0F) && !(value2 < -180.0F)) {
         return value1;
      }

      value2 = Math.copySign(360.0F - Math.abs(value2), value2);
      return value0 + value2;
   }

   public static float envelope(float value0, float value1, float value2) {
      return envelope(value0, 0.0F, value2, value1 - value2, value1);
   }

   public static float envelope(float value0, float value1, float value2, float value3, float value4) {
      if (value0 < value1 || value0 > value4) {
         return 0.0F;
      } else if (value0 < value2) {
         return (value0 - value1) / (value2 - value1);
      } else {
         return value0 > value3 ? 1.0F - (value0 - value3) / (value4 - value3) : 1.0F;
      }
   }

   public static double lerp(double value0, double value2, double value4) {
      return value0 + (value2 - value0) * value4;
   }

   public static double lerpYaw(double value0, double value2, double value4) {
      value0 = AngleMath.wrapDegrees(value0);
      value2 = AngleMath.wrapDegrees(value2);
      return lerp(value0, normalizeYaw(value0, value2), value4);
   }

   public static double cubic(double value0, double value2, double value4, double value6, double value8) {
      double value10 = value6 - value4 - value0 + value2;
      double value12 = value0 - value2 - value10;
      double value14 = value4 - value0;
      return ((value10 * value8 + value12) * value8 + value14) * value8 + value2;
   }

   public static double cubicYaw(double value0, double value2, double value4, double value6, double value8) {
      value0 = AngleMath.wrapDegrees(value0);
      value2 = AngleMath.wrapDegrees(value2);
      value4 = AngleMath.wrapDegrees(value4);
      value6 = AngleMath.wrapDegrees(value6);
      value2 = normalizeYaw(value0, value2);
      value4 = normalizeYaw(value2, value4);
      value6 = normalizeYaw(value4, value6);
      return cubic(value0, value2, value4, value6, value8);
   }

   public static double bezierX(double value0, double value2, double value4, double value6) {
      double value8 = value4;
      double value10 = bezier(0.0, value0, value2, 1.0, value4);
      double value12 = Math.copySign(0.1F, value4 - value10);

      while (Math.abs(value4 - value10) > value6) {
         double value14 = value12;
         value8 += value12;
         value10 = bezier(0.0, value0, value2, 1.0, value8);
         if (Math.copySign(value12, value4 - value10) != value14) {
            value12 *= -0.25;
         }
      }

      return value8;
   }

   public static double bezierX(double value0, double value2, float value4) {
      return bezierX(value0, value2, value4, 5.0E-4F);
   }

   public static double bezier(double value0, double value2, double value4, double value6, double value8) {
      double value10 = lerp(value0, value2, value8);
      double value12 = lerp(value2, value4, value8);
      double value14 = lerp(value4, value6, value8);
      double value16 = lerp(value10, value12, value8);
      double value18 = lerp(value12, value14, value8);
      return lerp(value16, value18, value8);
   }

   public static double normalizeYaw(double value0, double value2) {
      double value4 = value0 - value2;
      if (!(value4 > 180.0) && !(value4 < -180.0)) {
         return value2;
      }

      value4 = Math.copySign(360.0 - Math.abs(value4), value4);
      return value0 + value4;
   }

   public static double envelope(double value0, double value2, double value4) {
      return envelope(value0, 0.0, value4, value2 - value4, value2);
   }

   public static double envelope(double value0, double value2, double value4, double value6, double value8) {
      if (value0 < value2 || value0 > value8) {
         return 0.0;
      } else if (value0 < value4) {
         return (value0 - value2) / (value4 - value2);
      } else {
         return value0 > value6 ? 1.0 - (value0 - value6) / (value8 - value6) : 1.0;
      }
   }

   public static byte method1(byte number0, byte number1, float value2) {
      value2 = ClampUtils.clamp(value2, 0.0F, 1.0F);
      float value3 = number0 + value2 * (number1 - number0);
      return (byte)value3;
   }

   public static byte method2(byte number0, byte number1, float value2) {
      int number4 = (number1 - number0 + 16) % 16;
      int number5 = (number0 - number1 + 16) % 16;
      float value3;
      if (number4 <= number5) {
         value3 = number0 + value2 * number4;
      } else {
         value3 = number0 - value2 * number5;
      }

      value3 = (value3 + 16.0F) % 16.0F;
      return (byte)value3;
   }
}
