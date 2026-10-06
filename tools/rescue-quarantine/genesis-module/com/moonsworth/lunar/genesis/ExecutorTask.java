package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.common.base.Preconditions;

@Annotation3
final class ExecutorTask implements Executor {
   private static final Logger field1 = Logger.getLogger(ExecutorTask.class.getName());
   private final Executor field2;
   @GuardedBy("queue")
   private final Deque<Runnable> field3 = new ArrayDeque<>();
   @GuardedBy("queue")
   private ExecutorTask.Type field4 = ExecutorTask.Type.IDLE;
   @GuardedBy("queue")
   private long workerRunCount = 0L;
   private final ExecutorTask.Data field5 = new ExecutorTask.Data();

   ExecutorTask(Executor var1) {
      this.field2 = Preconditions.checkNotNull(var1);
   }

   @Override
   public void execute(final Runnable var1) {
      Preconditions.checkNotNull(var1);
      Runnable var2;
      long var3;
      synchronized (this.field3) {
         if (this.field4 == ExecutorTask.Type.RUNNING || this.field4 == ExecutorTask.Type.QUEUED) {
            this.field3.add(var1);
            return;
         }

         var3 = this.workerRunCount;
         var2 = new Runnable() {
            @Override
            public void run() {
               var1.run();
            }
         };
         this.field3.add(var2);
         this.field4 = ExecutorTask.Type.QUEUING;
      }

      try {
         this.field2.execute(this.field5);
      } catch (RuntimeException | Error var12) {
         RuntimeException var14 = var12;
         synchronized (this.field3) {
            boolean var7 = (this.field4 == ExecutorTask.Type.IDLE || this.field4 == ExecutorTask.Type.QUEUING) && this.field3.removeLastOccurrence(var2);
            if (var14 instanceof RejectedExecutionException && !var7) {
               return;
            }

            throw var14;
         }
      }

      boolean var15 = this.field4 != ExecutorTask.Type.QUEUING;
      if (!var15) {
         synchronized (this.field3) {
            if (this.workerRunCount == var3 && this.field4 == ExecutorTask.Type.QUEUING) {
               this.field4 = ExecutorTask.Type.QUEUED;
            }
         }
      }
   }

   @Override
   public String toString() {
      return "SequentialExecutor@" + System.identityHashCode(this) + "{" + this.field2 + "}";
   }

   private final class Data implements Runnable {
      private Data() {
      }

      @Override
      public void run() {
         try {
            this.workOnQueue();
         } catch (Error var5) {
            synchronized (ExecutorTask.this.field3) {
               ExecutorTask.this.field4 = ExecutorTask.Type.IDLE;
            }

            throw var5;
         }
      }

      private void workOnQueue() {
         boolean var1 = false;
         boolean var2 = false;

         while (true) {
            try {
               Runnable var3;
               synchronized (ExecutorTask.this.field3) {
                  if (!var2) {
                     if (ExecutorTask.this.field4 == ExecutorTask.Type.RUNNING) {
                        return;
                     }

                     ExecutorTask.this.workerRunCount++;
                     ExecutorTask.this.field4 = ExecutorTask.Type.RUNNING;
                     var2 = true;
                  }

                  var3 = ExecutorTask.this.field3.poll();
                  if (var3 == null) {
                     ExecutorTask.this.field4 = ExecutorTask.Type.IDLE;
                     return;
                  }
               }

               var1 |= Thread.interrupted();

               try {
                  var3.run();
               } catch (RuntimeException var10) {
                  ExecutorTask.field1.log(Level.SEVERE, "Exception while executing runnable " + var3, var10);
               }
            } finally {
               if (var1) {
                  Thread.currentThread().interrupt();
               }
            }
         }
      }
   }

   enum Type {
      IDLE,
      QUEUING,
      QUEUED,
      RUNNING;
   }
}
