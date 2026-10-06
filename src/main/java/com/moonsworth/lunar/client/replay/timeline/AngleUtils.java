package com.moonsworth.lunar.client.replay.timeline;

public class AngleUtils {
   public AngleUtils() {
   }

   public static double method1(double value0, double value) {
      double value4 = Math.abs(value - value0);
      if (value4 > 180.0) {
         value0 += value0 < value ? 360.0 : -360.0;
      }

      return value0;
   }

   public static double method2(double value0) {
      return (value0 % 360.0 + 360.0) % 360.0;
   }
}
