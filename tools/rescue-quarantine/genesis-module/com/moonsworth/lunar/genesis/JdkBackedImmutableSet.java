package com.moonsworth.lunar.genesis;

import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;

@GwtCompatible(serializable = true)
final class JdkBackedImmutableSet<E> extends IndexedImmutableSet<E> {
   private final Set<?> field10;
   private final ImmutableList<E> field11;

   JdkBackedImmutableSet(Set<?> set1, ImmutableList<E> abstractcollectioniterator32) {
      this.field10 = set1;
      this.field11 = abstractcollectioniterator32;
   }

   @Override
   E get(int index1) {
      return (E)this.field11.get(index1);
   }

   public boolean contains(@Nullable Object obj1) {
      return this.field10.contains(obj1);
   }

   boolean isPartialView() {
      return false;
   }

   public int size() {
      return this.field11.size();
   }
}
