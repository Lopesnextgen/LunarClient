package com.moonsworth.lunar.genesis;

import java.util.Collections;
import java.util.Spliterator;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.base.Preconditions;

@GwtCompatible(serializable = true, emulated = true)
final class AbstractCollectionIterator32<E> extends ImmutableList<E> {
   final transient E field3;

   AbstractCollectionIterator32(E var1) {
      this.field3 = Preconditions.checkNotNull((E)var1);
   }

   @Override
   public E get(int var1) {
      Preconditions.checkElementIndex(var1, 1);
      return this.field3;
   }

   @Override
   public MixinHelperIterator3<E> method1() {
      return Iterators.method20(this.field3);
   }

   @Override
   public Spliterator<E> spliterator() {
      return Collections.singleton(this.field3).spliterator();
   }

   @Override
   public int size() {
      return 1;
   }

   @Override
   public ImmutableList<E> method26(int var1, int var2) {
      Preconditions.checkPositionIndexes(var1, var2, 1);
      return var1 == var2 ? ImmutableList.method3() : this;
   }

   @Override
   public String toString() {
      return '[' + this.field3.toString() + ']';
   }

   @Override
   boolean isPartialView() {
      return false;
   }
}
