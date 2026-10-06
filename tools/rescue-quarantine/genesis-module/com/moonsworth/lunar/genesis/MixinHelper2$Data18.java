package com.moonsworth.lunar.genesis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;

@Annotation3
@Annotation4
class MixinHelper2$Data18 {
   final ExecutorService method1(ThreadPoolExecutor var1, long var2, TimeUnit var4) {
      MoreExecutors.access$000(var1);
      ExecutorService var5 = Executors.unconfigurableExecutorService(var1);
      this.method5(var1, var2, var4);
      return var5;
   }

   final ExecutorService method2(ThreadPoolExecutor var1) {
      return this.method1(var1, 120L, TimeUnit.SECONDS);
   }

   final ScheduledExecutorService method3(ScheduledThreadPoolExecutor var1, long var2, TimeUnit var4) {
      MoreExecutors.access$000(var1);
      ScheduledExecutorService var5 = Executors.unconfigurableScheduledExecutorService(var1);
      this.method5(var1, var2, var4);
      return var5;
   }

   final ScheduledExecutorService method4(ScheduledThreadPoolExecutor var1) {
      return this.method3(var1, 120L, TimeUnit.SECONDS);
   }

   final void method5(final ExecutorService var1, final long var2, final TimeUnit var4) {
      Preconditions.checkNotNull(var1);
      Preconditions.checkNotNull(var4);
      this.addShutdownHook(MoreExecutors.newThread("DelayedShutdownHook-for-" + var1, new Runnable() {
         @Override
         public void run() {
            try {
               var1.shutdown();
               var1.awaitTermination(var2, var4);
            } catch (InterruptedException var2x) {
            }
         }
      }));
   }

   @Annotation4
   void addShutdownHook(Thread var1) {
      Runtime.getRuntime().addShutdownHook(var1);
   }
}
