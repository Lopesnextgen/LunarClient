package com.moonsworth.lunar.genesis;
import com.google.common.collect.UnmodifiableListIterator;

final class Iterators$ArrayItr<T> extends AbstractIndexedListIterator<T> {
   static final UnmodifiableListIterator<Object> field2 = new Iterators$ArrayItr<Object>(new Object[0], 0, 0, 0);
   private final T[] field3;
   private final int field4;

   Iterators$ArrayItr(T[] items1, int number2, int number3, int number4) {
      super(number3, number4);
      this.field3 = (T[])items1;
      this.field4 = number2;
   }

   protected T get(int index1) {
      return this.field3[this.field4 + index1];
   }
}
