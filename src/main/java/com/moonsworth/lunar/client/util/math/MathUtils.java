package com.moonsworth.lunar.client.util.math;

import lombok.Generated;

public final class MathUtils {
   public static float method1(float value0, float value1, float value2) {
      return value0 < value1 ? value1 : (value0 > value2 ? value2 : value0);
   }

   public static double method2(double value0, double value2, double value4) {
      return Math.max(value2, Math.min(value0, value4));
   }

   public static float method3(float value0, float value1, float value2) {
      return Math.max(value1, Math.min(value0, value2));
   }

   public static byte method4(byte number0, byte number1, byte number2) {
      byte number3 = number0 <= number2 ? number0 : number2;
      return number1 >= number3 ? number1 : number3;
   }

   public static short method5(short number0, short number1, short number2) {
      short number3 = number0 <= number2 ? number0 : number2;
      return number1 >= number3 ? number1 : number3;
   }

   public static int method6(int number0, int number1, int number2) {
      return Math.max(number1, Math.min(number0, number2));
   }

   public static long method7(long number0, long number2, long value) {
      return Math.max(number2, Math.min(number0, value));
   }

   public static int method8(double value0) {
      int number2 = (int)value0;
      return value0 > number2 ? number2 + 1 : number2;
   }

   public static int method9(double value0) {
      int number2 = (int)value0;
      return value0 < number2 ? number2 - 1 : number2;
   }

   public static int method10(double value0) {
      int number2 = (int)value0;
      return value0 < number2 ? number2 - 1 : number2;
   }

   public static double method11(double value0, double value2, double value4) {
      return Math.min(Math.max(value0, value2), value4);
   }

   public static float method12(float value0, float value1, float value2) {
      return Math.min(Math.max(value0, value1), value2);
   }

   public static float method13(float value0) {
      value0 %= 360.0F;
      if (value0 >= 180.0F) {
         value0 -= 360.0F;
      }

      if (value0 < -180.0F) {
         value0 += 360.0F;
      }

      return value0;
   }

   public static double method14(double value0) {
      value0 %= 360.0;
      if (value0 >= 180.0) {
         value0 -= 360.0;
      }

      if (value0 < -180.0) {
         value0 += 360.0;
      }

      return value0;
   }

   public static float lerp(float value0, float value1, float value2) {
      return value2 >= 1.0F ? value1 : (value2 <= 0.0F ? value0 : value0 + (value1 - value0) * value2);
   }

   public static double method15(double value0, double value2, float value4) {
      return value4 >= 1.0F ? value2 : (value4 <= 0.0F ? value0 : value0 + (value2 - value0) * value4);
   }

   public static float method16(float value0, float value1, float value2, float value, float value4) {
      return (value0 - value1) / (value2 - value1) * (value4 - value) + value;
   }

   public static double method17(double value0, double value2, double value4, double value) {
      double value8 = value0 - value4;
      double value10 = value2 - value;
      return value8 * value8 + value10 * value10;
   }

   public static boolean method18(double value0, double value2, double value4) {
      return Math.abs(value0 - value2) < value4;
   }

   @Generated
   private MathUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
