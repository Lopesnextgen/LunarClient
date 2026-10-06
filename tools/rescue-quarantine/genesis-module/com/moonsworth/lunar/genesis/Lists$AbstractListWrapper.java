package com.moonsworth.lunar.genesis;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import com.google.common.base.Preconditions;

class Lists$AbstractListWrapper<E> extends AbstractList<E> {
   final List<E> field1;

   Lists$AbstractListWrapper(List<E> list1) {
      this.field1 = (List<E>)Preconditions.checkNotNull(list1);
   }

   @Override
   public void add(int index1, E value2) {
      this.field1.add(index1, (E)value2);
   }

   @Override
   public boolean addAll(int number1, Collection<? extends E> list2) {
      return this.field1.addAll(number1, list2);
   }

   @Override
   public E get(int index1) {
      return this.field1.get(index1);
   }

   @Override
   public E remove(int index1) {
      return this.field1.remove(index1);
   }

   @Override
   public E set(int index1, E value2) {
      return this.field1.set(index1, (E)value2);
   }

   @Override
   public boolean contains(Object obj1) {
      return this.field1.contains(obj1);
   }

   @Override
   public int size() {
      return this.field1.size();
   }
}
