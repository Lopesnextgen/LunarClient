package com.moonsworth.lunar.genesis;

import java.util.concurrent.atomic.AtomicInteger;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;

final class Futures$InCompletionOrderState<T> {
   private boolean wasCancelled = false;
   private boolean shouldInterrupt = true;
   private final AtomicInteger field1;
   private final ListenableFuture<? extends T>[] field2;
   private volatile int delegateIndex = 0;

   private Futures$InCompletionOrderState(ListenableFuture<? extends T>[] items1) {
      this.field2 = items1;
      this.field1 = new AtomicInteger(items1.length);
   }

   private void recordOutputCancellation(boolean flag1) {
      this.wasCancelled = true;
      if (!flag1) {
         this.shouldInterrupt = false;
      }

      this.recordCompletion();
   }

   private void method1(ImmutableList<AbstractFuture<T>> abstractcollectioniterator31, int index2) {
      ListenableFuture futureextension3 = this.field2[index2];
      this.field2[index2] = null;

      for (int index4 = this.delegateIndex; index4 < abstractcollectioniterator31.size(); index4++) {
         if (((AbstractFuture)abstractcollectioniterator31.get(index4)).method3(futureextension3)) {
            this.recordCompletion();
            this.delegateIndex = index4 + 1;
            return;
         }
      }

      this.delegateIndex = abstractcollectioniterator31.size();
   }

   private void recordCompletion() {
      if (this.field1.decrementAndGet() == 0 && this.wasCancelled) {
         for (ListenableFuture futureextension4 : this.field2) {
            if (futureextension4 != null) {
               futureextension4.cancel(this.shouldInterrupt);
            }
         }
      }
   }
}
