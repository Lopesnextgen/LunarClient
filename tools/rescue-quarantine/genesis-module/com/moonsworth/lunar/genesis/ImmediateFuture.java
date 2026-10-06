package com.moonsworth.lunar.genesis;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.base.Preconditions;

@GwtCompatible
class ImmediateFuture<V> implements ListenableFuture<V> {
   static final ListenableFuture<?> field1 = new ImmediateFuture(null);
   private static final Logger field2 = Logger.getLogger(ImmediateFuture.class.getName());
   private final @Nullable V field3;

   ImmediateFuture(@Nullable V value1) {
      this.field3 = (V)value1;
   }

   public void addListener(Runnable runnable1, Executor executor2) {
      Preconditions.checkNotNull(runnable1, "Runnable was null.");
      Preconditions.checkNotNull(executor2, "Executor was null.");

      try {
         executor2.execute(runnable1);
      } catch (RuntimeException exception4) {
         field2.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable1 + " with executor " + executor2, exception4);
      }
   }

   public boolean cancel(boolean flag1) {
      return false;
   }

   public V get() {
      return this.field3;
   }

   public V get(long number1, TimeUnit timeunit3) {
      Preconditions.checkNotNull(timeunit3);
      return this.get();
   }

   public boolean isCancelled() {
      return false;
   }

   public boolean isDone() {
      return true;
   }

   @Override
   public String toString() {
      return super.toString() + "[status=SUCCESS, result=[" + this.field3 + "]]";
   }
}
