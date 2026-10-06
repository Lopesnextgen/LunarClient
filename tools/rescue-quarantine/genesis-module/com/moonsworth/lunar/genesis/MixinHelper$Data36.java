package com.moonsworth.lunar.genesis;

import java.util.Queue;
import org.checkerframework.checker.nullness.qual.Nullable;

class MixinHelper$Data36<E> extends MixinHelper$Data44<E> implements Queue<E> {
   private static final long field5 = 0L;

   MixinHelper$Data36(Queue<E> var1, @Nullable Object var2) {
      super(var1, var2);
   }

   Queue<E> delegate() {
      return (Queue<E>)super.delegate();
   }

   @Override
   public E element() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().element();
      }
   }

   @Override
   public boolean offer(E var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().offer((E)var1);
      }
   }

   @Override
   public E peek() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().peek();
      }
   }

   @Override
   public E poll() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().poll();
      }
   }

   @Override
   public E remove() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().remove();
      }
   }
}
