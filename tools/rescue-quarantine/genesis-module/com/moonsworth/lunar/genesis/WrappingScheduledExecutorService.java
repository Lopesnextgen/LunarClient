package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import com.google.common.annotations.GwtIncompatible;

@CanIgnoreReturnValue
@GwtIncompatible
abstract class WrappingScheduledExecutorService extends WrappingExecutorService implements ScheduledExecutorService {
   final ScheduledExecutorService field2;

   protected WrappingScheduledExecutorService(ScheduledExecutorService scheduledexecutorservice1) {
      super(scheduledexecutorservice1);
      this.field2 = scheduledexecutorservice1;
   }

   @Override
   public final ScheduledFuture<?> schedule(Runnable runnable1, long number2, TimeUnit timeunit4) {
      return this.field2.schedule(this.wrapTask(runnable1), number2, timeunit4);
   }

   @Override
   public final <V> ScheduledFuture<V> schedule(Callable<V> callable1, long number2, TimeUnit timeunit4) {
      return this.field2.schedule(this.wrapTask(callable1), number2, timeunit4);
   }

   @Override
   public final ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable1, long number2, long number4, TimeUnit timeunit6) {
      return this.field2.scheduleAtFixedRate(this.wrapTask(runnable1), number2, number4, timeunit6);
   }

   @Override
   public final ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable1, long number2, long number4, TimeUnit timeunit6) {
      return this.field2.scheduleWithFixedDelay(this.wrapTask(runnable1), number2, number4, timeunit6);
   }
}
