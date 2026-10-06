package com.moonsworth.lunar.genesis;

import java.math.RoundingMode;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLongArray;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.math.LongMath;
import com.google.common.base.Preconditions;

final class MixinHelperType$Data2 {
   private static final int field1 = 6;
   final AtomicLongArray field2;
   private final MixinHelper_12 field3;

   MixinHelperType$Data2(long var1) {
      Preconditions.checkArgument(var1 > 0L, "data length is zero!");
      this.field2 = new AtomicLongArray(MixinHelper122.checkedCast(LongMath.divide(var1, 64L, RoundingMode.CEILING)));
      this.field3 = MixinHelper6_11.method1();
   }

   MixinHelperType$Data2(long[] var1) {
      Preconditions.checkArgument(var1.length > 0, "data length is zero!");
      this.field2 = new AtomicLongArray(var1);
      this.field3 = MixinHelper6_11.method1();
      long var2 = 0L;

      for (long var7 : var1) {
         var2 += Long.bitCount(var7);
      }

      this.field3.add(var2);
   }

   boolean set(long var1) {
      if (this.get(var1)) {
         return false;
      }

      int var3 = (int)(var1 >>> 6);
      long var4 = 1L << (int)var1;

      long var6;
      long var8;
      do {
         var6 = this.field2.get(var3);
         var8 = var6 | var4;
         if (var6 == var8) {
            return false;
         }
      } while (!this.field2.compareAndSet(var3, var6, var8));

      this.field3.increment();
      return true;
   }

   boolean get(long var1) {
      return (this.field2.get((int)(var1 >>> 6)) & 1L << (int)var1) != 0L;
   }

   public static long[] toPlainArray(AtomicLongArray var0) {
      long[] var1 = new long[var0.length()];

      for (int var2 = 0; var2 < var1.length; var2++) {
         var1[var2] = var0.get(var2);
      }

      return var1;
   }

   long bitSize() {
      return this.field2.length() * 64L;
   }

   long bitCount() {
      return this.field3.sum();
   }

   MixinHelperType$Data2 method1() {
      return new MixinHelperType$Data2(toPlainArray(this.field2));
   }

   void method2(MixinHelperType$Data2 var1) {
      Preconditions.checkArgument(
         this.field2.length() == var1.field2.length(), "BitArrays must be of equal length (%s != %s)", this.field2.length(), var1.field2.length()
      );

      for (int var2 = 0; var2 < this.field2.length(); var2++) {
         long var3 = var1.field2.get(var2);
         boolean var9 = true;

         long var5;
         long var7;
         do {
            var5 = this.field2.get(var2);
            var7 = var5 | var3;
            if (var5 == var7) {
               var9 = false;
               break;
            }
         } while (!this.field2.compareAndSet(var2, var5, var7));

         if (var9) {
            int var10 = Long.bitCount(var7) - Long.bitCount(var5);
            this.field3.add(var10);
         }
      }
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof MixinHelperType$Data2) {
         MixinHelperType$Data2 var2 = (MixinHelperType$Data2)var1;
         return Arrays.equals(toPlainArray(this.field2), toPlainArray(var2.field2));
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Arrays.hashCode(toPlainArray(this.field2));
   }
}
