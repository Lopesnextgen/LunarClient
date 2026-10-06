package com.moonsworth.lunar.genesis;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import com.google.common.util.concurrent.Callables;
import com.google.common.base.Supplier;

final class MoreExecutors$3 extends WrappingExecutorService {
   MoreExecutors$3(ExecutorService executorservice1, Supplier supplierextension2) {
      super(executorservice1);
      this.field2 = supplierextension2;
   }

   protected <T> Callable<T> wrapTask(Callable<T> callable1) {
      return Callables.method2(callable1, this.field2);
   }

   protected Runnable wrapTask(Runnable runnable1) {
      return Callables.method3(runnable1, this.field2);
   }
}
