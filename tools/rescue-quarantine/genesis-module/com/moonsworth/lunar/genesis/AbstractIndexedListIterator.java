package com.moonsworth.lunar.genesis;

import java.util.NoSuchElementException;
import com.google.common.collect.UnmodifiableListIterator;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
abstract class AbstractIndexedListIterator<E> extends UnmodifiableListIterator<E> {
   private final int field1;
   private int position;

   protected abstract E get(int number1);

   protected AbstractIndexedListIterator(int number1) {
      this(number1, 0);
   }

   protected AbstractIndexedListIterator(int number1, int number2) {
      Preconditions.checkPositionIndex(number2, number1);
      this.field1 = number1;
      this.position = number2;
   }

   @Override
   public final boolean hasNext() {
      return this.position < this.field1;
   }

   @Override
   public final E next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         return this.get(this.position++);
      }
   }

   @Override
   public final int nextIndex() {
      return this.position;
   }

   @Override
   public final boolean hasPrevious() {
      return this.position > 0;
   }

   @Override
   public final E previous() {
      if (!this.hasPrevious()) {
         throw new NoSuchElementException();
      } else {
         return this.get(--this.position);
      }
   }

   @Override
   public final int previousIndex() {
      return this.position - 1;
   }
}
