package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@CanIgnoreReturnValue
@Annotation3
abstract class ExecutorServiceTask2 extends ExecutorServiceTask implements ScheduledExecutorService {
   final ScheduledExecutorService field2;

   protected ExecutorServiceTask2(ScheduledExecutorService var1) {
      super(var1);
      this.field2 = var1;
   }

   @Override
   public final ScheduledFuture<?> schedule(Runnable var1, long var2, TimeUnit var4) {
      return this.field2.schedule(this.wrapTask(var1), var2, var4);
   }

   @Override
   public final <V> ScheduledFuture<V> schedule(Callable<V> var1, long var2, TimeUnit var4) {
      return this.field2.schedule(this.wrapTask(var1), var2, var4);
   }

   @Override
   public final ScheduledFuture<?> scheduleAtFixedRate(Runnable var1, long var2, long var4, TimeUnit var6) {
      return this.field2.scheduleAtFixedRate(this.wrapTask(var1), var2, var4, var6);
   }

   @Override
   public final ScheduledFuture<?> scheduleWithFixedDelay(Runnable var1, long var2, long var4, TimeUnit var6) {
      return this.field2.scheduleWithFixedDelay(this.wrapTask(var1), var2, var4, var6);
   }
}
