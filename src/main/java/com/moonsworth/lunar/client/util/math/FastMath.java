package com.moonsworth.lunar.client.util.math;

public class FastMath {
   private static final int field1 = 8192;
   private static final int field2 = 16384;
   private static final double field3 = Math.PI * 2;
   private static final double[] field4 = new double[8192];

   public FastMath() {
   }

   public static double sin(double value0) {
      return method1(value0 - (Math.PI / 2));
   }

   public static double method1(double value0) {
      int index2 = (int)(value0 / (Math.PI * 2) % 1.0 * 16384.0);
      if (index2 < 0) {
         index2 += 16384;
      }

      return index2 >= 8192 ? -field4[index2 - 8192] : field4[index2];
   }

   public static void method2() {
      double value0 = 0.0;
      double value2 = 0.0;

      for (int index4 = -200000000; index4 < 800000000; index4++) {
         double value5 = index4 / 1.0E8;
         double value7 = Math.sin(value5);
         double value9 = sin(value5);
         double value11 = Math.abs(value7 - value9);
         value2 += value11;
         if (value11 > value0) {
            value0 = value11;
         }
      }

      System.err.println("[LC Fast Math] Average error: " + value2 / 1.0E9);
      System.err.println("[LC Fast Math] Maximum error: " + value0);
   }

   static {
      for (int index0 = 0; index0 < 8192; index0++) {
         field4[index0] = Math.cos(index0 / 8192.0F * Math.PI);
      }
   }
}
