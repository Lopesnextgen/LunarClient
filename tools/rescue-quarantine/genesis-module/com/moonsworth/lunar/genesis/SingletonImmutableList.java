package com.moonsworth.lunar.genesis;

import java.util.Collections;
import java.util.Spliterator;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;

@GwtCompatible(serializable = true, emulated = true)
final class SingletonImmutableList<E> extends ImmutableList<E> {
   final transient E field3;

   SingletonImmutableList(E value1) {
      this.field3 = (E)Preconditions.checkNotNull(value1);
   }

   public E get(int number1) {
      Preconditions.checkElementIndex(number1, 1);
      return this.field3;
   }

   public UnmodifiableIterator<E> method1() {
      return Iterators.method20(this.field3);
   }

   public Spliterator<E> spliterator() {
      return Collections.singleton(this.field3).spliterator();
   }

   public int size() {
      return 1;
   }

   public ImmutableList<E> method26(int number1, int number2) {
      Preconditions.checkPositionIndexes(number1, number2, 1);
      return number1 == number2 ? ImmutableList.method3() : this;
   }

   public String toString() {
      return '[' + this.field3.toString() + ']';
   }

   boolean isPartialView() {
      return false;
   }
}
