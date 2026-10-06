package com.moonsworth.lunar.genesis;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.util.concurrent.AbstractListeningExecutorService;
import com.google.common.base.Preconditions;

@GwtIncompatible
class MoreExecutors$ListeningDecorator extends AbstractListeningExecutorService {
   private final ExecutorService field1;

   MoreExecutors$ListeningDecorator(ExecutorService executorservice1) {
      this.field1 = (ExecutorService)Preconditions.checkNotNull(executorservice1);
   }

   @Override
   public final boolean awaitTermination(long number1, TimeUnit timeunit3) {
      return this.field1.awaitTermination(number1, timeunit3);
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
   public final void shutdown() {
      this.field1.shutdown();
   }

   @Override
   public final List<Runnable> shutdownNow() {
      return this.field1.shutdownNow();
   }

   @Override
   public final void execute(Runnable runnable1) {
      this.field1.execute(runnable1);
   }
}
