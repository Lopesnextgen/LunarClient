package com.moonsworth.lunar.genesis;

import java.util.ListIterator;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Iterators;

@GwtCompatible
abstract class TransformedListIterator<F, T> extends TransformedIterator<F, T> implements ListIterator<T> {
   TransformedListIterator(ListIterator<? extends F> iterator1) {
      super(iterator1);
   }

   private ListIterator<? extends F> backingIterator() {
      return Iterators.cast(this.OOCIHOOIIROCOHICRHIIIICHHHORCR);
   }

   @Override
   public final boolean hasPrevious() {
      return this.backingIterator().hasPrevious();
   }

   @Override
   public final T previous() {
      return this.transform((F)this.backingIterator().previous());
   }

   @Override
   public final int nextIndex() {
      return this.backingIterator().nextIndex();
   }

   @Override
   public final int previousIndex() {
      return this.backingIterator().previousIndex();
   }

   @Override
   public void set(T value1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void add(T value1) {
      throw new UnsupportedOperationException();
   }
}
