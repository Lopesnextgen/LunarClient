package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

class Lists$TwoPlusArrayList<E> extends AbstractList<E> implements Serializable, RandomAccess {
   final @Nullable E field1;
   final @Nullable E field2;
   final E[] field3;
   private static final long field4 = 0L;

   Lists$TwoPlusArrayList(@Nullable E value1, @Nullable E value2, E[] items3) {
      this.field1 = (E)value1;
      this.field2 = (E)value2;
      this.field3 = (E[])((Object[])Preconditions.checkNotNull(items3));
   }

   @Override
   public int size() {
      return MixinHelper7_3.saturatedAdd(this.field3.length, 2);
   }

   @Override
   public E get(int index1) {
      switch (index1) {
         case 0:
            return this.field1;
         case 1:
            return this.field2;
         default:
            Preconditions.checkElementIndex(index1, this.size());
            return this.field3[index1 - 2];
      }
   }
}
