package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.function.Predicate;
import com.google.common.base.Preconditions;
import com.google.common.base.Function;

class Lists$TransformingRandomAccessList<F, T> extends AbstractList<T> implements Serializable, RandomAccess {
   final List<F> field1;
   final Function<? super F, ? extends T> field2;
   private static final long field3 = 0L;

   Lists$TransformingRandomAccessList(List<F> list1, Function<? super F, ? extends T> mixinhelper24_22) {
      this.field1 = (List<F>)Preconditions.checkNotNull(list1);
      this.field2 = (Function<? super F, ? extends T>)Preconditions.checkNotNull(mixinhelper24_22);
   }

   @Override
   public void clear() {
      this.field1.clear();
   }

   @Override
   public T get(int index1) {
      return (T)this.field2.apply(this.field1.get(index1));
   }

   @Override
   public Iterator<T> iterator() {
      return this.listIterator();
   }

   @Override
   public ListIterator<T> listIterator(int number1) {
      return new Data3$1(this, this.field1.listIterator(number1));
   }

   @Override
   public boolean isEmpty() {
      return this.field1.isEmpty();
   }

   @Override
   public boolean removeIf(Predicate<? super T> predicate1) {
      Preconditions.checkNotNull(predicate1);
      return this.field1.removeIf(arg2 -> predicate1.test((T)this.field2.apply(arg2)));
   }

   @Override
   public T remove(int index1) {
      return (T)this.field2.apply(this.field1.remove(index1));
   }

   @Override
   public int size() {
      return this.field1.size();
   }
}
