package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
abstract class MixinHelperIterator2<F, T> implements Iterator<T> {
   final Iterator<? extends F> field1;

   MixinHelperIterator2(Iterator<? extends F> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   abstract T transform(F var1);

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
