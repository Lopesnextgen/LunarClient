package com.moonsworth.lunar.client.util.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class IterableImpl<T> implements Iterable<T> {
   private final Iterable<T> field1;
   private final Predicate<T> field2;

   public IterableImpl(Iterable<T> list1, Predicate<T> predicate2) {
      this.field1 = list1;
      this.field2 = predicate2;
   }

   @Override
   public Iterator<T> iterator() {
      return new IterableImpl.Data<>(this.field1, this.field2);
   }

   private static class Data<T> implements Iterator<T> {
      private final Iterator<T> field1;
      private final Predicate<T> field2;
      private T next;
      private boolean hasNext;
      private boolean field3;

      public Data(Iterable<T> list1, Predicate<T> predicate2) {
         this.field1 = list1.iterator();
         this.field2 = predicate2;
      }

      @Override
      public boolean hasNext() {
         if (this.field3) {
            return this.hasNext;
         }

         while (this.field1.hasNext()) {
            Object obj1 = this.field1.next();
            if (this.field2.test((T)obj1)) {
               this.next = (T)obj1;
               this.hasNext = true;
               this.field3 = true;
               return true;
            }
         }

         this.next = null;
         this.hasNext = false;
         this.field3 = true;
         return false;
      }

      @Override
      public T next() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         }

         Object obj1 = this.next;
         this.next = null;
         this.hasNext = false;
         this.field3 = false;
         return (T)obj1;
      }
   }
}
