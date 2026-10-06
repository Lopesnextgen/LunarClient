package com.moonsworth.lunar.genesis;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.base.Preconditions;

@GwtCompatible
final class ForwardingFluentFuture<V> extends MixinHelper29222<V> {
   private final ListenableFuture<V> field8;

   ForwardingFluentFuture(ListenableFuture<V> futureextension1) {
      this.field8 = (ListenableFuture<V>)Preconditions.checkNotNull(futureextension1);
   }

   public void addListener(Runnable runnable1, Executor executor2) {
      this.field8.addListener(runnable1, executor2);
   }

   public boolean cancel(boolean flag1) {
      return this.field8.cancel(flag1);
   }

   public boolean isCancelled() {
      return this.field8.isCancelled();
   }

   public boolean isDone() {
      return this.field8.isDone();
   }

   public V get() {
      return (V)this.field8.get();
   }

   public V get(long index1, TimeUnit timeunit3) {
      return (V)this.field8.get(index1, timeunit3);
   }

   public String toString() {
      return this.field8.toString();
   }
}
