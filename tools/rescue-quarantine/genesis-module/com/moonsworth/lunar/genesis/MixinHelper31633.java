package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Deque;
import java.util.Iterator;
import com.google.common.collect.ForwardingQueue;

@Annotation3
public abstract class MixinHelper31633<E> extends ForwardingQueue<E> implements Deque<E> {
   protected MixinHelper31633() {
   }

   protected abstract Deque<E> delegate();

   @Override
   public void addFirst(E var1) {
      this.delegate().addFirst((E)var1);
   }

   @Override
   public void addLast(E var1) {
      this.delegate().addLast((E)var1);
   }

   @Override
   public Iterator<E> descendingIterator() {
      return this.delegate().descendingIterator();
   }

   @Override
   public E getFirst() {
      return this.delegate().getFirst();
   }

   @Override
   public E getLast() {
      return this.delegate().getLast();
   }

   @CanIgnoreReturnValue
   @Override
   public boolean offerFirst(E var1) {
      return this.delegate().offerFirst((E)var1);
   }

   @CanIgnoreReturnValue
   @Override
   public boolean offerLast(E var1) {
      return this.delegate().offerLast((E)var1);
   }

   @Override
   public E peekFirst() {
      return this.delegate().peekFirst();
   }

   @Override
   public E peekLast() {
      return this.delegate().peekLast();
   }

   @CanIgnoreReturnValue
   @Override
   public E pollFirst() {
      return this.delegate().pollFirst();
   }

   @CanIgnoreReturnValue
   @Override
   public E pollLast() {
      return this.delegate().pollLast();
   }

   @CanIgnoreReturnValue
   @Override
   public E pop() {
      return this.delegate().pop();
   }

   @Override
   public void push(E var1) {
      this.delegate().push((E)var1);
   }

   @CanIgnoreReturnValue
   @Override
   public E removeFirst() {
      return this.delegate().removeFirst();
   }

   @CanIgnoreReturnValue
   @Override
   public E removeLast() {
      return this.delegate().removeLast();
   }

   @CanIgnoreReturnValue
   @Override
   public boolean removeFirstOccurrence(Object var1) {
      return this.delegate().removeFirstOccurrence(var1);
   }

   @CanIgnoreReturnValue
   @Override
   public boolean removeLastOccurrence(Object var1) {
      return this.delegate().removeLastOccurrence(var1);
   }
}
