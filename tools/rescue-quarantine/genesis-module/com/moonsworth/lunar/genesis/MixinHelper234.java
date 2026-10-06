package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.util.concurrent.Service;
import com.google.common.util.concurrent.AbstractService;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;

@Annotation3
public abstract class MixinHelper234 implements Service {
   private static final Logger field1 = Logger.getLogger(MixinHelper234.class.getName());
   private final AbstractService field2 = new MixinHelper234.Data4();

   protected MixinHelper234() {
   }

   protected abstract void runOneIteration();

   protected void startUp() {
   }

   protected void shutDown() {
   }

   protected abstract MixinHelper234.Data3 method4();

   protected ScheduledExecutorService executor() {
      class Data2 implements ThreadFactory {
         @Override
         public Thread newThread(Runnable var1) {
            return MoreExecutors.newThread(MixinHelper234.this.serviceName(), var1);
         }
      }

      final ScheduledExecutorService var1 = Executors.newSingleThreadScheduledExecutor(new Data2());
      this.method4(new MixinHelper23$Data11() {
         @Override
         public void method2(MixinHelper23$Type var1x) {
            var1.shutdown();
         }

         @Override
         public void method3(MixinHelper23$Type var1x, Throwable var2) {
            var1.shutdown();
         }
      }, MoreExecutors.directExecutor());
      return var1;
   }

   protected String serviceName() {
      return this.getClass().getSimpleName();
   }

   @Override
   public String toString() {
      return this.serviceName() + " [" + this.method2() + "]";
   }

   @Override
   public final boolean isRunning() {
      return this.field2.isRunning();
   }

   @Override
   public final MixinHelper23$Type method2() {
      return this.field2.method2();
   }

   @Override
   public final void method4(MixinHelper23$Data11 var1, Executor var2) {
      this.field2.method4(var1, var2);
   }

   @Override
   public final Throwable failureCause() {
      return this.field2.failureCause();
   }

   @CanIgnoreReturnValue
   @Override
   public final Service method1() {
      this.field2.method1();
      return this;
   }

   @CanIgnoreReturnValue
   @Override
   public final Service method3() {
      this.field2.method3();
      return this;
   }

   @Override
   public final void awaitRunning() {
      this.field2.awaitRunning();
   }

   @Override
   public final void awaitRunning(Duration var1) {
      Service.super.awaitRunning(var1);
   }

   @Override
   public final void awaitRunning(long var1, TimeUnit var3) {
      this.field2.awaitRunning(var1, var3);
   }

   @Override
   public final void awaitTerminated() {
      this.field2.awaitTerminated();
   }

   @Override
   public final void awaitTerminated(Duration var1) {
      Service.super.awaitTerminated(var1);
   }

   @Override
   public final void awaitTerminated(long var1, TimeUnit var3) {
      this.field2.awaitTerminated(var1, var3);
   }

   public abstract static class Data extends MixinHelper234.Data3 {
      @Override
      final Future<?> method5(AbstractService var1, ScheduledExecutorService var2, Runnable var3) {
         MixinHelper234.Data.Data2 var4 = new MixinHelper234.Data.Data2(var1, var2, var3);
         var4.reschedule();
         return var4;
      }

      protected abstract MixinHelper234.Data.Data method2();

      protected static final class Data {
         private final long field1;
         private final TimeUnit field2;

         public Data(long var1, TimeUnit var3) {
            this.field1 = var1;
            this.field2 = Preconditions.checkNotNull(var3);
         }
      }

      private class Data2 extends MixinHelper312_2<Void> implements Callable<Void> {
         private final Runnable field1;
         private final ScheduledExecutorService field2;
         private final AbstractService field3;
         private final ReentrantLock field4 = new ReentrantLock();
         @GuardedBy("lock")
         private @Nullable Future<Void> currentFuture;

         Data2(AbstractService var2, ScheduledExecutorService var3, Runnable var4) {
            this.field1 = var4;
            this.field2 = var3;
            this.field3 = var2;
         }

         public Void call() {
            this.field1.run();
            this.reschedule();
            return null;
         }

         public void reschedule() {
            MixinHelper234.Data.Data var1;
            try {
               var1 = Data.this.method2();
            } catch (Throwable var8) {
               this.field3.method8(var8);
               return;
            }

            Throwable var2 = null;
            this.field4.lock();

            try {
               if (this.currentFuture == null || !this.currentFuture.isCancelled()) {
                  this.currentFuture = this.field2.schedule(this, var1.field1, var1.field2);
               }
            } catch (Throwable var9) {
               var2 = var9;
            } finally {
               this.field4.unlock();
            }

            if (var2 != null) {
               this.field3.method8(var2);
            }
         }

         @Override
         public boolean cancel(boolean var1) {
            this.field4.lock();

            try {
               return this.currentFuture.cancel(var1);
            } finally {
               this.field4.unlock();
            }
         }

