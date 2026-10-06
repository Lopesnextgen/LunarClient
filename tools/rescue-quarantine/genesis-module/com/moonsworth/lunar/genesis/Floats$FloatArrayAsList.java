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
class Floats$FloatArrayAsList extends AbstractList<Float> implements Serializable, RandomAccess {
   final float[] field1;
   final int field2;
   final int field3;
   private static final long field4 = 0L;

   Floats$FloatArrayAsList(float[] items1) {
      this(items1, 0, items1.length);
   }

   Floats$FloatArrayAsList(float[] items1, int number2, int number3) {
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

   public Float get(int index1) {
      Preconditions.checkElementIndex(index1, this.size());
      return this.field1[this.field2 + index1];
   }

   @Override
   public boolean contains(Object obj1) {
      return obj1 instanceof Float && MixinHelper132.access$000(this.field1, (Float)obj1, this.field2, this.field3) != -1;
   }

   @Override
   public int indexOf(Object obj1) {
      if (obj1 instanceof Float) {
         int number2 = MixinHelper132.access$000(this.field1, (Float)obj1, this.field2, this.field3);
         if (number2 >= 0) {
            return number2 - this.field2;
         }
      }

      return -1;
   }

   @Override
   public int lastIndexOf(Object obj1) {
      if (obj1 instanceof Float) {
         int number2 = MixinHelper132.access$100(this.field1, (Float)obj1, this.field2, this.field3);
         if (number2 >= 0) {
            return number2 - this.field2;
         }
      }

      return -1;
   }

   public Float set(int index1, Float value2) {
      Preconditions.checkElementIndex(index1, this.size());
      float value3 = this.field1[this.field2 + index1];
      this.field1[this.field2 + index1] = (Float)Preconditions.checkNotNull(value2);
      return value3;
   }

   @Override
   public List<Float> subList(int number1, int number2) {
      int number3 = this.size();
      Preconditions.checkPositionIndexes(number1, number2, number3);
      return number1 == number2 ? Collections.emptyList() : new Floats$FloatArrayAsList(this.field1, this.field2 + number1, this.field2 + number2);
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 == this) {
         return true;
      }

      if (obj1 instanceof Floats$FloatArrayAsList) {
         Floats$FloatArrayAsList mixinhelper132$data22 = (Floats$FloatArrayAsList)obj1;
         int number3 = this.size();
         if (mixinhelper132$data22.size() != number3) {
            return false;
         }

         for (int index4 = 0; index4 < number3; index4++) {
            if (this.field1[this.field2 + index4] != mixinhelper132$data22.field1[mixinhelper132$data22.field2 + index4]) {
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
         number1 = 31 * number1 + MixinHelper132.hashCode(this.field1[index2]);
      }

      return number1;
   }

   @Override
   public String toString() {
      StringBuilder builder1 = new StringBuilder(this.size() * 12);
      builder1.append('[').append(this.field1[this.field2]);

      for (int index2 = this.field2 + 1; index2 < this.field3; index2++) {
         builder1.append(", ").append(this.field1[index2]);
      }

      return builder1.append(']').toString();
   }

   float[] toFloatArray() {
      return Arrays.copyOfRange(this.field1, this.field2, this.field3);
   }
}
