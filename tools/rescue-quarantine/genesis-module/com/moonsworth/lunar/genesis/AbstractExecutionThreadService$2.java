package com.moonsworth.lunar.genesis;

import java.util.concurrent.Executor;
import com.google.common.util.concurrent.MoreExecutors;

class AbstractExecutionThreadService$2 implements Executor {
   AbstractExecutionThreadService$2(MixinHelper232 mixinhelper2321) {
      this.field1 = mixinhelper2321;
   }

   @Override
   public void execute(Runnable runnable1) {
      MoreExecutors.newThread(this.field1.serviceName(), runnable1).start();
   }
}
