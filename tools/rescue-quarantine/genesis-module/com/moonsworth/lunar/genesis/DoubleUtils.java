package com.moonsworth.lunar.genesis;

import java.math.BigInteger;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.eventbus.Subscribe;
import com.google.common.base.Preconditions;

@GwtIncompatible
final class DoubleUtils {
   static final long field1 = 4503599627370495L;
   static final long field2 = 9218868437227405312L;
   static final long field3 = Long.MIN_VALUE;
   static final int field4 = 52;
   static final int field5 = 1023;
   static final long field6 = 4503599627370496L;
   @Subscribe
   static final long field7 = 4607182418800017408L;

   private DoubleUtils() {
   }

   static double nextDown(double value0) {
      return -Math.nextUp(-value0);
   }

   static long getSignificand(double value0) {
      Preconditions.checkArgument(isFinite(value0), "not a normal value");
      int number2 = Math.getExponent(value0);
      long number3 = Double.doubleToRawLongBits(value0);
      number3 &= 4503599627370495L;
      return number2 == -1023 ? number3 << 1 : number3 | 4503599627370496L;
   }

   static boolean isFinite(double value0) {
      return Math.getExponent(value0) <= 1023;
   }

   static boolean isNormal(double value0) {
      return Math.getExponent(value0) >= -1022;
   }

   static double scaleNormalize(double value0) {
      long number2 = Double.doubleToRawLongBits(value0) & 4503599627370495L;
      return Double.longBitsToDouble(number2 | 4607182418800017408L);
   }

   static double bigToDouble(BigInteger number0) {
      BigInteger number1 = number0.abs();
      int number2 = number1.bitLength() - 1;
      if (number2 < 63) {
         return number0.longValue();
      }

      if (number2 > 1023) {
         return number0.signum() * (Double.POSITIVE_INFINITY);
      }

      int number3 = number2 - 52 - 1;
      long number4 = number1.shiftRight(number3).longValue();
      long number6 = number4 >> 1;
      number6 &= 4503599627370495L;
      boolean flag8 = (number4 & 1L) != 0L && ((number6 & 1L) != 0L || number1.getLowestSetBit() < number3);
      long number9 = flag8 ? number6 + 1L : number6;
      long number11 = (long)(number2 + 1023) << 52;
      number11 += number9;
      number11 |= number0.signum() & Long.MIN_VALUE;
      return Double.longBitsToDouble(number11);
   }

   static double ensureNonNegative(double value0) {
      Preconditions.checkArgument(!Double.isNaN(value0));
      return Math.max(value0, 0.0);
   }
}
