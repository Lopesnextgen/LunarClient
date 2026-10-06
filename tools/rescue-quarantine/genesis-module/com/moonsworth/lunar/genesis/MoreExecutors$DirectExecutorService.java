package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.util.concurrent.AbstractListeningExecutorService;

@GwtIncompatible
final class MoreExecutors$DirectExecutorService extends AbstractListeningExecutorService {
   private final Object field1 = new Object();
   @GuardedBy("lock")
   private int runningTasks = 0;
   @GuardedBy("lock")
   private boolean shutdown = false;

   private MoreExecutors$DirectExecutorService() {
   }

   public void execute(Runnable runnable1) {
      this.startTask();

      try {
         runnable1.run();
      } finally {
         this.endTask();
      }
   }

   public boolean isShutdown() {
      synchronized (this.field1) {
         return this.shutdown;
      }
   }

   public void shutdown() {
      synchronized (this.field1) {
         this.shutdown = true;
         if (this.runningTasks == 0) {
            this.field1.notifyAll();
         }
      }
   }

   public List<Runnable> shutdownNow() {
      this.shutdown();
      return Collections.emptyList();
   }

   public boolean isTerminated() {
      synchronized (this.field1) {
         return this.shutdown && this.runningTasks == 0;
      }
   }

   public boolean awaitTermination(long number1, TimeUnit timeunit3) {
      long number4 = timeunit3.toNanos(number1);
      synchronized (this.field1) {
         while (!this.shutdown || this.runningTasks != 0) {
            if (number4 <= 0L) {
               return false;
            }

            long number7 = System.nanoTime();
            TimeUnit.NANOSECONDS.timedWait(this.field1, number4);
            number4 -= System.nanoTime() - number7;
         }

         return true;
      }
   }

   private void startTask() {
      synchronized (this.field1) {
         if (this.shutdown) {
            throw new RejectedExecutionException("Executor already shutdown");
         }

         this.runningTasks++;
      }
   }

   private void endTask() {
      synchronized (this.field1) {
         int number2 = --this.runningTasks;
         if (number2 == 0) {
            this.field1.notifyAll();
         }
      }
   }
}
