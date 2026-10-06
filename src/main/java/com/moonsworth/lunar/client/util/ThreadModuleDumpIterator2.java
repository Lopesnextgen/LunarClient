package com.moonsworth.lunar.client.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ThreadModuleDumpIterator2<T> implements Iterator<T> {
   private final Iterator<T> field1;
   private final Predicate<T> field2;
   private T nextElement;

   public ThreadModuleDumpIterator2(Collection<T> var1, Predicate<T> var2) {
      this(var1.iterator(), var2);
   }

   public ThreadModuleDumpIterator2(Iterator<T> var1, Predicate<T> var2) {
      this.field1 = var1;
      this.field2 = var2;
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

      Object var1 = this.nextElement;
      this.nextElement = null;
      return (T)var1;
   }

   private boolean method1() {
      while (this.field1.hasNext()) {
         Object var1 = this.field1.next();
         if (this.field2.test((T)var1)) {
            this.nextElement = (T)var1;
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
