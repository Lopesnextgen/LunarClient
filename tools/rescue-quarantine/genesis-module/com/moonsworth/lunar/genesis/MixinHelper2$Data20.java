package com.moonsworth.lunar.genesis;

import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.base.Preconditions;

@Annotation3
final class MixinHelper2$Data20 extends MixinHelper2$Data19 implements ExecutorServiceExtension2 {
   final ScheduledExecutorService field2;

   MixinHelper2$Data20(ScheduledExecutorService var1) {
      super(var1);
      this.field2 = Preconditions.checkNotNull(var1);
   }

   @Override
   public FutureExtension3<?> method1(Runnable var1, long var2, TimeUnit var4) {
      MixinHelper35 var5 = MixinHelper35.method3(var1, null);
      ScheduledFuture var6 = this.field2.schedule(var5, var2, var4);
      return new MixinHelper2$Data20.Data2(var5, var6);
   }

   @Override
   public <V> FutureExtension3<V> method3(Callable<V> var1, long var2, TimeUnit var4) {
      MixinHelper35 var5 = MixinHelper35.method2(var1);
      ScheduledFuture var6 = this.field2.schedule(var5, var2, var4);
      return new MixinHelper2$Data20.Data2<>(var5, var6);
   }

   @Override
   public FutureExtension3<?> method5(Runnable var1, long var2, long var4, TimeUnit var6) {
      MixinHelper2$Data20.Data var7 = new MixinHelper2$Data20.Data(var1);
      ScheduledFuture var8 = this.field2.scheduleAtFixedRate(var7, var2, var4, var6);
      return new MixinHelper2$Data20.Data2<>(var7, var8);
   }

   @Override
   public FutureExtension3<?> method7(Runnable var1, long var2, long var4, TimeUnit var6) {
      MixinHelper2$Data20.Data var7 = new MixinHelper2$Data20.Data(var1);
      ScheduledFuture var8 = this.field2.scheduleWithFixedDelay(var7, var2, var4, var6);
      return new MixinHelper2$Data20.Data2<>(var7, var8);
   }

   @Annotation3
   private static final class Data extends AbstractFuture.Data5<Void> implements Runnable {
      private final Runnable field8;

      public Data(Runnable var1) {
         this.field8 = Preconditions.checkNotNull(var1);
      }

      @Override
      public void run() {
         try {
            this.field8.run();
         } catch (Throwable var2) {
            this.setException(var2);
            throw MixinHelper13_2.propagate(var2);
         }
      }
   }

   private static final class Data2<V> extends MixinHelper3122$Data4<V> implements FutureExtension3<V> {
      private final ScheduledFuture<?> field2;

      public Data2(ListenableFuture<V> var1, ScheduledFuture<?> var2) {
         super(var1);
         this.field2 = var2;
      }

      @Override
      public boolean cancel(boolean var1) {
         boolean var2 = super.cancel(var1);
         if (var2) {
            this.field2.cancel(var1);
         }

         return var2;
      }

      @Override
      public long getDelay(TimeUnit var1) {
         return this.field2.getDelay(var1);
      }

      public int compareTo(Delayed var1) {
         return this.field2.compareTo(var1);
      }
   }
}
