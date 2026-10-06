package com.moonsworth.lunar.genesis;

import java.util.Spliterator;
import java.util.function.Consumer;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;

@GwtCompatible(emulated = true)
abstract class IndexedImmutableSet<E> extends ImmutableSet<E> {
   IndexedImmutableSet() {
   }

   abstract E get(int number1);

   public UnmodifiableIterator<E> method1() {
      return this.method2().method1();
   }

   public Spliterator<E> spliterator() {
      return MixinHelper3_5.indexed(this.size(), 1297, this::get);
   }

   public void forEach(Consumer<? super E> consumer1) {
      Preconditions.checkNotNull(consumer1);
      int number2 = this.size();

      for (int index3 = 0; index3 < number2; index3++) {
         consumer1.accept(this.get(index3));
      }
   }

   @GwtIncompatible
   int copyIntoArray(Object[] items1, int number2) {
      return this.method2().copyIntoArray(items1, number2);
   }

   ImmutableList<E> method17() {
      return new AbstractCollectionIterator53$1(this);
   }
}
