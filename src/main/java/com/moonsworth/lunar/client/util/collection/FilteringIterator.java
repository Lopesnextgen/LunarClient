package com.moonsworth.lunar.client.util.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class FilteringIterator<T> implements Iterator<T> {
   private final Iterator<T> field1;
   private final Predicate<T> field2;
   private T nextElement;

   public FilteringIterator(Collection<T> list, Predicate<T> predicate2) {
      this(list.iterator(), predicate2);
   }

   public FilteringIterator(Iterator<T> iterator, Predicate<T> predicate2) {
      this.field1 = iterator;
      this.field2 = predicate2;
   }

   @Override
   public final boolean hasNext() {
      return this.nextElement != null || this.method1();
   }

   @Override
   public final T next() {
      if (this.nextElement == null && !this.method1()) {
         throw new NoSuchElementException();
      }

      Object obj1 = this.nextElement;
      this.nextElement = null;
      return (T)obj1;
   }

   private boolean method1() {
      while (this.field1.hasNext()) {
         Object obj1 = this.field1.next();
         if (this.field2.test((T)obj1)) {
            this.nextElement = (T)obj1;
            return true;
         }
      }

      return false;
   }

   @Override
   public final void remove() {
      if (this.nextElement != null) {
         throw new IllegalStateException("remove() cannot be called");
      }

      this.field1.remove();
   }
}
