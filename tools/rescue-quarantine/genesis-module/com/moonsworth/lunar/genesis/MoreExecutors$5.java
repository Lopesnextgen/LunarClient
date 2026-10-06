package com.moonsworth.lunar.genesis;

import java.util.concurrent.Executor;
import com.google.common.util.concurrent.Callables;
import com.google.common.base.Supplier;

final class MoreExecutors$5 implements Executor {
   MoreExecutors$5(Executor executor1, Supplier supplierextension2) {
      this.field1 = executor1;
      this.field2 = supplierextension2;
   }

   @Override
   public void execute(Runnable runnable1) {
      this.field1.execute(Callables.method3(runnable1, this.field2));
   }
}
