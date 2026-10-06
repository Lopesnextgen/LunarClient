package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.math.BigInteger;
import java.math.RoundingMode;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
@CanIgnoreReturnValue
final class MixinHelper10_4 {
   static int checkPositive(@Nullable String var0, int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException(var0 + " (" + var1 + ") must be > 0");
      } else {
         return var1;
      }
   }

   static long checkPositive(@Nullable String var0, long var1) {
      if (var1 <= 0L) {
         throw new IllegalArgumentException(var0 + " (" + var1 + ") must be > 0");
      } else {
         return var1;
      }
   }

   static BigInteger checkPositive(@Nullable String var0, BigInteger var1) {
      if (var1.signum() <= 0) {
         throw new IllegalArgumentException(var0 + " (" + var1 + ") must be > 0");
      } else {
         return var1;
      }
   }

   static int checkNonNegative(@Nullable String var0, int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException(var0 + " (" + var1 + ") must be >= 0");
      } else {
         return var1;
      }
   }

   static long checkNonNegative(@Nullable String var0, long var1) {
      if (var1 < 0L) {
         throw new IllegalArgumentException(var0 + " (" + var1 + ") must be >= 0");
      } else {
         return var1;
      }
   }

   static BigInteger checkNonNegative(@Nullable String var0, BigInteger var1) {
      if (var1.signum() < 0) {
         throw new IllegalArgumentException(var0 + " (" + var1 + ") must be >= 0");
      } else {
         return var1;
      }
   }

   static double checkNonNegative(@Nullable String var0, double var1) {
      if (!(var1 >= 0.0)) {
         throw new IllegalArgumentException(var0 + " (" + var1 + ") must be >= 0");
      } else {
         return var1;
      }
   }

   static void checkRoundingUnnecessary(boolean var0) {
      if (!var0) {
         throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
      }
   }

   static void checkInRangeForRoundingInputs(boolean var0, double var1, RoundingMode var3) {
      if (!var0) {
         throw new ArithmeticException("rounded value is out of range for input " + var1 + " and rounding mode " + var3);
      }
   }

   static void checkNoOverflow(boolean var0, String var1, int var2, int var3) {
      if (!var0) {
         throw new ArithmeticException("overflow: " + var1 + "(" + var2 + ", " + var3 + ")");
      }
   }

   static void checkNoOverflow(boolean var0, String var1, long var2, long var4) {
      if (!var0) {
         throw new ArithmeticException("overflow: " + var1 + "(" + var2 + ", " + var4 + ")");
      }
   }

   private MixinHelper10_4() {
   }
}
