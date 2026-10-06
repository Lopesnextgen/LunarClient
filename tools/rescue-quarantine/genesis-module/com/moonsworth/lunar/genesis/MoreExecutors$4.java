package com.moonsworth.lunar.genesis;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import com.google.common.util.concurrent.Callables;
import com.google.common.base.Supplier;

final class MoreExecutors$4 extends WrappingScheduledExecutorService {
   MoreExecutors$4(ScheduledExecutorService scheduledexecutorservice1, Supplier supplierextension2) {
      super(scheduledexecutorservice1);
      this.field3 = supplierextension2;
   }

   protected <T> Callable<T> wrapTask(Callable<T> callable1) {
      return Callables.method2(callable1, this.field3);
   }

   protected Runnable wrapTask(Runnable runnable1) {
      return Callables.method3(runnable1, this.field3);
   }
}
