package com.moonsworth.lunar.genesis;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
class ConsumingQueueIterator<T> extends MixinHelperIterator32_2<T> {
   private final Queue<T> field2;

   ConsumingQueueIterator(T... items1) {
      this.field2 = new ArrayDeque<>(items1.length);
      Collections.addAll(this.field2, (T[])items1);
   }

   ConsumingQueueIterator(Queue<T> list1) {
      this.field2 = (Queue<T>)Preconditions.checkNotNull(list1);
   }

   public T computeNext() {
      return (T)(this.field2.isEmpty() ? this.method1() : this.field2.remove());
   }
}
