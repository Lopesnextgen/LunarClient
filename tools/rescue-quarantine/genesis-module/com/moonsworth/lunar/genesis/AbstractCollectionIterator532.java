package com.moonsworth.lunar.genesis;

import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;

@GwtCompatible(serializable = true)
final class AbstractCollectionIterator532<E> extends AbstractCollectionIterator53<E> {
   private final Set<?> field10;
   private final ImmutableList<E> field11;

   AbstractCollectionIterator532(Set<?> var1, ImmutableList<E> var2) {
      this.field10 = var1;
      this.field11 = var2;
   }

   @Override
   E get(int var1) {
      return this.field11.get(var1);
   }

   @Override
   public boolean contains(@Nullable Object var1) {
      return this.field10.contains(var1);
   }

   @Override
   boolean isPartialView() {
      return false;
   }

   @Override
   public int size() {
      return this.field11.size();
   }
}
