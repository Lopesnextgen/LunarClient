package com.moonsworth.lunar.client.util.gui;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class ListExtension22<T> implements ListExtension2<T> {
   private final List<T> field1;

   public ListExtension22(List<T> var1) {
      this.field1 = var1;
   }

   @NotNull
   @Override
   public Iterator<T> iterator() {
      return new Iterator<T>() {
         private int index = 0;

         @Override
         public boolean hasNext() {
            return ListExtension22.this.field1.size() > this.index;
         }

         @Override
         public T next() {
            return !this.hasNext() ? null : ListExtension22.this.field1.get(this.index++);
         }
      };
   }

   @Override
   public T get(int var1) {
      return this.field1.get(var1);
   }

   @NotNull
   @Override
   public ListIterator<T> listIterator() {
      return this.listIterator(0);
   }

   @NotNull
   @Override
   public ListIterator<T> listIterator(int var1) {
      return new ListIterator<T>() {
         private int index = 0;

         @Override
         public boolean hasNext() {
            return ListExtension22.this.field1.size() > this.index;
         }

         @Override
         public T next() {
            return !this.hasNext() ? null : ListExtension22.this.field1.get(this.index++);
         }

         @Override
         public boolean hasPrevious() {
            return this.index > 0;
         }

         @Override
         public T previous() {
            return !this.hasPrevious() ? null : ListExtension22.this.field1.get(--this.index);
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
         public void set(T var1) {
            throw new UnsupportedOperationException("List is immutable!");
         }

         @Override
         public void add(T var1) {
            throw new UnsupportedOperationException("List is immutable!");
         }
      };
   }

   @NotNull
   @Override
   public List<T> subList(int var1, int value) {
      return ListExtension2.method1(this.field1.subList(var1, value));
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
   public boolean contains(Object var1) {
      return this.field1.contains(var1);
   }

   @Generated
   @Override
   public Object[] toArray() {
      return this.field1.toArray();
   }

   @Generated
   @Override
   public <E> E[] toArray(E[] var1) {
      return (E[])this.field1.toArray(var1);
   }

   @Generated
   @Override
   public boolean containsAll(Collection<?> var1) {
      return this.field1.containsAll(var1);
   }

   @Generated
   @Override
   public int indexOf(Object var1) {
      return this.field1.indexOf(var1);
   }

   @Generated
   @Override
   public int lastIndexOf(Object var1) {
      return this.field1.lastIndexOf(var1);
   }
}
