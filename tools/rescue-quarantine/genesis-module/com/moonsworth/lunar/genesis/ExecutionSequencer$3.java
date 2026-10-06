package com.moonsworth.lunar.genesis;

import java.util.concurrent.Executor;
import com.google.common.util.concurrent.ListenableFuture;

class ExecutionSequencer$3 implements Executor {
   ExecutionSequencer$3(MixinHelper3_12 mixinhelper3_121, ListenableFuture futureextension2, Executor executor3) {
      this.field3 = mixinhelper3_121;
      this.field1 = futureextension2;
      this.field2 = executor3;
   }

   @Override
   public void execute(Runnable runnable1) {
      this.field1.addListener(runnable1, this.field2);
   }
}
