package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.common.util.concurrent.Service;
import com.google.common.util.concurrent.AbstractService;
import com.google.common.util.concurrent.MoreExecutors;

@Annotation3
public abstract class MixinHelper232 implements Service {
   private static final Logger field1 = Logger.getLogger(MixinHelper232.class.getName());
   private final Service field2 = new AbstractService() {
      @Override
      protected final void doStart() {
         Executor var1 = MoreExecutors.method7(MixinHelper232.this.executor(), new SupplierExtension<String>() {
            public String get() {
               return MixinHelper232.this.serviceName();
            }
         });
         var1.execute(new Runnable() {
            @Override
            public void run() {
               try {
                  MixinHelper232.this.startUp();
                  HIRRIIRHCOCIRIHOHOIHIHROHCCRIO();
                  if (isRunning()) {
                     try {
                        MixinHelper232.this.run();
                     } catch (Throwable var4) {
                        try {
                           MixinHelper232.this.shutDown();
                        } catch (Exception var3) {
                           MixinHelper232.field1.log(Level.WARNING, "Error while attempting to shut down the service after failure.", var3);
                        }

                        OHOOORICRHIIIIRHCICICOCHROICRC(var4);
                        return;
                     }
                  }

                  MixinHelper232.this.shutDown();
                  OIRRHHROCORCRCOCICIOHCHOHCCOHH();
               } catch (Throwable var5) {
                  OHOOORICRHIIIIRHCICICOCHROICRC(var5);
               }
            }
         });
      }

      @Override
      protected void doStop() {
         MixinHelper232.this.triggerShutdown();
      }

      @Override
      public String toString() {
         return MixinHelper232.this.toString();
      }
   };

   protected MixinHelper232() {
   }

   protected void startUp() {
   }

   protected abstract void run();

   protected void shutDown() {
   }

   @Annotation2
   protected void triggerShutdown() {
   }

   protected Executor executor() {
      return new Executor() {
         @Override
         public void execute(Runnable var1) {
            MoreExecutors.newThread(MixinHelper232.this.serviceName(), var1).start();
         }
      };
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

   protected String serviceName() {
      return this.getClass().getSimpleName();
   }
}
