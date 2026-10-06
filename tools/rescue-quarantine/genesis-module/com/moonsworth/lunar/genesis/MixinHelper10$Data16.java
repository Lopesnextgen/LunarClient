package com.moonsworth.lunar.genesis;

import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Sets;

class MixinHelper10$Data16<E> extends MixinHelper39.Data2<E> implements Set<E> {
   MixinHelper10$Data16(Set<E> var1, PredicateExtension<? super E> var2) {
      super(var1, var2);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      return Sets.equalsImpl(this, var1);
   }

   @Override
   public int hashCode() {
      return Sets.hashCodeImpl(this);
   }
}
