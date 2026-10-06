package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

class ImmutableList$ReverseImmutableList<E> extends AbstractCollectionIterator3<E> {
   private final transient AbstractCollectionIterator3<E> field3;

   ImmutableList$ReverseImmutableList(AbstractCollectionIterator3<E> abstractcollectioniterator31) {
      this.field3 = abstractcollectioniterator31;
   }

   private int reverseIndex(int number1) {
      return this.size() - 1 - number1;
   }

   private int reversePosition(int number1) {
      return this.size() - number1;
   }

   public AbstractCollectionIterator3<E> method29() {
      return this.field3;
   }

   public boolean contains(@Nullable Object obj1) {
      return this.field3.contains(obj1);
   }

   public int indexOf(@Nullable Object obj1) {
      int number2 = this.field3.lastIndexOf(obj1);
      return number2 >= 0 ? this.reverseIndex(number2) : -1;
   }

   public int lastIndexOf(@Nullable Object obj1) {
      int number2 = this.field3.indexOf(obj1);
      return number2 >= 0 ? this.reverseIndex(number2) : -1;
   }

   public AbstractCollectionIterator3<E> method26(int number1, int number2) {
      Preconditions.checkPositionIndexes(number1, number2, this.size());
      return this.field3.method26(this.reversePosition(number2), this.reversePosition(number1)).method29();
   }

   public E get(int number1) {
      Preconditions.checkElementIndex(number1, this.size());
      return (E)this.field3.get(this.reverseIndex(number1));
   }

   public int size() {
      return this.field3.size();
   }

   boolean isPartialView() {
      return this.field3.isPartialView();
   }
}
