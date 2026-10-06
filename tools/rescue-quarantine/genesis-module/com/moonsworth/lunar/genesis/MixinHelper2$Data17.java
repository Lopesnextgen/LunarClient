package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import com.google.common.util.concurrent.AbstractListeningExecutorService;

@Annotation3
final class MixinHelper2$Data17 extends AbstractListeningExecutorService {
   private final Object field1 = new Object();
   @GuardedBy("lock")
   private int runningTasks = 0;
   @GuardedBy("lock")
   private boolean shutdown = false;

   private MixinHelper2$Data17() {
   }

   @Override
   public void execute(Runnable var1) {
      this.startTask();

      try {
         var1.run();
      } finally {
         this.endTask();
      }
   }

   @Override
   public boolean isShutdown() {
      synchronized (this.field1) {
         return this.shutdown;
      }
   }

   @Override
   public void shutdown() {
      synchronized (this.field1) {
         this.shutdown = true;
         if (this.runningTasks == 0) {
            this.field1.notifyAll();
         }
      }
   }

   @Override
   public List<Runnable> shutdownNow() {
      this.shutdown();
      return Collections.emptyList();
   }

   @Override
   public boolean isTerminated() {
      synchronized (this.field1) {
         return this.shutdown && this.runningTasks == 0;
      }
   }

   @Override
   public boolean awaitTermination(long var1, TimeUnit var3) {
      long var4 = var3.toNanos(var1);
      synchronized (this.field1) {
         while (!this.shutdown || this.runningTasks != 0) {
            if (var4 <= 0L) {
               return false;
            }

            long var7 = System.nanoTime();
            TimeUnit.NANOSECONDS.timedWait(this.field1, var4);
            var4 -= System.nanoTime() - var7;
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
         int var2 = --this.runningTasks;
         if (var2 == 0) {
            this.field1.notifyAll();
         }
      }
   }
}
