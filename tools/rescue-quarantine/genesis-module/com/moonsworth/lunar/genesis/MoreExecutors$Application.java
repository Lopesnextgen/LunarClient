package com.moonsworth.lunar.genesis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.eventbus.Subscribe;
import com.google.common.base.Preconditions;

@GwtIncompatible
@Subscribe
class MoreExecutors$Application {
   MoreExecutors$Application() {
   }

   final ExecutorService method1(ThreadPoolExecutor threadpoolexecutor1, long number2, TimeUnit timeunit4) {
      MixinHelper2_6.access$000(threadpoolexecutor1);
      ExecutorService executorservice5 = Executors.unconfigurableExecutorService(threadpoolexecutor1);
      this.method5(threadpoolexecutor1, number2, timeunit4);
      return executorservice5;
   }

   final ExecutorService method2(ThreadPoolExecutor threadpoolexecutor1) {
      return this.method1(threadpoolexecutor1, 120L, TimeUnit.SECONDS);
   }

   final ScheduledExecutorService method3(ScheduledThreadPoolExecutor scheduledthreadpoolexecutor1, long number2, TimeUnit timeunit4) {
      MixinHelper2_6.access$000(scheduledthreadpoolexecutor1);
      ScheduledExecutorService scheduledexecutorservice5 = Executors.unconfigurableScheduledExecutorService(scheduledthreadpoolexecutor1);
      this.method5(scheduledthreadpoolexecutor1, number2, timeunit4);
      return scheduledexecutorservice5;
   }

   final ScheduledExecutorService method4(ScheduledThreadPoolExecutor scheduledthreadpoolexecutor1) {
      return this.method3(scheduledthreadpoolexecutor1, 120L, TimeUnit.SECONDS);
   }

   final void method5(ExecutorService executorservice1, long number2, TimeUnit timeunit4) {
      Preconditions.checkNotNull(executorservice1);
      Preconditions.checkNotNull(timeunit4);
      this.addShutdownHook(MixinHelper2_6.newThread("DelayedShutdownHook-for-" + executorservice1, new Data18$1(this, executorservice1, number2, timeunit4)));
   }

   @Subscribe
   void addShutdownHook(Thread thread1) {
      Runtime.getRuntime().addShutdownHook(thread1);
   }
}
