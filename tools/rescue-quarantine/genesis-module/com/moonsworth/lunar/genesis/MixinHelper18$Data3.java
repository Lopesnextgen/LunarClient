package com.moonsworth.lunar.genesis;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.Uninterruptibles;

class MixinHelper18$Data3<V> extends MixinHelper312_2<V> implements ListenableFuture<V> {
   private static final ThreadFactory field1 = new ThreadFactoryBuilder().method2(true).method1("ListenableFutureAdapter-thread-%d").build();
   private static final Executor field2 = Executors.newCachedThreadPool(field1);
   private final Executor field3;
   private final MixinHelper13_3 field4 = new MixinHelper13_3();
   private final AtomicBoolean field5 = new AtomicBoolean(false);
   private final Future<V> field6;

   MixinHelper18$Data3(Future<V> var1) {
      this(var1, field2);
   }

   MixinHelper18$Data3(Future<V> var1, Executor var2) {
      this.field6 = Preconditions.checkNotNull(var1);
      this.field3 = Preconditions.checkNotNull(var2);
   }

   @Override
   protected Future<V> delegate() {
      return this.field6;
   }

   @Override
   public void addListener(Runnable var1, Executor var2) {
      this.field4.add(var1, var2);
      if (this.field5.compareAndSet(false, true)) {
         if (this.field6.isDone()) {
            this.field4.execute();
            return;
         }

         this.field3.execute(new Runnable() {
            @Override
            public void run() {
               try {
                  Uninterruptibles.getUninterruptibly(MixinHelper18$Data3.this.field6);
               } catch (Throwable var2x) {
               }

               MixinHelper18$Data3.this.field4.execute();
            }
         });
      }
   }
}
