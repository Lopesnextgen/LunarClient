package com.moonsworth.lunar.genesis;

import java.util.AbstractSet;
import java.util.Iterator;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableMap;

final class Sets$SubSet<E> extends AbstractSet<E> {
   private final ImmutableMap<E, Integer> field1;
   private final int field2;

   Sets$SubSet(ImmutableMap<E, Integer> serializableiterator1, int number2) {
      this.field1 = serializableiterator1;
      this.field2 = number2;
   }

   @Override
   public Iterator<E> iterator() {
      return new Data6$1(this);
   }

   @Override
   public int size() {
      return Integer.bitCount(this.field2);
   }

   @Override
   public boolean contains(@Nullable Object obj1) {
      Integer number2 = (Integer)this.field1.get(obj1);
      return number2 != null && (this.field2 & 1 << number2) != 0;
   }
}
