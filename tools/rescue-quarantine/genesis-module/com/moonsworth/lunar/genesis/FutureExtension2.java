package com.moonsworth.lunar.genesis;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.base.Preconditions;

@GwtCompatible
class FutureExtension2<V> implements ListenableFuture<V> {
   static final ListenableFuture<?> field1 = new FutureExtension2(null);
   private static final Logger field2 = Logger.getLogger(FutureExtension2.class.getName());
   private final @Nullable V field3;

   FutureExtension2(@Nullable V var1) {
      this.field3 = (V)var1;
   }

   @Override
   public void addListener(Runnable var1, Executor var2) {
      Preconditions.checkNotNull(var1, "Runnable was null.");
      Preconditions.checkNotNull(var2, "Executor was null.");

      try {
         var2.execute(var1);
      } catch (RuntimeException var4) {
         field2.log(Level.SEVERE, "RuntimeException while executing runnable " + var1 + " with executor " + var2, var4);
      }
   }

   @Override
   public boolean cancel(boolean var1) {
      return false;
   }

   @Override
   public V get() {
      return this.field3;
   }

   @Override
   public V get(long var1, TimeUnit var3) {
      Preconditions.checkNotNull(var3);
      return this.get();
   }

   @Override
   public boolean isCancelled() {
      return false;
   }

   @Override
   public boolean isDone() {
      return true;
   }

   @Override
   public String toString() {
      return super.toString() + "[status=SUCCESS, result=[" + this.field3 + "]]";
   }

   static final class Data<V> extends AbstractFuture.Data5<V> {
      Data(Throwable var1) {
         this.setException(var1);
      }
   }

   static final class Data2<V> extends AbstractFuture.Data5<V> {
      Data2() {
         this.cancel(false);
      }
   }
}
