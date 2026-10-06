package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.math.BigInteger;
import java.math.RoundingMode;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
@CanIgnoreReturnValue
final class MathPreconditions {
   static int checkPositive(@Nullable String text0, int number1) {
      if (number1 <= 0) {
         throw new IllegalArgumentException(text0 + " (" + number1 + ") must be > 0");
      } else {
         return number1;
      }
   }

   static long checkPositive(@Nullable String text0, long number1) {
      if (number1 <= 0L) {
         throw new IllegalArgumentException(text0 + " (" + number1 + ") must be > 0");
      } else {
         return number1;
      }
   }

   static BigInteger checkPositive(@Nullable String text0, BigInteger number1) {
      if (number1.signum() <= 0) {
         throw new IllegalArgumentException(text0 + " (" + number1 + ") must be > 0");
      } else {
         return number1;
      }
   }

   static int checkNonNegative(@Nullable String text0, int number1) {
      if (number1 < 0) {
         throw new IllegalArgumentException(text0 + " (" + number1 + ") must be >= 0");
      } else {
         return number1;
      }
   }

   static long checkNonNegative(@Nullable String text0, long number1) {
      if (number1 < 0L) {
         throw new IllegalArgumentException(text0 + " (" + number1 + ") must be >= 0");
      } else {
         return number1;
      }
   }

   static BigInteger checkNonNegative(@Nullable String text0, BigInteger number1) {
      if (number1.signum() < 0) {
         throw new IllegalArgumentException(text0 + " (" + number1 + ") must be >= 0");
      } else {
         return number1;
      }
   }

   static double checkNonNegative(@Nullable String text0, double value1) {
      if (!(value1 >= 0.0)) {
         throw new IllegalArgumentException(text0 + " (" + value1 + ") must be >= 0");
      } else {
         return value1;
      }
   }

   static void checkRoundingUnnecessary(boolean flag0) {
      if (!flag0) {
         throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
      }
   }

   static void checkInRangeForRoundingInputs(boolean flag0, double value1, RoundingMode roundingmode3) {
      if (!flag0) {
         throw new ArithmeticException("rounded value is out of range for input " + value1 + " and rounding mode " + roundingmode3);
      }
   }

   static void checkNoOverflow(boolean flag0, String text1, int number2, int number3) {
      if (!flag0) {
         throw new ArithmeticException("overflow: " + text1 + "(" + number2 + ", " + number3 + ")");
      }
   }

   static void checkNoOverflow(boolean flag0, String text1, long number2, long number4) {
      if (!flag0) {
         throw new ArithmeticException("overflow: " + text1 + "(" + number2 + ", " + number4 + ")");
      }
   }

   private MathPreconditions() {
   }
}
