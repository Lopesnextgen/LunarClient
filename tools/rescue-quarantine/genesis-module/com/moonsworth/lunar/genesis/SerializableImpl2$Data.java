package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CheckReturnValue;
import java.util.Collection;
import java.util.Spliterator.OfInt;
import java.util.stream.IntStream;

@CanIgnoreReturnValue
public final class SerializableImpl2$Data {
   private int[] array;
   private int count = 0;

   SerializableImpl2$Data(int var1) {
      this.array = new int[var1];
   }

   public SerializableImpl2$Data method1(int var1) {
      this.ensureRoomFor(1);
      this.array[this.count] = var1;
      this.count++;
      return this;
   }

   public SerializableImpl2$Data method2(int[] var1) {
      this.ensureRoomFor(var1.length);
      System.arraycopy(var1, 0, this.array, this.count, var1.length);
      this.count += var1.length;
      return this;
   }

   public SerializableImpl2$Data method3(Iterable<Integer> var1) {
      if (var1 instanceof Collection) {
         return this.method4((Collection<Integer>)var1);
      }

      for (Integer var3 : var1) {
         this.method1(var3);
      }

      return this;
   }

   public SerializableImpl2$Data method4(Collection<Integer> var1) {
      this.ensureRoomFor(var1.size());

      for (Integer var3 : var1) {
         this.array[this.count++] = var3;
      }

      return this;
   }

   public SerializableImpl2$Data method5(IntStream var1) {
      OfInt var2 = var1.spliterator();
      long var3 = var2.getExactSizeIfKnown();
      if (var3 > 0L) {
         this.ensureRoomFor(MixinHelper122.saturatedCast(var3));
      }

      var2.forEachRemaining(this::method1);
      return this;
   }

   public SerializableImpl2$Data method6(SerializableImpl2_2 var1) {
      this.ensureRoomFor(var1.length());
      System.arraycopy(SerializableImpl2_2.method17(var1), SerializableImpl2_2.method18(var1), this.array, this.count, var1.length());
      this.count = this.count + var1.length();
      return this;
   }

   private void ensureRoomFor(int var1) {
      int var2 = this.count + var1;
      if (var2 > this.array.length) {
         int[] var3 = new int[expandedCapacity(this.array.length, var2)];
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
   public SerializableImpl2_2 method7() {
      return this.count == 0 ? SerializableImpl2_2.method19() : new SerializableImpl2_2(this.array, 0, this.count);
   }
}
