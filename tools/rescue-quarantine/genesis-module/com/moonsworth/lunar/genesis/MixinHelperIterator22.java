package com.moonsworth.lunar.genesis;

import java.util.ListIterator;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Iterators;

@GwtCompatible
abstract class MixinHelperIterator22<F, T> extends MixinHelperIterator2<F, T> implements ListIterator<T> {
   MixinHelperIterator22(ListIterator<? extends F> var1) {
      super(var1);
   }

   private ListIterator<? extends F> backingIterator() {
      return Iterators.cast(this.field1);
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
   public void set(T var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void add(T var1) {
      throw new UnsupportedOperationException();
   }
}
