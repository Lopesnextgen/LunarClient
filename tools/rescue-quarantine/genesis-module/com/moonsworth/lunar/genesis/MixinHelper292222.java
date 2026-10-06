package com.moonsworth.lunar.genesis;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.base.Preconditions;

@GwtCompatible
final class MixinHelper292222<V> extends MixinHelper29222<V> {
   private final ListenableFuture<V> field8;

   MixinHelper292222(ListenableFuture<V> var1) {
      this.field8 = Preconditions.checkNotNull(var1);
   }

   @Override
   public void addListener(Runnable var1, Executor var2) {
      this.field8.addListener(var1, var2);
   }

   @Override
   public boolean cancel(boolean var1) {
      return this.field8.cancel(var1);
   }

   @Override
   public boolean isCancelled() {
      return this.field8.isCancelled();
   }

   @Override
   public boolean isDone() {
      return this.field8.isDone();
   }

   @Override
   public V get() {
      return this.field8.get();
   }

   @Override
   public V get(long var1, TimeUnit var3) {
      return this.field8.get(var1, var3);
   }

   @Override
   public String toString() {
      return this.field8.toString();
   }
}
