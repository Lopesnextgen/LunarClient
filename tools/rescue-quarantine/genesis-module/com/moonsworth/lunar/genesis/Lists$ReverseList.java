package com.moonsworth.lunar.genesis;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

class Lists$ReverseList<T> extends AbstractList<T> {
   private final List<T> field1;

   Lists$ReverseList(List<T> list1) {
      this.field1 = (List<T>)Preconditions.checkNotNull(list1);
   }

   List<T> getForwardList() {
      return this.field1;
   }

   private int reverseIndex(int number1) {
      int number2 = this.size();
      Preconditions.checkElementIndex(number1, number2);
      return number2 - 1 - number1;
   }

   private int reversePosition(int number1) {
      int number2 = this.size();
      Preconditions.checkPositionIndex(number1, number2);
      return number2 - number1;
   }

   @Override
   public void add(int number1, @Nullable T value2) {
      this.field1.add(this.reversePosition(number1), (T)value2);
   }

   @Override
   public void clear() {
      this.field1.clear();
   }

   @Override
   public T remove(int number1) {
      return this.field1.remove(this.reverseIndex(number1));
   }

   @Override
   protected void removeRange(int index1, int index2) {
      this.subList(index1, index2).clear();
   }

   @Override
   public T set(int number1, @Nullable T value2) {
      return this.field1.set(this.reverseIndex(number1), (T)value2);
   }

   @Override
   public T get(int number1) {
      return this.field1.get(this.reverseIndex(number1));
   }

   @Override
   public int size() {
      return this.field1.size();
   }

   @Override
   public List<T> subList(int number1, int number2) {
      Preconditions.checkPositionIndexes(number1, number2, this.size());
      return MixinHelper22.reverse(this.field1.subList(this.reversePosition(number2), this.reversePosition(number1)));
   }

   @Override
   public Iterator<T> iterator() {
      return this.listIterator();
   }

   @Override
   public ListIterator<T> listIterator(int number1) {
      int number2 = this.reversePosition(number1);
      ListIterator iterator3 = this.field1.listIterator(number2);
      return new Data13$1(this, iterator3);
   }
}
