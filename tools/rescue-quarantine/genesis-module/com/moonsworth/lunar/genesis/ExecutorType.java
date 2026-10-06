package com.moonsworth.lunar.genesis;

import java.util.concurrent.Executor;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
enum ExecutorType implements Executor {
   INSTANCE;

   ExecutorType() {
   }

   @Override
   public void execute(Runnable runnable1) {
      runnable1.run();
   }

   @Override
   public String toString() {
      return "MoreExecutors.directExecutor()";
   }
}
