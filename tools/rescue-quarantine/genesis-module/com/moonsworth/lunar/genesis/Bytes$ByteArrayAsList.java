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
class Bytes$ByteArrayAsList extends AbstractList<Byte> implements Serializable, RandomAccess {
   final byte[] field1;
   final int field2;
   final int field3;
   private static final long field4 = 0L;

   Bytes$ByteArrayAsList(byte[] items1) {
      this(items1, 0, items1.length);
   }

   Bytes$ByteArrayAsList(byte[] items1, int number2, int number3) {
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

   public Byte get(int index1) {
      Preconditions.checkElementIndex(index1, this.size());
      return this.field1[this.field2 + index1];
   }

   @Override
   public boolean contains(Object obj1) {
      return obj1 instanceof Byte && MixinHelper4_5.access$000(this.field1, (Byte)obj1, this.field2, this.field3) != -1;
   }

   @Override
   public int indexOf(Object obj1) {
      if (obj1 instanceof Byte) {
         int number2 = MixinHelper4_5.access$000(this.field1, (Byte)obj1, this.field2, this.field3);
         if (number2 >= 0) {
            return number2 - this.field2;
         }
      }

      return -1;
   }

   @Override
   public int lastIndexOf(Object obj1) {
      if (obj1 instanceof Byte) {
         int number2 = MixinHelper4_5.access$100(this.field1, (Byte)obj1, this.field2, this.field3);
         if (number2 >= 0) {
            return number2 - this.field2;
         }
      }

      return -1;
   }

   public Byte set(int index1, Byte number2) {
      Preconditions.checkElementIndex(index1, this.size());
      byte number3 = this.field1[this.field2 + index1];
      this.field1[this.field2 + index1] = (Byte)Preconditions.checkNotNull(number2);
      return number3;
   }

   @Override
   public List<Byte> subList(int number1, int number2) {
      int number3 = this.size();
      Preconditions.checkPositionIndexes(number1, number2, number3);
      return number1 == number2 ? Collections.emptyList() : new Bytes$ByteArrayAsList(this.field1, this.field2 + number1, this.field2 + number2);
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 == this) {
         return true;
      }

      if (obj1 instanceof Bytes$ByteArrayAsList) {
         Bytes$ByteArrayAsList mixinhelper4$data142 = (Bytes$ByteArrayAsList)obj1;
         int number3 = this.size();
         if (mixinhelper4$data142.size() != number3) {
            return false;
         }

         for (int index4 = 0; index4 < number3; index4++) {
            if (this.field1[this.field2 + index4] != mixinhelper4$data142.field1[mixinhelper4$data142.field2 + index4]) {
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
         number1 = 31 * number1 + MixinHelper4_5.hashCode(this.field1[index2]);
      }

      return number1;
   }

   @Override
   public String toString() {
      StringBuilder builder1 = new StringBuilder(this.size() * 5);
      builder1.append('[').append(this.field1[this.field2]);

      for (int index2 = this.field2 + 1; index2 < this.field3; index2++) {
         builder1.append(", ").append(this.field1[index2]);
      }

      return builder1.append(']').toString();
   }

   byte[] toByteArray() {
      return Arrays.copyOfRange(this.field1, this.field2, this.field3);
   }
}
