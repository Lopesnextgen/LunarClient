package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;

final class MixinHelper5$Data22<L> implements Runnable {
   final L field1;
   final Executor field2;
   @GuardedBy("this")
   final Queue<MixinHelper5$Extension<L>> field3 = Queues.newArrayDeque();
   @GuardedBy("this")
   final Queue<Object> field4 = Queues.newArrayDeque();
   @GuardedBy("this")
   boolean isThreadScheduled;

   MixinHelper5$Data22(L var1, Executor var2) {
      this.field1 = Preconditions.checkNotNull((L)var1);
      this.field2 = Preconditions.checkNotNull(var2);
   }

   synchronized void method1(MixinHelper5$Extension<L> var1, Object var2) {
      this.field3.add(var1);
      this.field4.add(var2);
   }

   void dispatch() {
      boolean var1 = false;
      synchronized (this) {
         if (!this.isThreadScheduled) {
            this.isThreadScheduled = true;
            var1 = true;
         }
      }

      if (var1) {
         try {
            this.field2.execute(this);
         } catch (RuntimeException var6) {
            synchronized (this) {
               this.isThreadScheduled = false;
            }

            MixinHelper5_9.access$000().log(Level.SEVERE, "Exception while running callbacks for " + this.field1 + " on " + this.field2, var6);
            throw var6;
         }
      }
   }

   @Override
   public void run() {
      boolean var1 = true;

      try {
         while (true) {
            MixinHelper5$Extension var2;
            Object var3;
            synchronized (this) {
               Preconditions.checkState(this.isThreadScheduled);
               var2 = this.field3.poll();
               var3 = this.field4.poll();
               if (var2 == null) {
                  this.isThreadScheduled = false;
                  var1 = false;
                  return;
               }
            }

            try {
               var2.call(this.field1);
            } catch (RuntimeException var16) {
               MixinHelper5_9.access$000().log(Level.SEVERE, "Exception while executing callback: " + this.field1 + " " + var3, var16);
            }
         }
      } finally {
         if (var1) {
            synchronized (this) {
               this.isThreadScheduled = false;
            }
         }
      }
   }
}
