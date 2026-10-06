package com.moonsworth.lunar.genesis;

import java.util.concurrent.Callable;

class WrappingExecutorService$1 implements Runnable {
   WrappingExecutorService$1(WrappingExecutorService executorservicetask1, Callable callable2) {
      this.field2 = executorservicetask1;
      this.field1 = callable2;
   }

   @Override
   public void run() {
      try {
         this.field1.call();
      } catch (Exception exception2) {
         MixinHelper13_2.throwIfUnchecked(exception2);
         throw new RuntimeException(exception2);
      }
   }
}
