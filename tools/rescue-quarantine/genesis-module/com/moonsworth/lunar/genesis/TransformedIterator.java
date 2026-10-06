package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
abstract class TransformedIterator<F, T> implements Iterator<T> {
   final Iterator<? extends F> field1;

   TransformedIterator(Iterator<? extends F> iterator1) {
      this.field1 = (Iterator<? extends F>)Preconditions.checkNotNull(iterator1);
   }

   abstract T transform(F f1);

   @Override
   public final boolean hasNext() {
      return this.field1.hasNext();
   }

   @Override
   public final T next() {
      return this.transform((F)this.field1.next());
   }

   @Override
   public final void remove() {
      this.field1.remove();
   }
}