         @Override
         public boolean isCancelled() {
            this.field4.lock();

            try {
               return this.currentFuture.isCancelled();
            } finally {
               this.field4.unlock();
            }
         }

         @Override
         protected Future<Void> delegate() {
            throw new UnsupportedOperationException("Only cancel and isCancelled is supported by this future");
         }
      }
   }

   public abstract static class Data3 {
      public static MixinHelper234.Data3 method1(Duration var0, Duration var1) {
         return method2(MixinHelper7_7.toNanosSaturated(var0), MixinHelper7_7.toNanosSaturated(var1), TimeUnit.NANOSECONDS);
      }

      public static MixinHelper234.Data3 method2(final long var0, final long var2, final TimeUnit var4) {
         Preconditions.checkNotNull(var4);
         Preconditions.checkArgument(var2 > 0L, "delay must be > 0, found %s", var2);
         return new MixinHelper234.Data3() {
            @Override
            public Future<?> method5(AbstractService var1, ScheduledExecutorService var2x, Runnable var3) {
               return var2x.scheduleWithFixedDelay(var3, var0, var2, var4);
            }
         };
      }

      public static MixinHelper234.Data3 method3(Duration var0, Duration var1) {
         return method4(MixinHelper7_7.toNanosSaturated(var0), MixinHelper7_7.toNanosSaturated(var1), TimeUnit.NANOSECONDS);
      }

      public static MixinHelper234.Data3 method4(final long var0, final long var2, final TimeUnit var4) {
         Preconditions.checkNotNull(var4);
         Preconditions.checkArgument(var2 > 0L, "period must be > 0, found %s", var2);
         return new MixinHelper234.Data3() {
            @Override
            public Future<?> method5(AbstractService var1, ScheduledExecutorService var2x, Runnable var3) {
               return var2x.scheduleAtFixedRate(var3, var0, var2, var4);
            }
         };
      }

      abstract Future<?> method5(AbstractService var1, ScheduledExecutorService var2, Runnable var3);

      private Data3() {
      }
   }

   private final class Data4 extends AbstractService {
      private volatile @Nullable Future<?> runningTask;
      private volatile @Nullable ScheduledExecutorService executorService;
      private final ReentrantLock field16 = new ReentrantLock();
      private final Runnable field17 = new MixinHelper234.Data4.Data();

      private Data4() {
      }

      @Override
      protected final void doStart() {
         this.executorService = MoreExecutors.method9(MixinHelper234.this.executor(), new SupplierExtension<String>() {
            public String get() {
               return MixinHelper234.this.serviceName() + " " + Data4.this.OHOHCIOHCIRCHRCCOHHHHCCHROCOOC();
            }
         });
         this.executorService.execute(new Runnable() {
            @Override
            public void run() {
               Data4.this.field16.lock();

               try {
                  MixinHelper234.this.startUp();
                  Data4.this.runningTask = MixinHelper234.this.method4().method5(MixinHelper234.this.field2, Data4.this.executorService, Data4.this.field17);
                  Data4.this.HIRRIIRHCOCIRIHOHOIHIHROHCCRIO();
               } catch (Throwable var5) {
                  Data4.this.OHOOORICRHIIIIRHCICICOCHROICRC(var5);
                  if (Data4.this.runningTask != null) {
                     Data4.this.runningTask.cancel(false);
                  }
               } finally {
                  Data4.this.field16.unlock();
               }
            }
         });
      }

      @Override
      protected final void doStop() {
         this.runningTask.cancel(false);
         this.executorService.execute(new Runnable() {
            @Override
            public void run() {
               try {
                  Data4.this.field16.lock();

                  label42: {
                     try {
                        if (Data4.this.OHOHCIOHCIRCHRCCOHHHHCCHROCOOC() == MixinHelper23$Type.STOPPING) {
                           MixinHelper234.this.shutDown();
                           break label42;
                        }
                     } finally {
                        Data4.this.field16.unlock();
                     }

                     return;
                  }

                  Data4.this.OIRRHHROCORCRCOCICIOHCHOHCCOHH();
               } catch (Throwable var5) {
                  Data4.this.OHOOORICRHIIIIRHCICICOCHROICRC(var5);
               }
            }
         });
      }

      @Override
      public String toString() {
         return MixinHelper234.this.toString();
      }

      class Data implements Runnable {
         @Override
         public void run() {
            Data4.this.field16.lock();

            try {
               if (!Data4.this.runningTask.isCancelled()) {
                  MixinHelper234.this.runOneIteration();
                  return;
               }
            } catch (Throwable var8) {
               try {
                  MixinHelper234.this.shutDown();
               } catch (Exception var7) {
                  MixinHelper234.field1.log(Level.WARNING, "Error while attempting to shut down the service after failure.", var7);
               }

               Data4.this.OHOOORICRHIIIIRHCICICOCHROICRC(var8);
               Data4.this.runningTask.cancel(false);
               return;
            } finally {
               Data4.this.field16.unlock();
            }
         }
      }
   }
}
