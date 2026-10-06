package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableList;
import com.google.common.base.Preconditions;

@CanIgnoreReturnValue
@GwtIncompatible
abstract class WrappingExecutorService implements ExecutorService {
   private final ExecutorService field1;

   protected WrappingExecutorService(ExecutorService executorservice1) {
      this.field1 = Preconditions.checkNotNull(executorservice1);
   }

   protected abstract <T> Callable<T> wrapTask(Callable<T> callable1);

   protected Runnable wrapTask(Runnable runnable1) {
      Callable callable2 = this.wrapTask(Executors.callable(runnable1, null));
      return new WrappingExecutorService$1(this, callable2);
   }

   private <T> ImmutableList<Callable<T>> method1(Collection<? extends Callable<T>> list1) {
      AbstractCollectionIterator3$Data2 abstractcollectioniterator3$data22 = ImmutableList.method30();

      for (Callable callable4 : list1) {
         abstractcollectioniterator3$data22.method2(this.wrapTask(callable4));
      }

      return abstractcollectioniterator3$data22.method6();
   }

   @Override
   public final void execute(Runnable runnable1) {
      this.field1.execute(this.wrapTask(runnable1));
   }

   @Override
   public final <T> Future<T> submit(Callable<T> callable1) {
      return this.field1.submit(this.wrapTask(Preconditions.checkNotNull(callable1)));
   }

   @Override
   public final Future<?> submit(Runnable runnable1) {
      return this.field1.submit(this.wrapTask(runnable1));
   }

   @Override
   public final <T> Future<T> submit(Runnable runnable1, T value2) {
      return this.field1.submit(this.wrapTask(runnable1), (T)value2);
   }

   @Override
   public final <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> list1) {
      return this.field1.invokeAll(this.<T>method1(list1));
   }

   @Override
   public final <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> list1, long number2, TimeUnit timeunit4) {
      return this.field1.invokeAll(this.<T>method1(list1), number2, timeunit4);
   }

   @Override
   public final <T> T invokeAny(Collection<? extends Callable<T>> list1) {
      return this.field1.invokeAny(this.<T>method1(list1));
   }

   @Override
   public final <T> T invokeAny(Collection<? extends Callable<T>> list1, long number2, TimeUnit timeunit4) {
      return this.field1.invokeAny(this.<T>method1(list1), number2, timeunit4);
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
   public final boolean awaitTermination(long number1, TimeUnit timeunit3) {
      return this.field1.awaitTermination(number1, timeunit3);
   }
}
