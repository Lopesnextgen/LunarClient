package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

@Deprecated
@Annotation3
public abstract class MixinHelper316332<E> extends MixinHelper31633<E> implements BlockingDeque<E> {
   protected MixinHelper316332() {
   }

   protected abstract BlockingDeque<E> delegate();

   @Override
   public int remainingCapacity() {
      return this.delegate().remainingCapacity();
   }

   @Override
   public void putFirst(E var1) {
      this.delegate().putFirst((E)var1);
   }

   @Override
   public void putLast(E var1) {
      this.delegate().putLast((E)var1);
   }

   @Override
   public boolean offerFirst(E var1, long var2, TimeUnit var4) {
      return this.delegate().offerFirst((E)var1, var2, var4);
   }

   @Override
   public boolean offerLast(E var1, long var2, TimeUnit var4) {
      return this.delegate().offerLast((E)var1, var2, var4);
   }

   @Override
   public E takeFirst() {
      return this.delegate().takeFirst();
   }

   @Override
   public E takeLast() {
      return this.delegate().takeLast();
   }

   @Override
   public E pollFirst(long var1, TimeUnit var3) {
      return this.delegate().pollFirst(var1, var3);
   }

   @Override
   public E pollLast(long var1, TimeUnit var3) {
      return this.delegate().pollLast(var1, var3);
   }

   @Override
   public void put(E var1) {
      this.delegate().put((E)var1);
   }

   @Override
   public boolean offer(E var1, long var2, TimeUnit var4) {
      return this.delegate().offer((E)var1, var2, var4);
   }

   @Override
   public E take() {
      return this.delegate().take();
   }

   @Override
   public E poll(long var1, TimeUnit var3) {
      return this.delegate().poll(var1, var3);
   }

   @Override
   public int drainTo(Collection<? super E> var1) {
      return this.delegate().drainTo(var1);
   }

   @Override
   public int drainTo(Collection<? super E> var1, int var2) {
      return this.delegate().drainTo(var1, var2);
   }
}
