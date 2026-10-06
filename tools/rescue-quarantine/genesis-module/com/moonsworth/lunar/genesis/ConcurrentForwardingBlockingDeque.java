package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ForwardingDeque;

@GwtIncompatible
public abstract class ConcurrentForwardingBlockingDeque<E> extends ForwardingDeque<E> implements BlockingDeque<E> {
   protected ConcurrentForwardingBlockingDeque() {
   }

   protected abstract BlockingDeque<E> delegate();

   @Override
   public int remainingCapacity() {
      return this.delegate().remainingCapacity();
   }

   @Override
   public void putFirst(E value1) {
      this.delegate().putFirst((E)value1);
   }

   @Override
   public void putLast(E value1) {
      this.delegate().putLast((E)value1);
   }

   @Override
   public boolean offerFirst(E value1, long number2, TimeUnit timeunit4) {
      return this.delegate().offerFirst((E)value1, number2, timeunit4);
   }

   @Override
   public boolean offerLast(E value1, long number2, TimeUnit timeunit4) {
      return this.delegate().offerLast((E)value1, number2, timeunit4);
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
   public E pollFirst(long number1, TimeUnit timeunit3) {
      return this.delegate().pollFirst(number1, timeunit3);
   }

   @Override
   public E pollLast(long number1, TimeUnit timeunit3) {
      return this.delegate().pollLast(number1, timeunit3);
   }

   @Override
   public void put(E value1) {
      this.delegate().put((E)value1);
   }

   @Override
   public boolean offer(E value1, long number2, TimeUnit timeunit4) {
      return this.delegate().offer((E)value1, number2, timeunit4);
   }

   @Override
   public E take() {
      return this.delegate().take();
   }

   @Override
   public E poll(long number1, TimeUnit timeunit3) {
      return this.delegate().poll(number1, timeunit3);
   }

   @Override
   public int drainTo(Collection<? super E> list1) {
      return this.delegate().drainTo(list1);
   }

   @Override
   public int drainTo(Collection<? super E> list1, int number2) {
      return this.delegate().drainTo(list1, number2);
   }
}
