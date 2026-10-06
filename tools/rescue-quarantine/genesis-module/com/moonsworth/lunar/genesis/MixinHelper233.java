package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import com.google.common.util.concurrent.AbstractService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.Service;

@Annotation3
public abstract class MixinHelper233 implements Service {
   private final SupplierExtension<String> field1 = new MixinHelper233.Data();
   private final Service field2 = new MixinHelper233.Data2();

   protected MixinHelper233() {
   }

   protected abstract void startUp();

   protected abstract void shutDown();

   protected Executor executor() {
      return new Executor() {
         @Override
         public void execute(Runnable var1) {
            MoreExecutors.newThread(MixinHelper233.this.field1.get(), var1).start();
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

   private final class Data implements SupplierExtension<String> {
      private Data() {
      }

      public String get() {
         return MixinHelper233.this.serviceName() + " " + MixinHelper233.this.method2();
      }
   }

   private final class Data2 extends AbstractService {
      private Data2() {
      }

      @Override
      protected final void doStart() {
         MoreExecutors.method7(MixinHelper233.this.executor(), MixinHelper233.this.field1).execute(new Runnable() {
            @Override
            public void run() {
               try {
                  MixinHelper233.this.startUp();
                  Data2.this.HIRRIIRHCOCIRIHOHOIHIHROHCCRIO();
               } catch (Throwable var2) {
                  Data2.this.OHOOORICRHIIIIRHCICICOCHROICRC(var2);
               }
            }
         });
      }

      @Override
      protected final void doStop() {
         MoreExecutors.method7(MixinHelper233.this.executor(), MixinHelper233.this.field1).execute(new Runnable() {
            @Override
            public void run() {
               try {
                  MixinHelper233.this.shutDown();
                  Data2.this.OIRRHHROCORCRCOCICIOHCHOHCCOHH();
               } catch (Throwable var2) {
                  Data2.this.OHOOORICRHIIIIRHCICICOCHROICRC(var2);
               }
            }
         });
      }

      @Override
      public String toString() {
         return MixinHelper233.this.toString();
      }
   }
}
