package com.moonsworth.lunar.genesis;

import java.math.BigInteger;
import com.google.common.base.Preconditions;

@Annotation3
final class MixinHelper5_11 {
   static final long field1 = 4503599627370495L;
   static final long field2 = 9218868437227405312L;
   static final long field3 = Long.MIN_VALUE;
   static final int field4 = 52;
   static final int field5 = 1023;
   static final long field6 = 4503599627370496L;
   @Annotation4
   static final long field7 = 4607182418800017408L;

   private MixinHelper5_11() {
   }

   static double nextDown(double var0) {
      return -Math.nextUp(-var0);
   }

   static long getSignificand(double var0) {
      Preconditions.checkArgument(isFinite(var0), "not a normal value");
      int var2 = Math.getExponent(var0);
      long var3 = Double.doubleToRawLongBits(var0);
      var3 &= 4503599627370495L;
      return var2 == -1023 ? var3 << 1 : var3 | 4503599627370496L;
   }

   static boolean isFinite(double var0) {
      return Math.getExponent(var0) <= 1023;
   }

   static boolean isNormal(double var0) {
      return Math.getExponent(var0) >= -1022;
   }

   static double scaleNormalize(double var0) {
      long var2 = Double.doubleToRawLongBits(var0) & 4503599627370495L;
      return Double.longBitsToDouble(var2 | 4607182418800017408L);
   }

   static double bigToDouble(BigInteger var0) {
      BigInteger var1 = var0.abs();
      int var2 = var1.bitLength() - 1;
      if (var2 < 63) {
         return var0.longValue();
      }

      if (var2 > 1023) {
         return var0.signum() * (Double.POSITIVE_INFINITY);
      }

      int var3 = var2 - 52 - 1;
      long var4 = var1.shiftRight(var3).longValue();
      long var6 = var4 >> 1;
      var6 &= 4503599627370495L;
      boolean var8 = (var4 & 1L) != 0L && ((var6 & 1L) != 0L || var1.getLowestSetBit() < var3);
      long var9 = var8 ? var6 + 1L : var6;
      long var11 = (long)(var2 + 1023) << 52;
      var11 += var9;
      var11 |= var0.signum() & Long.MIN_VALUE;
      return Double.longBitsToDouble(var11);
   }

   static double ensureNonNegative(double var0) {
      Preconditions.checkArgument(!Double.isNaN(var0));
      return Math.max(var0, 0.0);
   }
}
