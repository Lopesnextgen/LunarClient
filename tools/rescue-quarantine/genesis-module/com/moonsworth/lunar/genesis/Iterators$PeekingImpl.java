package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.PeekingIterator;
import com.google.common.base.Preconditions;

class Iterators$PeekingImpl<E> implements PeekingIterator<E> {
   private final Iterator<? extends E> field1;
   private boolean hasPeeked;
   private @Nullable E peekedElement;

   public Iterators$PeekingImpl(Iterator<? extends E> iterator1) {
      this.field1 = (Iterator<? extends E>)Preconditions.checkNotNull(iterator1);
   }

   public boolean hasNext() {
      return this.hasPeeked || this.field1.hasNext();
   }

   public E next() {
      if (!this.hasPeeked) {
         return (E)this.field1.next();
      }

      Object obj1 = this.peekedElement;
      this.hasPeeked = false;
      this.peekedElement = null;
      return (E)obj1;
   }

   public void remove() {
      Preconditions.checkState(!this.hasPeeked, "Can't remove after you've peeked at next");
      this.field1.remove();
   }

   public E peek() {
      if (!this.hasPeeked) {
         this.peekedElement = (E)this.field1.next();
         this.hasPeeked = true;
      }

      return this.peekedElement;
   }
}
