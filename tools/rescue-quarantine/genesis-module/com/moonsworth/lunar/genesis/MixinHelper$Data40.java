package com.moonsworth.lunar.genesis;

import java.util.Deque;
import java.util.Iterator;
import org.checkerframework.checker.nullness.qual.Nullable;

final class MixinHelper$Data40<E> extends MixinHelper$Data36<E> implements Deque<E> {
   private static final long field6 = 0L;

   MixinHelper$Data40(Deque<E> var1, @Nullable Object var2) {
      super(var1, var2);
   }

   Deque<E> delegate() {
      return (Deque<E>)super.delegate();
   }

   @Override
   public void addFirst(E var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         this.delegate().addFirst((E)var1);
      }
   }

   @Override
   public void addLast(E var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         this.delegate().addLast((E)var1);
      }
   }

   @Override
   public boolean offerFirst(E var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().offerFirst((E)var1);
      }
   }

   @Override
   public boolean offerLast(E var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().offerLast((E)var1);
      }
   }

   @Override
   public E removeFirst() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().removeFirst();
      }
   }

   @Override
   public E removeLast() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().removeLast();
      }
   }

   @Override
   public E pollFirst() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().pollFirst();
      }
   }

   @Override
   public E pollLast() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().pollLast();
      }
   }

   @Override
   public E getFirst() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().getFirst();
      }
   }

   @Override
   public E getLast() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().getLast();
      }
   }

   @Override
   public E peekFirst() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().peekFirst();
      }
   }

   @Override
   public E peekLast() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().peekLast();
      }
   }

   @Override
   public boolean removeFirstOccurrence(Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().removeFirstOccurrence(var1);
      }
   }

   @Override
   public boolean removeLastOccurrence(Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().removeLastOccurrence(var1);
      }
   }

   @Override
   public void push(E var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         this.delegate().push((E)var1);
      }
   }

   @Override
   public E pop() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().pop();
      }
   }

   @Override
   public Iterator<E> descendingIterator() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().descendingIterator();
      }
   }
}
