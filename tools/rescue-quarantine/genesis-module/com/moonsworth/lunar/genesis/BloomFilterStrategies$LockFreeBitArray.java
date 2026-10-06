package com.moonsworth.lunar.genesis;

import java.math.RoundingMode;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLongArray;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.math.LongMath;
import com.google.common.base.Preconditions;

final class BloomFilterStrategies$LockFreeBitArray {
   private static final int field1 = 6;
   final AtomicLongArray field2;
   private final HashLongAddable field3;

   BloomFilterStrategies$LockFreeBitArray(long number1) {
      Preconditions.checkArgument(number1 > 0L, "data length is zero!");
      this.field2 = new AtomicLongArray(MixinHelper122.checkedCast(LongMath.divide(number1, 64L, RoundingMode.CEILING)));
      this.field3 = MixinHelper6_11.method1();
   }

   BloomFilterStrategies$LockFreeBitArray(long[] items1) {
      Preconditions.checkArgument(items1.length > 0, "data length is zero!");
      this.field2 = new AtomicLongArray(items1);
      this.field3 = MixinHelper6_11.method1();
      long index2 = 0L;

      for (long index7 : items1) {
         index2 += Long.bitCount(index7);
      }

      this.field3.add(index2);
   }

   boolean set(long index1) {
      if (this.get(index1)) {
         return false;
      }

      int index3 = (int)(index1 >>> 6);
      long number4 = 1L << (int)index1;

      long number6;
      long number8;
      do {
         number6 = this.field2.get(index3);
         number8 = number6 | number4;
         if (number6 == number8) {
            return false;
         }
      } while (!this.field2.compareAndSet(index3, number6, number8));

      this.field3.increment();
      return true;
   }

   boolean get(long number1) {
      return (this.field2.get((int)(number1 >>> 6)) & 1L << (int)number1) != 0L;
   }

   public static long[] toPlainArray(AtomicLongArray atomiclongarray0) {
      long[] items1 = new long[atomiclongarray0.length()];

      for (int index2 = 0; index2 < items1.length; index2++) {
         items1[index2] = atomiclongarray0.get(index2);
      }

      return items1;
   }

   long bitSize() {
      return this.field2.length() * 64L;
   }

   long bitCount() {
      return this.field3.sum();
   }

   BloomFilterStrategies$LockFreeBitArray method1() {
      return new BloomFilterStrategies$LockFreeBitArray(toPlainArray(this.field2));
   }

   void method2(BloomFilterStrategies$LockFreeBitArray mixinhelpertype$data21) {
      Preconditions.checkArgument(
         this.field2.length() == mixinhelpertype$data21.field2.length(), "BitArrays must be of equal length (%s != %s)", this.field2.length(), mixinhelpertype$data21.field2.length()
      );

      for (int index2 = 0; index2 < this.field2.length(); index2++) {
         long number3 = mixinhelpertype$data21.field2.get(index2);
         boolean flag9 = true;

         long number5;
         long number7;
         do {
            number5 = this.field2.get(index2);
            number7 = number5 | number3;
            if (number5 == number7) {
               flag9 = false;
               break;
            }
         } while (!this.field2.compareAndSet(index2, number5, number7));

         if (flag9) {
            int index10 = Long.bitCount(number7) - Long.bitCount(number5);
            this.field3.add(index10);
         }
      }
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof BloomFilterStrategies$LockFreeBitArray) {
         BloomFilterStrategies$LockFreeBitArray mixinhelpertype$data22 = (BloomFilterStrategies$LockFreeBitArray)obj1;
         return Arrays.equals(toPlainArray(this.field2), toPlainArray(mixinhelpertype$data22.field2));
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Arrays.hashCode(toPlainArray(this.field2));
   }
}
