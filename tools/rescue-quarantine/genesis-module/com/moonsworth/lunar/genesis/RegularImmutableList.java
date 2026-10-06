package com.moonsworth.lunar.genesis;

import java.util.Spliterator;
import java.util.Spliterators;
import com.google.common.collect.UnmodifiableListIterator;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.eventbus.Subscribe;
import com.google.common.collect.Iterators;

@GwtCompatible(serializable = true, emulated = true)
class RegularImmutableList<E> extends ImmutableList<E> {
   static final ImmutableList<Object> field3 = new RegularImmutableList<>(new Object[0]);
   @Subscribe
   final transient Object[] field4;

   RegularImmutableList(Object[] items1) {
      this.field4 = items1;
   }

   public int size() {
      return this.field4.length;
   }

   boolean isPartialView() {
      return false;
   }

   Object[] internalArray() {
      return this.field4;
   }

   int internalArrayStart() {
      return 0;
   }

   int internalArrayEnd() {
      return this.field4.length;
   }

   int copyIntoArray(Object[] items1, int number2) {
      System.arraycopy(this.field4, 0, items1, number2, this.field4.length);
      return number2 + this.field4.length;
   }

   public E get(int index1) {
      return (E)this.field4[index1];
   }

   public UnmodifiableListIterator<E> method25(int number1) {
      return Iterators.method19(this.field4, 0, this.field4.length, number1);
   }

   public Spliterator<E> spliterator() {
      return Spliterators.spliterator(this.field4, 1296);
   }
}
