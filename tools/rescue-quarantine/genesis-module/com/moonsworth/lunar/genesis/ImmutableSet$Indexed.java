package com.moonsworth.lunar.genesis;

import java.util.Spliterator;
import java.util.function.Consumer;
import com.google.common.collect.ImmutableList;
import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;

abstract class ImmutableSet$Indexed<E> extends AbstractCollectionIterator5<E> {
   ImmutableSet$Indexed() {
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

   int copyIntoArray(Object[] items1, int number2) {
      return this.method2().copyIntoArray(items1, number2);
   }

   ImmutableList<E> method17() {
      return new Data$1(this);
   }
}
