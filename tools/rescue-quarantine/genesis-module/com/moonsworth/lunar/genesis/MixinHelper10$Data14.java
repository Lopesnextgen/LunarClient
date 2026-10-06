package com.moonsworth.lunar.genesis;

import java.util.AbstractSet;
import java.util.Collection;
import com.google.common.collect.Sets;
import com.google.common.base.Preconditions;

abstract class MixinHelper10$Data14<E> extends AbstractSet<E> {
   @Override
   public boolean removeAll(Collection<?> var1) {
      return Sets.removeAllImpl(this, var1);
   }

   @Override
   public boolean retainAll(Collection<?> var1) {
      return super.retainAll(Preconditions.checkNotNull(var1));
   }
}
