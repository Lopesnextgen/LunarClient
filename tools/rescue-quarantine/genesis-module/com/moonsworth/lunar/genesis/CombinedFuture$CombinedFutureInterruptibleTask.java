package com.moonsworth.lunar.genesis;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import com.google.common.base.Preconditions;

abstract class CombinedFuture$CombinedFutureInterruptibleTask<T> extends AtomicReferenceTask<T> {
   private final Executor field5;
   boolean thrownByExecute;

   CombinedFuture$CombinedFutureInterruptibleTask(MixinHelper3023 mixinhelper30231, Executor executor2) {
      this.field6 = mixinhelper30231;
      this.thrownByExecute = true;
      this.field5 = Preconditions.checkNotNull(executor2);
   }

   final boolean isDone() {
      return this.field6.isDone();
   }

   final void method2() {
      try {
         this.field5.execute(this);
      } catch (RejectedExecutionException rejectedexecutionexception2) {
         if (this.thrownByExecute) {
            this.field6.setException(rejectedexecutionexception2);
         }
      }
   }

   final void afterRanInterruptibly(T value1, Throwable exception2) {
      MixinHelper3023.method2(this.field6, null);
      if (exception2 != null) {
         if (exception2 instanceof ExecutionException) {
            this.field6.setException(exception2.getCause());
         } else if (exception2 instanceof CancellationException) {
            this.field6.cancel(false);
         } else {
            this.field6.setException(exception2);
         }
      } else {
         this.setValue((T)value1);
      }
   }

   abstract void setValue(T value1);
}
