package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CheckReturnValue;
import java.util.Collection;
import java.util.Spliterator.OfLong;
import java.util.stream.LongStream;

@CanIgnoreReturnValue
public final class SerializableImpl$Data12 {
   private long[] array;
   private int count = 0;

   SerializableImpl$Data12(int var1) {
      this.array = new long[var1];
   }

   public SerializableImpl$Data12 method1(long var1) {
      this.ensureRoomFor(1);
      this.array[this.count] = var1;
      this.count++;
      return this;
   }

   public SerializableImpl$Data12 method2(long[] var1) {
      this.ensureRoomFor(var1.length);
      System.arraycopy(var1, 0, this.array, this.count, var1.length);
      this.count += var1.length;
      return this;
   }

   public SerializableImpl$Data12 method3(Iterable<Long> var1) {
      if (var1 instanceof Collection) {
         return this.method4((Collection<Long>)var1);
      }

      for (Long var3 : var1) {
         this.method1(var3);
      }

      return this;
   }

   public SerializableImpl$Data12 method4(Collection<Long> var1) {
      this.ensureRoomFor(var1.size());

      for (Long var3 : var1) {
         this.array[this.count++] = var3;
      }

      return this;
   }

   public SerializableImpl$Data12 method5(LongStream var1) {
      OfLong var2 = var1.spliterator();
      long var3 = var2.getExactSizeIfKnown();
      if (var3 > 0L) {
         this.ensureRoomFor(MixinHelper122.saturatedCast(var3));
      }

      var2.forEachRemaining(this::method1);
      return this;
   }

   public SerializableImpl$Data12 method6(SerializableImpl_3 var1) {
      this.ensureRoomFor(var1.length());
      System.arraycopy(SerializableImpl_3.method17(var1), SerializableImpl_3.method18(var1), this.array, this.count, var1.length());
      this.count = this.count + var1.length();
      return this;
   }

   private void ensureRoomFor(int var1) {
      int var2 = this.count + var1;
      if (var2 > this.array.length) {
         long[] var3 = new long[expandedCapacity(this.array.length, var2)];
         System.arraycopy(this.array, 0, var3, 0, this.count);
         this.array = var3;
      }
   }

   private static int expandedCapacity(int var0, int var1) {
      if (var1 < 0) {
         throw new AssertionError("cannot store more than MAX_VALUE elements");
      }

      int var2 = var0 + (var0 >> 1) + 1;
      if (var2 < var1) {
         var2 = Integer.highestOneBit(var1 - 1) << 1;
      }

      if (var2 < 0) {
         var2 = Integer.MAX_VALUE;
      }

      return var2;
   }

   @CheckReturnValue
   public SerializableImpl_3 method7() {
      return this.count == 0 ? SerializableImpl_3.method19() : new SerializableImpl_3(this.array, 0, this.count);
   }
}
