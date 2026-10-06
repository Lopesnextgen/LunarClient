package com.moonsworth.lunar.genesis;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;

class Iterators$ConcatenatedIterator<T> implements Iterator<T> {
   private @Nullable Iterator<? extends T> toRemove;
   private Iterator<? extends T> iterator = MixinHelper25.method1();
   private Iterator<? extends Iterator<? extends T>> topMetaIterator;
   private @Nullable Deque<Iterator<? extends Iterator<? extends T>>> metaIterators;

   Iterators$ConcatenatedIterator(Iterator<? extends Iterator<? extends T>> iterator1) {
      this.topMetaIterator = (Iterator<? extends Iterator<? extends T>>)Preconditions.checkNotNull(iterator1);
   }

   private @Nullable Iterator<? extends Iterator<? extends T>> getTopMetaIterator() {
      while (this.topMetaIterator == null || !this.topMetaIterator.hasNext()) {
         if (this.metaIterators == null || this.metaIterators.isEmpty()) {
            return null;
         }

         this.topMetaIterator = this.metaIterators.removeFirst();
      }

      return this.topMetaIterator;
   }

   @Override
   public boolean hasNext() {
      while (!((Iterator)Preconditions.checkNotNull(this.iterator)).hasNext()) {
         this.topMetaIterator = this.getTopMetaIterator();
         if (this.topMetaIterator == null) {
            return false;
         }

         this.iterator = (Iterator<? extends T>)this.topMetaIterator.next();
         if (this.iterator instanceof Iterators$ConcatenatedIterator) {
            Iterators$ConcatenatedIterator mixinhelper25$data91 = (Iterators$ConcatenatedIterator)this.iterator;
            this.iterator = mixinhelper25$data91.iterator;
            if (this.metaIterators == null) {
               this.metaIterators = new ArrayDeque<>();
            }

            this.metaIterators.addFirst(this.topMetaIterator);
            if (mixinhelper25$data91.metaIterators != null) {
               while (!mixinhelper25$data91.metaIterators.isEmpty()) {
                  this.metaIterators.addFirst(mixinhelper25$data91.metaIterators.removeLast());
               }
            }

            this.topMetaIterator = mixinhelper25$data91.topMetaIterator;
         }
      }

      return true;
   }

   @Override
   public T next() {
      if (this.hasNext()) {
         this.toRemove = this.iterator;
         return (T)this.iterator.next();
      } else {
         throw new NoSuchElementException();
      }
   }

   @Override
   public void remove() {
      CollectPreconditions.checkRemove(this.toRemove != null);
      this.toRemove.remove();
      this.toRemove = null;
   }
}
