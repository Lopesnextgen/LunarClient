package com.moonsworth.lunar.genesis;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ForwardingFuture;
import com.google.common.base.Preconditions;

class JdkFutureAdapters$ListenableFutureAdapter<V> extends ForwardingFuture<V> implements ListenableFuture<V> {
   private static final ThreadFactory field1 = new ThreadFactoryBuilder().method2(true).method1("ListenableFutureAdapter-thread-%d").build();
   private static final Executor field2 = Executors.newCachedThreadPool(field1);
   private final Executor field3;
   private final MixinHelper13_3 field4 = new MixinHelper13_3();
   private final AtomicBoolean field5 = new AtomicBoolean(false);
   private final Future<V> field6;

   JdkFutureAdapters$ListenableFutureAdapter(Future<V> future1) {
      this(future1, field2);
   }

   JdkFutureAdapters$ListenableFutureAdapter(Future<V> future1, Executor executor2) {
      this.field6 = (Future<V>)Preconditions.checkNotNull(future1);
      this.field3 = (Executor)Preconditions.checkNotNull(executor2);
   }

   protected Future<V> delegate() {
      return this.field6;
   }

   public void addListener(Runnable runnable1, Executor executor2) {
      this.field4.add(runnable1, executor2);
      if (this.field5.compareAndSet(false, true)) {
         if (this.field6.isDone()) {
            this.field4.execute();
            return;
         }

         this.field3.execute(new Data3$1(this));
      }
   }
}
