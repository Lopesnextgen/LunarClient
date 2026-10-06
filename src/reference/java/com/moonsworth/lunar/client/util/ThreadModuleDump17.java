package com.moonsworth.lunar.client.util;

import com.moonsworth.lunar.client.render.particle.ClampUtils;

public class ThreadModuleDump17 {
   private static final double field1 = 360.0;
   private static final double field2 = 180.0;
   private static final double field3 = 255.0;
   private static final double SRGB_LINEAR_THRESHOLD = 0.04045;
   private static final double SRGB_LINEAR_SCALE = 12.92;
   private static final double GAMMA_OFFSET = 0.055;
   private static final double GAMMA_SCALE = 1.055;
   private static final double GAMMA = 2.4;
   private static final double X_FROM_RED = 0.4124;
   private static final double X_FROM_GREEN = 0.3576;
   private static final double X_FROM_BLUE = 0.1805;
   private static final double Y_FROM_RED = 0.2126;
   private static final double Y_FROM_GREEN = 0.7152;
   private static final double Y_FROM_BLUE = 0.0722;
   private static final double Z_FROM_RED = 0.0193;
   private static final double Z_FROM_GREEN = 0.1192;
   private static final double Z_FROM_BLUE = 0.9505;
   private static final double WHITE_X = 95.047;
   private static final double WHITE_Y = 100.0;
   private static final double WHITE_Z = 108.883;
   private static final double PERCENT_SCALE = 100.0;
   private static final double LAB_EPSILON = 0.008856;
   private static final double LAB_KAPPA = 7.787;
   private static final double LAB_OFFSET = 0.13793103448275862;
   private static final double LAB_L_SCALE = 116.0;
   private static final double LAB_L_OFFSET = 16.0;
   private static final double LAB_A_SCALE = 500.0;
   private static final double LAB_B_SCALE = 200.0;
   private static final double RED_FROM_X = 3.2406;
   private static final double RED_FROM_Y = -1.5372;
   private static final double RED_FROM_Z = -0.4986;
   private static final double GREEN_FROM_X = -0.9689;
   private static final double GREEN_FROM_Y = 1.8758;
   private static final double GREEN_FROM_Z = 0.0415;
   private static final double BLUE_FROM_X = 0.0557;
   private static final double BLUE_FROM_Y = -0.204;
   private static final double BLUE_FROM_Z = 1.057;
   private static final double GAMMA_THRESHOLD = 0.0031308;

   public static double interpolateHue(double var0, double var2, float var4) {
      double var5 = var2 - var0;
      if (var5 > 180.0) {
         var5 -= 360.0;
      } else if (var5 < -180.0) {
         var5 += 360.0;
      }

      double var7 = var0 + var4 * var5;
      if (var7 < 0.0) {
         var7 += 360.0;
      }

      if (var7 >= 360.0) {
         var7 -= 360.0;
      }

      return var7;
   }

   public static ThreadModuleDump17.Data rgbToLch(int var0) {
      ThreadModuleDump17.Data2 var1 = rgbToLab(var0);
      double var2 = Math.hypot(var1.a, var1.b);
      double var4 = Math.toDegrees(Math.atan2(var1.b, var1.a));
      if (var4 < 0.0) {
         var4 += 360.0;
      }

      return new ThreadModuleDump17.Data(var1.field1, var2, var4);
   }

   public static int lchToRgb(double var0, double var2, double var4, int var6) {
      double var7 = Math.toRadians(var4);
      double var9 = var2 * Math.cos(var7);
      double var11 = var2 * Math.sin(var7);
      return labToRgb(var0, var9, var11, var6);
   }

