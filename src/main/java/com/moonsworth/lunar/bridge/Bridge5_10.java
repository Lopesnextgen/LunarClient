package com.moonsworth.lunar.bridge;

public class Bridge5_10 {
   public static double method1(float var0) {
      return var0;
   }

   public static float method2(double var0) {
      return (float)var0;
   }

   public static long method3(int var0) {
      return var0;
   }

   public static float method4(int var0) {
      return var0;
   }

   public static float[] method5(int[] var0) {
      float[] var1 = new float[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = var0[var2];
      }

      return var1;
   }
}
