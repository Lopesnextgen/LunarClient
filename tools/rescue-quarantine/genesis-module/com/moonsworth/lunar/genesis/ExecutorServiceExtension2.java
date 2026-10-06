package com.moonsworth.lunar.genesis;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.google.common.util.concurrent.ListeningExecutorService;

@Annotation3
public interface ExecutorServiceExtension2 extends ListeningExecutorService, ScheduledExecutorService {
   FutureExtension3<?> method1(Runnable var1, long var2, TimeUnit var4);

   default FutureExtension3<?> method2(Runnable var1, Duration var2) {
      return this.method1(var1, MixinHelper7_7.toNanosSaturated(var2), TimeUnit.NANOSECONDS);
   }

   <V> FutureExtension3<V> method3(Callable<V> var1, long var2, TimeUnit var4);

   default <V> FutureExtension3<V> method4(Callable<V> var1, Duration var2) {
      return this.method3(var1, MixinHelper7_7.toNanosSaturated(var2), TimeUnit.NANOSECONDS);
   }

   FutureExtension3<?> method5(Runnable var1, long var2, long var4, TimeUnit var6);

   default FutureExtension3<?> method6(Runnable var1, Duration var2, Duration var3) {
      return this.method5(var1, MixinHelper7_7.toNanosSaturated(var2), MixinHelper7_7.toNanosSaturated(var3), TimeUnit.NANOSECONDS);
   }

   FutureExtension3<?> method7(Runnable var1, long var2, long var4, TimeUnit var6);

   default FutureExtension3<?> method8(Runnable var1, Duration var2, Duration var3) {
      return this.method7(var1, MixinHelper7_7.toNanosSaturated(var2), MixinHelper7_7.toNanosSaturated(var3), TimeUnit.NANOSECONDS);
   }
}
