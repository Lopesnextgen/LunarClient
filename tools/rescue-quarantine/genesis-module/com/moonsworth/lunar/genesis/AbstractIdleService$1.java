package com.moonsworth.lunar.genesis;

import java.util.concurrent.Executor;
import com.google.common.util.concurrent.MoreExecutors;

class AbstractIdleService$1 implements Executor {
   AbstractIdleService$1(MixinHelper233 mixinhelper2331) {
      this.field1 = mixinhelper2331;
   }

   @Override
   public void execute(Runnable runnable1) {
      MoreExecutors.newThread((String)MixinHelper233.method5(this.field1).get(), runnable1).start();
   }
}
