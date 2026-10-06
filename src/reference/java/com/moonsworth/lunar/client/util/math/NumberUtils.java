package com.moonsworth.lunar.client.util.math;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.NumberFormat.Style;
import java.util.Locale;
import java.util.Optional;
import lombok.Generated;
import com.moonsworth.lunar.client.util.text.RomanNumeralParser;

public final class NumberUtils {
   private static final NumberFormat field1 = NumberFormat.getCompactNumberInstance(Locale.US, Style.SHORT);
   private static final DecimalFormat field2 = new DecimalFormat("###,###,###");

   public static long method1(String text0) {
      try {
         return Long.parseLong(text0);
      } catch (NumberFormatException numberformatexception2) {
         return 0L;
      }
   }

   public static double method2(String text0) {
      try {
         return Double.parseDouble(text0);
      } catch (NumberFormatException numberformatexception2) {
         return 0.0;
      }
   }

   public static int method3(String text0) {
      try {
         return Integer.parseInt(text0);
      } catch (NumberFormatException numberformatexception2) {
         return 0;
      }
   }

   public static int method4(String text0, int value) {
      try {
         return Integer.parseInt(text0);
      } catch (NumberFormatException numberformatexception3) {
         return value;
      }
   }

   public static int method5(String text0) {
      try {
         return RomanNumeralParser.romanToInt(text0);
      } catch (Exception exception2) {
         return method3(text0);
      }
   }

   public static Optional<Long> method6(String text0) {
      try {
         return Optional.of(Long.parseLong(text0));
      } catch (NumberFormatException numberformatexception2) {
         return Optional.empty();
      }
   }

   public static Optional<Double> method7(String text0) {
      try {
         return Optional.of(Double.parseDouble(text0));
      } catch (NumberFormatException numberformatexception2) {
         return Optional.empty();
      }
   }

   public static Optional<Integer> method8(String text0) {
      try {
         return Optional.of(Integer.parseInt(text0));
      } catch (NumberFormatException numberformatexception2) {
         return Optional.empty();
      }
   }

   public static int method9(String text0) {
      char character1 = Character.toLowerCase(text0.charAt(text0.length() - 1));
      switch (character1) {
         case 'b':
            float value4 = Float.parseFloat(text0.substring(0, text0.length() - 1));
            return Math.round(value4 * 1.0E9F);
         case 'k':
            float value3 = Float.parseFloat(text0.substring(0, text0.length() - 1));
            return Math.round(value3 * 1000.0F);
         case 'm':
            float value2 = Float.parseFloat(text0.substring(0, text0.length() - 1));
            return Math.round(value2 * 1000000.0F);
         default:
            return Integer.parseInt(text0.replaceAll(",", ""));
      }
   }

   public static String method10(long number0) {
      return method11(number0, 0, 1);
   }

   public static String method11(long number0, int value, int value2) {
      field1.setMinimumFractionDigits(value);
      field1.setMaximumFractionDigits(value2);
      return field1.format(number0);
   }

   public static String method12(double value) {
      return field2.format(value);
   }

   @Generated
   private NumberUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
