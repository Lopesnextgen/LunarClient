package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import java.util.Spliterators;
import java.util.Spliterator.OfLong;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;

@GwtCompatible
class MixinHelper5$Data14 extends AbstractList<Long> implements Serializable, RandomAccess {
   final long[] field1;
   final int field2;
   final int field3;
   private static final long field4 = 0L;

   MixinHelper5$Data14(long[] var1) {
      this(var1, 0, var1.length);
   }

   MixinHelper5$Data14(long[] var1, int var2, int var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
   }

   @Override
   public int size() {
      return this.field3 - this.field2;
   }

   @Override
   public boolean isEmpty() {
      return false;
   }

   public Long get(int var1) {
      Preconditions.checkElementIndex(var1, this.size());
      return this.field1[this.field2 + var1];
   }

   public OfLong spliterator() {
      return Spliterators.spliterator(this.field1, this.field2, this.field3, 0);
   }

   @Override
   public boolean contains(Object var1) {
      return var1 instanceof Long && Longs.access$000(this.field1, (Long)var1, this.field2, this.field3) != -1;
   }

   @Override
   public int indexOf(Object var1) {
      if (var1 instanceof Long) {
         int var2 = Longs.access$000(this.field1, (Long)var1, this.field2, this.field3);
         if (var2 >= 0) {
            return var2 - this.field2;
         }
      }

      return -1;
   }

   @Override
   public int lastIndexOf(Object var1) {
      if (var1 instanceof Long) {
         int var2 = Longs.access$100(this.field1, (Long)var1, this.field2, this.field3);
         if (var2 >= 0) {
            return var2 - this.field2;
         }
      }

      return -1;
   }

   public Long set(int var1, Long var2) {
      Preconditions.checkElementIndex(var1, this.size());
      long var3 = this.field1[this.field2 + var1];
      this.field1[this.field2 + var1] = Preconditions.checkNotNull(var2);
      return var3;
   }

   @Override
   public List<Long> subList(int var1, int var2) {
      int var3 = this.size();
      Preconditions.checkPositionIndexes(var1, var2, var3);
      return var1 == var2 ? Collections.emptyList() : new MixinHelper5$Data14(this.field1, this.field2 + var1, this.field2 + var2);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 == this) {
         return true;
      }

      if (var1 instanceof MixinHelper5$Data14) {
         MixinHelper5$Data14 var2 = (MixinHelper5$Data14)var1;
         int var3 = this.size();
         if (var2.size() != var3) {
            return false;
         }

         for (int var4 = 0; var4 < var3; var4++) {
            if (this.field1[this.field2 + var4] != var2.field1[var2.field2 + var4]) {
               return false;
            }
         }

         return true;
      } else {
         return super.equals(var1);
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;

      for (int var2 = this.field2; var2 < this.field3; var2++) {
         var1 = 31 * var1 + Longs.hashCode(this.field1[var2]);
      }

      return var1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder(this.size() * 10);
      var1.append('[').append(this.field1[this.field2]);

      for (int var2 = this.field2 + 1; var2 < this.field3; var2++) {
         var1.append(", ").append(this.field1[var2]);
      }

      return var1.append(']').toString();
   }

   long[] toLongArray() {
      return Arrays.copyOfRange(this.field1, this.field2, this.field3);
   }
}
