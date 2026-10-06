package com.moonsworth.lunar.genesis;

import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
final class TopKSelector<T> {
   private final int field1;
   private final Comparator<? super T> field2;
   private final T[] field3;
   private int bufferSize;
   private @Nullable T threshold;

   public static <T extends Comparable<? super T>> TopKSelector<T> method1(int number0) {
      return method2(number0, com.google.common.collect.Ordering.method1());
   }

   public static <T> TopKSelector<T> method2(int number0, Comparator<? super T> comparator1) {
      return new TopKSelector<>(comparator1, number0);
   }

   public static <T extends Comparable<? super T>> TopKSelector<T> method3(int number0) {
      return method4(number0, com.google.common.collect.Ordering.method1());
   }

   public static <T> TopKSelector<T> method4(int number0, Comparator<? super T> comparator1) {
      return new TopKSelector<>(com.google.common.collect.Ordering.method2(comparator1).method9(), number0);
   }

   private TopKSelector(Comparator<? super T> comparator1, int number2) {
      this.field2 = (Comparator<? super T>)Preconditions.checkNotNull(comparator1, "comparator");
      this.field1 = number2;
      Preconditions.checkArgument(number2 >= 0, "k (%s) must be >= 0", number2);
      Preconditions.checkArgument(number2 <= 1073741823, "k (%s) must be <= Integer.MAX_VALUE / 2", number2);
      this.field3 = (T[])(new Object[MixinHelper7_3.checkedMultiply(number2, 2)]);
      this.bufferSize = 0;
      this.threshold = null;
   }

   public void offer(@Nullable T value1) {
      if (this.field1 != 0) {
         if (this.bufferSize == 0) {
            this.field3[0] = (T)value1;
            this.threshold = (T)value1;
            this.bufferSize = 1;
         } else if (this.bufferSize < this.field1) {
            this.field3[this.bufferSize++] = (T)value1;
            if (this.field2.compare((T)value1, this.threshold) > 0) {
               this.threshold = (T)value1;
            }
         } else if (this.field2.compare((T)value1, this.threshold) < 0) {
            this.field3[this.bufferSize++] = (T)value1;
            if (this.bufferSize == 2 * this.field1) {
               this.trim();
            }
         }
      }
   }

   private void trim() {
      int number1 = 0;
      int number2 = 2 * this.field1 - 1;
      int index3 = 0;
      int number4 = 0;
      int number5 = MixinHelper7_3.log2(number2 - number1, RoundingMode.CEILING) * 3;

      while (number1 < number2) {
         int number6 = number1 + number2 + 1 >>> 1;
         int number7 = this.partition(number1, number2, number6);
         if (number7 > this.field1) {
            number2 = number7 - 1;
         } else {
            if (number7 >= this.field1) {
               break;
            }

            number1 = Math.max(number7, number1 + 1);
            index3 = number7;
         }

         if (++number4 >= number5) {
            Arrays.sort(this.field3, number1, number2, this.field2);
            break;
         }
      }

      this.bufferSize = this.field1;
      this.threshold = this.field3[index3];

      for (int index8 = index3 + 1; index8 < this.field1; index8++) {
         if (this.field2.compare(this.field3[index8], this.threshold) > 0) {
            this.threshold = this.field3[index8];
         }
      }
   }

   private int partition(int number1, int index2, int index3) {
      Object obj4 = this.field3[index3];
      this.field3[index3] = this.field3[index2];
      int index5 = number1;

      for (int index6 = number1; index6 < index2; index6++) {
         if (this.field2.compare(this.field3[index6], (T)obj4) < 0) {
            this.swap(index5, index6);
            index5++;
         }
      }

      this.field3[index2] = this.field3[index5];
      this.field3[index5] = (T)obj4;
      return index5;
   }

   private void swap(int index1, int index2) {
      Object obj3 = this.field3[index1];
      this.field3[index1] = this.field3[index2];
      this.field3[index2] = (T)obj3;
   }

   TopKSelector<T> method5(TopKSelector<T> mixinhelper28_21) {
      for (int index2 = 0; index2 < mixinhelper28_21.bufferSize; index2++) {
         this.offer(mixinhelper28_21.field3[index2]);
      }

      return this;
   }

   public void offerAll(Iterable<? extends T> list1) {
      this.offerAll(list1.iterator());
   }

   public void offerAll(Iterator<? extends T> iterator1) {
      while (iterator1.hasNext()) {
         this.offer((T)iterator1.next());
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
