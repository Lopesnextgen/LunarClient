package com.moonsworth.lunar.genesis;

import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
final class MixinHelper28_2<T> {
   private final int field1;
   private final java.util.Comparator<? super T> field2;
   private final T[] field3;
   private int bufferSize;
   private @Nullable T threshold;

   public static <T extends Comparable<? super T>> MixinHelper28_2<T> method1(int var0) {
      return method2(var0, Ordering.method1());
   }

   public static <T> MixinHelper28_2<T> method2(int var0, java.util.Comparator<? super T> var1) {
      return new MixinHelper28_2<>(var1, var0);
   }

   public static <T extends Comparable<? super T>> MixinHelper28_2<T> method3(int var0) {
      return method4(var0, Ordering.method1());
   }

   public static <T> MixinHelper28_2<T> method4(int var0, java.util.Comparator<? super T> var1) {
      return new MixinHelper28_2<>(Ordering.method2(var1).method9(), var0);
   }

   private MixinHelper28_2(java.util.Comparator<? super T> var1, int var2) {
      this.field2 = Preconditions.checkNotNull(var1, "comparator");
      this.field1 = var2;
      Preconditions.checkArgument(var2 >= 0, "k (%s) must be >= 0", var2);
      Preconditions.checkArgument(var2 <= 1073741823, "k (%s) must be <= Integer.MAX_VALUE / 2", var2);
      this.field3 = (T[])(new Object[MixinHelper7_3.checkedMultiply(var2, 2)]);
      this.bufferSize = 0;
      this.threshold = null;
   }

   public void offer(@Nullable T var1) {
      if (this.field1 != 0) {
         if (this.bufferSize == 0) {
            this.field3[0] = (T)var1;
            this.threshold = (T)var1;
            this.bufferSize = 1;
         } else if (this.bufferSize < this.field1) {
            this.field3[this.bufferSize++] = (T)var1;
            if (this.field2.compare((T)var1, this.threshold) > 0) {
               this.threshold = (T)var1;
            }
         } else if (this.field2.compare((T)var1, this.threshold) < 0) {
            this.field3[this.bufferSize++] = (T)var1;
            if (this.bufferSize == 2 * this.field1) {
               this.trim();
            }
         }
      }
   }

   private void trim() {
      int var1 = 0;
      int var2 = 2 * this.field1 - 1;
      int var3 = 0;
      int var4 = 0;
      int var5 = MixinHelper7_3.log2(var2 - var1, RoundingMode.CEILING) * 3;

      while (var1 < var2) {
         int var6 = var1 + var2 + 1 >>> 1;
         int var7 = this.partition(var1, var2, var6);
         if (var7 > this.field1) {
            var2 = var7 - 1;
         } else {
            if (var7 >= this.field1) {
               break;
            }

            var1 = Math.max(var7, var1 + 1);
            var3 = var7;
         }

         if (++var4 >= var5) {
            Arrays.sort(this.field3, var1, var2, this.field2);
            break;
         }
      }

      this.bufferSize = this.field1;
      this.threshold = this.field3[var3];

      for (int var8 = var3 + 1; var8 < this.field1; var8++) {
         if (this.field2.compare(this.field3[var8], this.threshold) > 0) {
            this.threshold = this.field3[var8];
         }
      }
   }

   private int partition(int var1, int var2, int var3) {
      Object var4 = this.field3[var3];
      this.field3[var3] = this.field3[var2];
      int var5 = var1;

      for (int var6 = var1; var6 < var2; var6++) {
         if (this.field2.compare(this.field3[var6], (T)var4) < 0) {
            this.swap(var5, var6);
            var5++;
         }
      }

      this.field3[var2] = this.field3[var5];
      this.field3[var5] = (T)var4;
      return var5;
   }

   private void swap(int var1, int var2) {
      Object var3 = this.field3[var1];
      this.field3[var1] = this.field3[var2];
      this.field3[var2] = (T)var3;
   }

   MixinHelper28_2<T> method5(MixinHelper28_2<T> var1) {
      for (int var2 = 0; var2 < var1.bufferSize; var2++) {
         this.offer(var1.field3[var2]);
      }

      return this;
   }

   public void offerAll(Iterable<? extends T> var1) {
      this.offerAll(var1.iterator());
   }

   public void offerAll(Iterator<? extends T> var1) {
      while (var1.hasNext()) {
         this.offer((T)var1.next());
      }
   }

   public List<T> topK() {
      Arrays.sort(this.field3, 0, this.bufferSize, this.field2);
      if (this.bufferSize > this.field1) {
         Arrays.fill(this.field3, this.field1, this.field3.length, null);
         this.bufferSize = this.field1;
         this.threshold = this.field3[this.field1 - 1];
      }

      return Collections.unmodifiableList(Arrays.asList((T[])Arrays.copyOf(this.field3, this.bufferSize)));
   }
}
