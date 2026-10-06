package com.moonsworth.lunar.genesis;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import com.google.common.collect.PeekingIterator;
import com.google.common.collect.UnmodifiableIterator;

class Iterators$MergingIterator<T> extends UnmodifiableIterator<T> {
   final Queue<PeekingIterator<T>> field1;

   public Iterators$MergingIterator(Iterable<? extends Iterator<? extends T>> list1, Comparator<? super T> comparator2) {
      Data11$1 data11$13 = new Data11$1(this, comparator2);
      this.field1 = new PriorityQueue<>(2, data11$13);

      for (Iterator iterator5 : list1) {
         if (iterator5.hasNext()) {
            this.field1.add(MixinHelper25.method22(iterator5));
         }
      }
   }

   public boolean hasNext() {
      return !this.field1.isEmpty();
   }

   public T next() {
      PeekingIterator mixinhelperiterator_21 = this.field1.remove();
      Object obj2 = mixinhelperiterator_21.next();
      if (mixinhelperiterator_21.hasNext()) {
         this.field1.add(mixinhelperiterator_21);
      }

      return (T)obj2;
   }
}
