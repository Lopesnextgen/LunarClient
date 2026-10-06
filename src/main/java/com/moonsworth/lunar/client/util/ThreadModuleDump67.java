package com.moonsworth.lunar.client.util;

import lombok.Generated;

public final class ThreadModuleDump67 {
   public static float method1(float var0, float var1, float var2) {
      return var0 < var1 ? var1 : (var0 > var2 ? var2 : var0);
   }

   public static double method2(double var0, double var2, double var4) {
      return Math.max(var2, Math.min(var0, var4));
   }

   public static float method3(float var0, float var1, float var2) {
      return Math.max(var1, Math.min(var0, var2));
   }

   public static byte method4(byte var0, byte var1, byte var2) {
      byte var3 = var0 <= var2 ? var0 : var2;
      return var1 >= var3 ? var1 : var3;
   }

   public static short method5(short var0, short var1, short var2) {
      short var3 = var0 <= var2 ? var0 : var2;
      return var1 >= var3 ? var1 : var3;
   }

   public static int method6(int var0, int var1, int var2) {
      return Math.max(var1, Math.min(var0, var2));
   }

   public static long method7(long var0, long var2, long var4) {
      return Math.max(var2, Math.min(var0, var4));
   }

   public static int method8(double var0) {
      int var2 = (int)var0;
      return var0 > var2 ? var2 + 1 : var2;
   }

   public static int method9(double var0) {
      int var2 = (int)var0;
      return var0 < var2 ? var2 - 1 : var2;
   }

   public static int method10(double var0) {
      int var2 = (int)var0;
      return var0 < var2 ? var2 - 1 : var2;
   }

   public static double method11(double var0, double var2, double var4) {
      return Math.min(Math.max(var0, var2), var4);
   }

   public static float method12(float var0, float var1, float var2) {
      return Math.min(Math.max(var0, var1), var2);
   }

   public static float method13(float var0) {
      var0 %= 360.0F;
      if (var0 >= 180.0F) {
         var0 -= 360.0F;
      }

      if (var0 < -180.0F) {
         var0 += 360.0F;
      }

      return var0;
   }

   public static double method14(double var0) {
      var0 %= 360.0;
      if (var0 >= 180.0) {
         var0 -= 360.0;
      }

      if (var0 < -180.0) {
         var0 += 360.0;
      }

      return var0;
   }

   public static float lerp(float var0, float var1, float var2) {
      return var2 >= 1.0F ? var1 : (var2 <= 0.0F ? var0 : var0 + (var1 - var0) * var2);
   }

   public static double method15(double var0, double var2, float var4) {
      return var4 >= 1.0F ? var2 : (var4 <= 0.0F ? var0 : var0 + (var2 - var0) * var4);
   }

   public static float method16(float var0, float var1, float var2, float var3, float var4) {
      return (var0 - var1) / (var2 - var1) * (var4 - var3) + var3;
   }

   public static double method17(double var0, double var2, double var4, double value) {
      double var8 = var0 - var4;
      double var10 = var2 - value;
      return var8 * var8 + var10 * var10;
   }

   public static boolean method18(double var0, double var2, double var4) {
      return Math.abs(var0 - var2) < var4;
   }

   @Generated
   private ThreadModuleDump67() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
