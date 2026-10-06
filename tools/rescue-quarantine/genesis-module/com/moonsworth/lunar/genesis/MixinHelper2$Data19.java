package com.moonsworth.lunar.genesis;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import com.google.common.util.concurrent.AbstractListeningExecutorService;
import com.google.common.base.Preconditions;

@Annotation3
class MixinHelper2$Data19 extends AbstractListeningExecutorService {
   private final ExecutorService field1;

   MixinHelper2$Data19(ExecutorService var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   @Override
   public final boolean awaitTermination(long var1, TimeUnit var3) {
      return this.field1.awaitTermination(var1, var3);
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
   public final void execute(Runnable var1) {
      this.field1.execute(var1);
   }
}
