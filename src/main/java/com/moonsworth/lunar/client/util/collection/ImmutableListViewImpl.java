package com.moonsworth.lunar.client.util.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class ImmutableListViewImpl<T> implements ImmutableListView<T> {
   private final List<T> field1;

   public ImmutableListViewImpl(List<T> list1) {
      this.field1 = list1;
   }

   @NotNull
   @Override
   public Iterator<T> iterator() {
      return new Iterator<T>() {
         private int index = 0;

         @Override
         public boolean hasNext() {
            return ImmutableListViewImpl.this.field1.size() > this.index;
         }

         @Override
         public T next() {
            return !this.hasNext() ? null : ImmutableListViewImpl.this.field1.get(this.index++);
         }
      };
   }

   @Override
   public T get(int index1) {
      return this.field1.get(index1);
   }

   @NotNull
   @Override
   public ListIterator<T> listIterator() {
      return this.listIterator(0);
   }

   @NotNull
   @Override
   public ListIterator<T> listIterator(int index1) {
      return new ListIterator<T>() {
         private int index = 0;

         @Override
         public boolean hasNext() {
            return ImmutableListViewImpl.this.field1.size() > this.index;
         }

         @Override
         public T next() {
            return !this.hasNext() ? null : ImmutableListViewImpl.this.field1.get(this.index++);
         }

         @Override
         public boolean hasPrevious() {
            return this.index > 0;
         }

         @Override
         public T previous() {
            return !this.hasPrevious() ? null : ImmutableListViewImpl.this.field1.get(--this.index);
         }

         @Override
         public int nextIndex() {
            return this.index;
         }

         @Override
         public int previousIndex() {
            return this.index - 1;
         }

         @Override
         public void remove() {
            throw new UnsupportedOperationException("List is immutable!");
         }

         @Override
         public void set(T value1) {
            throw new UnsupportedOperationException("List is immutable!");
         }

         @Override
         public void add(T value1) {
            throw new UnsupportedOperationException("List is immutable!");
         }
      };
   }

   @NotNull
   @Override
   public List<T> subList(int index1, int index2) {
      return ImmutableListView.method1(this.field1.subList(index1, index2));
   }

   @Generated
   @Override
   public int size() {
      return this.field1.size();
   }

   @Generated
   @Override
   public boolean isEmpty() {
      return this.field1.isEmpty();
   }

   @Generated
   @Override
   public boolean contains(Object obj1) {
      return this.field1.contains(obj1);
   }

   @Generated
   @Override
   public Object[] toArray() {
      return this.field1.toArray();
   }

   @Generated
   @Override
   public <E> E[] toArray(E[] items1) {
      return (E[])this.field1.toArray(items1);
   }

   @Generated
   @Override
   public boolean containsAll(Collection<?> list1) {
      return this.field1.containsAll(list1);
   }

   @Generated
   @Override
   public int indexOf(Object obj1) {
      return this.field1.indexOf(obj1);
   }

   @Generated
   @Override
   public int lastIndexOf(Object obj1) {
      return this.field1.lastIndexOf(obj1);
   }
}
