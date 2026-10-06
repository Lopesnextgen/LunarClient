package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
class Shorts$ShortArrayAsList extends AbstractList<Short> implements Serializable, RandomAccess {
   final short[] field1;
   final int field2;
   final int field3;
   private static final long field4 = 0L;

   Shorts$ShortArrayAsList(short[] items1) {
      this(items1, 0, items1.length);
   }

   Shorts$ShortArrayAsList(short[] items1, int number2, int number3) {
      this.field1 = items1;
      this.field2 = number2;
      this.field3 = number3;
   }

   @Override
   public int size() {
      return this.field3 - this.field2;
   }

   @Override
   public boolean isEmpty() {
      return false;
   }

   public Short get(int index1) {
      Preconditions.checkElementIndex(index1, this.size());
      return this.field1[this.field2 + index1];
   }

   @Override
   public boolean contains(@Nullable Object obj1) {
      return obj1 instanceof Short && MixinHelper112.access$000(this.field1, (Short)obj1, this.field2, this.field3) != -1;
   }

   @Override
   public int indexOf(@Nullable Object obj1) {
      if (obj1 instanceof Short) {
         int number2 = MixinHelper112.access$000(this.field1, (Short)obj1, this.field2, this.field3);
         if (number2 >= 0) {
            return number2 - this.field2;
         }
      }

      return -1;
   }

   @Override
   public int lastIndexOf(@Nullable Object obj1) {
      if (obj1 instanceof Short) {
         int number2 = MixinHelper112.access$100(this.field1, (Short)obj1, this.field2, this.field3);
         if (number2 >= 0) {
            return number2 - this.field2;
         }
      }

      return -1;
   }

   public Short set(int index1, Short number2) {
      Preconditions.checkElementIndex(index1, this.size());
      short number3 = this.field1[this.field2 + index1];
      this.field1[this.field2 + index1] = (Short)Preconditions.checkNotNull(number2);
      return number3;
   }

   @Override
   public List<Short> subList(int number1, int number2) {
      int number3 = this.size();
      Preconditions.checkPositionIndexes(number1, number2, number3);
      return number1 == number2 ? Collections.emptyList() : new Shorts$ShortArrayAsList(this.field1, this.field2 + number1, this.field2 + number2);
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 == this) {
         return true;
      }

      if (obj1 instanceof Shorts$ShortArrayAsList) {
         Shorts$ShortArrayAsList mixinhelper112$data2 = (Shorts$ShortArrayAsList)obj1;
         int number3 = this.size();
         if (mixinhelper112$data2.size() != number3) {
            return false;
         }

         for (int index4 = 0; index4 < number3; index4++) {
            if (this.field1[this.field2 + index4] != mixinhelper112$data2.field1[mixinhelper112$data2.field2 + index4]) {
               return false;
            }
         }

         return true;
      } else {
         return super.equals(obj1);
      }
   }

   @Override
   public int hashCode() {
      int number1 = 1;

      for (int index2 = this.field2; index2 < this.field3; index2++) {
         number1 = 31 * number1 + MixinHelper112.hashCode(this.field1[index2]);
      }

      return number1;
   }

   @Override
   public String toString() {
      StringBuilder builder1 = new StringBuilder(this.size() * 6);
      builder1.append('[').append(this.field1[this.field2]);

      for (int index2 = this.field2 + 1; index2 < this.field3; index2++) {
         builder1.append(", ").append(this.field1[index2]);
      }

      return builder1.append(']').toString();
   }

   short[] toShortArray() {
      return Arrays.copyOfRange(this.field1, this.field2, this.field3);
   }
}
