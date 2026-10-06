package com.moonsworth.lunar.genesis;

import java.util.concurrent.BlockingQueue;
import com.google.common.util.concurrent.ListenableFuture;

final class MoreExecutors$1 implements Runnable {
   MoreExecutors$1(BlockingQueue blockingqueue1, ListenableFuture futureextension2) {
      this.field1 = blockingqueue1;
      this.field2 = futureextension2;
   }

   @Override
   public void run() {
      this.field1.add(this.field2);
   }
}
