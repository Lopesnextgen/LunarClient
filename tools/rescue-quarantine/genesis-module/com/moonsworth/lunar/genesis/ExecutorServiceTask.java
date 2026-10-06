package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import com.google.common.collect.ImmutableList;
import com.google.common.base.Preconditions;

@CanIgnoreReturnValue
@Annotation3
abstract class ExecutorServiceTask implements ExecutorService {
   private final ExecutorService field1;

   protected ExecutorServiceTask(ExecutorService var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   protected abstract <T> Callable<T> wrapTask(Callable<T> var1);

   protected Runnable wrapTask(Runnable var1) {
      final Callable var2 = this.wrapTask(Executors.callable(var1, null));
      return new Runnable() {
         @Override
         public void run() {
            try {
               var2.call();
            } catch (Exception var2x) {
               MixinHelper13_2.throwIfUnchecked(var2x);
               throw new RuntimeException(var2x);
            }
         }
      };
   }

   private <T> ImmutableList<Callable<T>> method1(Collection<? extends Callable<T>> var1) {
      ImmutableList.Data2 var2 = ImmutableList.method30();

      for (Callable var4 : var1) {
         var2.method2(this.wrapTask(var4));
      }

      return var2.method6();
   }

   @Override
   public final void execute(Runnable var1) {
      this.field1.execute(this.wrapTask(var1));
   }

   @Override
   public final <T> Future<T> submit(Callable<T> var1) {
      return this.field1.submit(this.wrapTask(Preconditions.checkNotNull(var1)));
   }

   @Override
   public final Future<?> submit(Runnable var1) {
      return this.field1.submit(this.wrapTask(var1));
   }

   @Override
   public final <T> Future<T> submit(Runnable var1, T var2) {
      return this.field1.submit(this.wrapTask(var1), (T)var2);
   }

   @Override
   public final <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> var1) {
      return this.field1.invokeAll(this.method1(var1));
   }

   @Override
   public final <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> var1, long var2, TimeUnit var4) {
      return this.field1.invokeAll(this.method1(var1), var2, var4);
   }

   @Override
   public final <T> T invokeAny(Collection<? extends Callable<T>> var1) {
      return this.field1.invokeAny(this.method1(var1));
   }

   @Override
   public final <T> T invokeAny(Collection<? extends Callable<T>> var1, long var2, TimeUnit var4) {
      return this.field1.invokeAny(this.method1(var1), var2, var4);
   }

   @Override
   public final void shutdown() {
      this.field1.shutdown();
   }

   @Override
   public final List<Runnable> shutdownNow() {
      return this.field1.shutdownNow();
   }

   @Override
   public final boolean isShutdown() {
      return this.field1.isShutdown();
   }

   @Override
   public final boolean isTerminated() {
      return this.field1.isTerminated();
   }

   @Override
   public final boolean awaitTermination(long var1, TimeUnit var3) {
      return this.field1.awaitTermination(var1, var3);
   }
}
