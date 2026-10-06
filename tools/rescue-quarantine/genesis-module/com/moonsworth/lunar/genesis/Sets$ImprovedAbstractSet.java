package com.moonsworth.lunar.genesis;

import java.util.AbstractSet;
import java.util.Collection;
import com.google.common.base.Preconditions;

abstract class Sets$ImprovedAbstractSet<E> extends AbstractSet<E> {
   Sets$ImprovedAbstractSet() {
   }

   @Override
   public boolean removeAll(Collection<?> list1) {
      return MixinHelper10_2.removeAllImpl(this, list1);
   }

   @Override
   public boolean retainAll(Collection<?> list1) {
      return super.retainAll((Collection<?>)Preconditions.checkNotNull(list1));
   }
}