   public static ThreadModuleDump17.Data2 rgbToLab(int var0) {
      int var1 = var0 >> 16 & 0xFF;
      int var2 = var0 >> 8 & 0xFF;
      int var3 = var0 & 0xFF;
      double var4 = var1 / 255.0;
      double var6 = var2 / 255.0;
      double var8 = var3 / 255.0;
      var4 = var4 > 0.04045 ? Math.pow((var4 + 0.055) / 1.055, 2.4) : var4 / 12.92;
      var6 = var6 > 0.04045 ? Math.pow((var6 + 0.055) / 1.055, 2.4) : var6 / 12.92;
      var8 = var8 > 0.04045 ? Math.pow((var8 + 0.055) / 1.055, 2.4) : var8 / 12.92;
      double var10 = var4 * 0.4124 + var6 * 0.3576 + var8 * 0.1805;
      double var12 = var4 * 0.2126 + var6 * 0.7152 + var8 * 0.0722;
      double var14 = var4 * 0.0193 + var6 * 0.1192 + var8 * 0.9505;
      var10 *= 100.0;
      var12 *= 100.0;
      var14 *= 100.0;
      double var16 = var10 / 95.047;
      double var18 = var12 / 100.0;
      double var20 = var14 / 108.883;
      var16 = var16 > 0.008856 ? Math.cbrt(var16) : 7.787 * var16 + 0.13793103448275862;
      var18 = var18 > 0.008856 ? Math.cbrt(var18) : 7.787 * var18 + 0.13793103448275862;
      var20 = var20 > 0.008856 ? Math.cbrt(var20) : 7.787 * var20 + 0.13793103448275862;
      double var22 = 116.0 * var18 - 16.0;
      double var24 = 500.0 * (var16 - var18);
      double var26 = 200.0 * (var18 - var20);
      return new ThreadModuleDump17.Data2(var22, var24, var26);
   }

   private static int labToRgb(double var0, double var2, double var4, int var6) {
      double var7 = (var0 + 16.0) / 116.0;
      double var9 = var2 / 500.0 + var7;
      double var11 = var7 - var4 / 200.0;
      double var13 = Math.pow(var9, 3.0);
      double var15 = Math.pow(var7, 3.0);
      double var17 = Math.pow(var11, 3.0);
      var9 = var13 > 0.008856 ? var13 : (var9 - 0.13793103448275862) / 7.787;
      var7 = var15 > 0.008856 ? var15 : (var7 - 0.13793103448275862) / 7.787;
      var11 = var17 > 0.008856 ? var17 : (var11 - 0.13793103448275862) / 7.787;
      double var19 = var9 * 95.047;
      double var21 = var7 * 100.0;
      double var23 = var11 * 108.883;
      var19 /= 100.0;
      var21 /= 100.0;
      var23 /= 100.0;
      double var25 = var19 * 3.2406 + var21 * -1.5372 + var23 * -0.4986;
      double var27 = var19 * -0.9689 + var21 * 1.8758 + var23 * 0.0415;
      double var29 = var19 * 0.0557 + var21 * -0.204 + var23 * 1.057;
      var25 = var25 > 0.0031308 ? 1.055 * Math.pow(var25, 0.4166666666666667) - 0.055 : 12.92 * var25;
      var27 = var27 > 0.0031308 ? 1.055 * Math.pow(var27, 0.4166666666666667) - 0.055 : 12.92 * var27;
      var29 = var29 > 0.0031308 ? 1.055 * Math.pow(var29, 0.4166666666666667) - 0.055 : 12.92 * var29;
      int var31 = ClampUtils.clamp((int)Math.round(var25 * 255.0), 0, 255);
      int var32 = ClampUtils.clamp((int)Math.round(var27 * 255.0), 0, 255);
      int var33 = ClampUtils.clamp((int)Math.round(var29 * 255.0), 0, 255);
      return var6 << 24 | var31 << 16 | var32 << 8 | var33;
   }

   public class Data {
      private final double field1;
      private final double field2;
      private final double field3;

      public Data(double var1, double var3, double var5) {
         this.MAX_HUE = var1;
         this.HALF_HUE = var3;
         this.MAX_CHANNEL = var5;
      }

      public double interpolateHue() {
         return this.MAX_HUE;
      }

      public double rgbToLch() {
         return this.HALF_HUE;
      }

      public double lchToRgb() {
         return this.MAX_CHANNEL;
      }
   }

   public class Data2 {
      private final double field1;
      private final double a;
      private final double b;

      public Data2(double var1, double var3, double var5) {
         this.MAX_HUE = var1;
         this.a = var3;
         this.b = var5;
      }

      public double interpolateHue() {
         return this.MAX_HUE;
      }

      public double rgbToLch() {
         return this.a;
      }

      public double lchToRgb() {
         return this.b;
      }
   }
}
