package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

class Lists$OnePlusArrayList<E> extends AbstractList<E> implements Serializable, RandomAccess {
   final @Nullable E field1;
   final E[] field2;
   private static final long field3 = 0L;

   Lists$OnePlusArrayList(@Nullable E value1, E[] items2) {
      this.field1 = (E)value1;
      this.field2 = (E[])((Object[])Preconditions.checkNotNull(items2));
   }

   @Override
   public int size() {
      return MixinHelper7_3.saturatedAdd(this.field2.length, 1);
   }

   @Override
   public E get(int index1) {
      Preconditions.checkElementIndex(index1, this.size());
      return index1 == 0 ? this.field1 : this.field2[index1 - 1];
   }
}
