package com.moonsworth.lunar.client.util.math;

import com.moonsworth.lunar.client.render.particle.ClampUtils;

public class ColorMath {
   private static final double field1 = 360.0;
   private static final double field2 = 180.0;
   private static final double field3 = 255.0;
   private static final double field4 = 0.04045;
   private static final double field5 = 12.92;
   private static final double field6 = 0.055;
   private static final double field7 = 1.055;
   private static final double field8 = 2.4;
   private static final double field9 = 0.4124;
   private static final double field10 = 0.3576;
   private static final double field11 = 0.1805;
   private static final double field12 = 0.2126;
   private static final double field13 = 0.7152;
   private static final double field14 = 0.0722;
   private static final double field15 = 0.0193;
   private static final double field16 = 0.1192;
   private static final double field17 = 0.9505;
   private static final double field18 = 95.047;
   private static final double field19 = 100.0;
   private static final double field20 = 108.883;
   private static final double field21 = 100.0;
   private static final double field22 = 0.008856;
   private static final double field23 = 7.787;
   private static final double field24 = 0.13793103448275862;
   private static final double field25 = 116.0;
   private static final double field26 = 16.0;
   private static final double field27 = 500.0;
   private static final double field28 = 200.0;
   private static final double field29 = 3.2406;
   private static final double field30 = -1.5372;
   private static final double field31 = -0.4986;
   private static final double field32 = -0.9689;
   private static final double field33 = 1.8758;
   private static final double field34 = 0.0415;
   private static final double field35 = 0.0557;
   private static final double field36 = -0.204;
   private static final double field37 = 1.057;
   private static final double field38 = 0.0031308;

   public ColorMath() {
   }

   public static double method1(double value0, double value2, float value4) {
      double value5 = value2 - value0;
      if (value5 > 180.0) {
         value5 -= 360.0;
      } else if (value5 < -180.0) {
         value5 += 360.0;
      }

      double value7 = value0 + value4 * value5;
      if (value7 < 0.0) {
         value7 += 360.0;
      }

      if (value7 >= 360.0) {
         value7 -= 360.0;
      }

      return value7;
   }

   public static ColorMath.Data method2(int number0) {
      ColorMath.LabColor data21 = method4(number0);
      double value2 = Math.hypot(data21.a, data21.b);
      double value4 = Math.toDegrees(Math.atan2(data21.b, data21.a));
      if (value4 < 0.0) {
         value4 += 360.0;
      }

      return new ColorMath.Data(data21.field1, value2, value4);
   }

   public static int method3(double value0, double value2, double value4, int number6) {
      double value7 = Math.toRadians(value4);
      double value9 = value2 * Math.cos(value7);
      double value11 = value2 * Math.sin(value7);
      return method5(value0, value9, value11, number6);
   }

   public static ColorMath.LabColor method4(int number0) {
      int number1 = number0 >> 16 & 0xFF;
      int number2 = number0 >> 8 & 0xFF;
      int number3 = number0 & 0xFF;
      double value4 = number1 / 255.0;
      double value6 = number2 / 255.0;
      double value8 = number3 / 255.0;
      value4 = value4 > 0.04045 ? Math.pow((value4 + 0.055) / 1.055, 2.4) : value4 / 12.92;
      value6 = value6 > 0.04045 ? Math.pow((value6 + 0.055) / 1.055, 2.4) : value6 / 12.92;
      value8 = value8 > 0.04045 ? Math.pow((value8 + 0.055) / 1.055, 2.4) : value8 / 12.92;
      double value10 = value4 * 0.4124 + value6 * 0.3576 + value8 * 0.1805;
      double value12 = value4 * 0.2126 + value6 * 0.7152 + value8 * 0.0722;
      double value14 = value4 * 0.0193 + value6 * 0.1192 + value8 * 0.9505;
      value10 *= 100.0;
      value12 *= 100.0;
      value14 *= 100.0;
      double value16 = value10 / 95.047;
      double value18 = value12 / 100.0;
      double value20 = value14 / 108.883;
      value16 = value16 > 0.008856 ? Math.cbrt(value16) : 7.787 * value16 + 0.13793103448275862;
      value18 = value18 > 0.008856 ? Math.cbrt(value18) : 7.787 * value18 + 0.13793103448275862;
      value20 = value20 > 0.008856 ? Math.cbrt(value20) : 7.787 * value20 + 0.13793103448275862;
      double value22 = 116.0 * value18 - 16.0;
      double value24 = 500.0 * (value16 - value18);
      double value26 = 200.0 * (value18 - value20);
      return new ColorMath.LabColor(value22, value24, value26);
   }

   private static int method5(double value0, double value2, double value4, int number6) {
      double value7 = (value0 + 16.0) / 116.0;
      double value9 = value2 / 500.0 + value7;
      double value11 = value7 - value4 / 200.0;
      double value13 = Math.pow(value9, 3.0);
      double value15 = Math.pow(value7, 3.0);
      double value17 = Math.pow(value11, 3.0);
      value9 = value13 > 0.008856 ? value13 : (value9 - 0.13793103448275862) / 7.787;
      value7 = value15 > 0.008856 ? value15 : (value7 - 0.13793103448275862) / 7.787;
      value11 = value17 > 0.008856 ? value17 : (value11 - 0.13793103448275862) / 7.787;
      double value19 = value9 * 95.047;
      double value21 = value7 * 100.0;
      double value23 = value11 * 108.883;
      value19 /= 100.0;
      value21 /= 100.0;
      value23 /= 100.0;
      double value25 = value19 * 3.2406 + value21 * -1.5372 + value23 * -0.4986;
      double value27 = value19 * -0.9689 + value21 * 1.8758 + value23 * 0.0415;
      double value29 = value19 * 0.0557 + value21 * -0.204 + value23 * 1.057;
      value25 = value25 > 0.0031308 ? 1.055 * Math.pow(value25, 0.4166666666666667) - 0.055 : 12.92 * value25;
      value27 = value27 > 0.0031308 ? 1.055 * Math.pow(value27, 0.4166666666666667) - 0.055 : 12.92 * value27;
      value29 = value29 > 0.0031308 ? 1.055 * Math.pow(value29, 0.4166666666666667) - 0.055 : 12.92 * value29;
      int number31 = ClampUtils.clamp((int)Math.round(value25 * 255.0), 0, 255);
      int number32 = ClampUtils.clamp((int)Math.round(value27 * 255.0), 0, 255);
      int number33 = ClampUtils.clamp((int)Math.round(value29 * 255.0), 0, 255);
      return number6 << 24 | number31 << 16 | number32 << 8 | number33;
   }

   public class Data {
      private final double field1;
      private final double field2;
      private final double field3;

      public Data(double value1, double value3, double value5) {
         this.field1 = value1;
         this.field2 = value3;
         this.field3 = value5;
      }

      public double method1() {
         return this.field1;
      }

      public double method2() {
         return this.field2;
      }

      public double method3() {
         return this.field3;
      }
   }

   public class LabColor {
      private final double field1;
      private final double a;
      private final double b;

      public LabColor(double value1, double value3, double value5) {
         this.field1 = value1;
         this.a = value3;
         this.b = value5;
      }

      public double method1() {
         return this.field1;
      }

      public double method2() {
         return this.a;
      }

      public double method3() {
         return this.b;
      }
   }
}
