package com.moonsworth.lunar.client.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.NumberFormat.Style;
import java.util.Locale;
import java.util.Optional;
import lombok.Generated;

public final class ThreadModuleDump40 {
   private static final NumberFormat field1 = NumberFormat.getCompactNumberInstance(Locale.US, Style.SHORT);
   private static final DecimalFormat field2 = new DecimalFormat("###,###,###");

   public static long method1(String var0) {
      try {
         return Long.parseLong(var0);
      } catch (NumberFormatException var2) {
         return 0L;
      }
   }

   public static double method2(String var0) {
      try {
         return Double.parseDouble(var0);
      } catch (NumberFormatException var2) {
         return 0.0;
      }
   }

   public static int method3(String var0) {
      try {
         return Integer.parseInt(var0);
      } catch (NumberFormatException var2) {
         return 0;
      }
   }

   public static int method4(String var0, int var1) {
      try {
         return Integer.parseInt(var0);
      } catch (NumberFormatException var3) {
         return var1;
      }
   }

   public static int method5(String var0) {
      try {
         return ThreadModuleDump83.parseRoman(var0);
      } catch (Exception var2) {
         return method3(var0);
      }
   }

   public static Optional<Long> method6(String var0) {
      try {
         return Optional.of(Long.parseLong(var0));
      } catch (NumberFormatException var2) {
         return Optional.empty();
      }
   }

   public static Optional<Double> method7(String var0) {
      try {
         return Optional.of(Double.parseDouble(var0));
      } catch (NumberFormatException var2) {
         return Optional.empty();
      }
   }

   public static Optional<Integer> method8(String var0) {
      try {
         return Optional.of(Integer.parseInt(var0));
      } catch (NumberFormatException var2) {
         return Optional.empty();
      }
   }

   public static int method9(String var0) {
      char var1 = Character.toLowerCase(var0.charAt(var0.length() - 1));
      switch (var1) {
         case 'b':
            float var4 = Float.parseFloat(var0.substring(0, var0.length() - 1));
            return Math.round(var4 * 1.0E9F);
         case 'k':
            float var3 = Float.parseFloat(var0.substring(0, var0.length() - 1));
            return Math.round(var3 * 1000.0F);
         case 'm':
            float var2 = Float.parseFloat(var0.substring(0, var0.length() - 1));
            return Math.round(var2 * 1000000.0F);
         default:
            return Integer.parseInt(var0.replaceAll(",", ""));
      }
   }

   public static String method10(long var0) {
      return method11(var0, 0, 1);
   }

   public static String method11(long var0, int var2, int var3) {
      field1.setMinimumFractionDigits(var2);
      field1.setMaximumFractionDigits(var3);
      return field1.format(var0);
   }

   public static String method12(double var0) {
      return field2.format(var0);
   }

   @Generated
   private ThreadModuleDump40() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
