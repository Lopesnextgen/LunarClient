package com.moonsworth.lunar.genesis;

import com.google.j2objc.annotations.ReflectionSupport;
import com.google.j2objc.annotations.ReflectionSupport.Level;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible(emulated = true)
@ReflectionSupport(Level.FULL)
abstract class AtomicReferenceTask<T> extends AtomicReference<Runnable> implements Runnable {
   private static final Runnable field1 = new AtomicReferenceTask.Data();
   private static final Runnable field2 = new AtomicReferenceTask.Data();
   private static final Runnable field3 = new AtomicReferenceTask.Data();
   private static final int field4 = 1000;

   @Override
   public final void run() {
      Thread var1 = Thread.currentThread();
      if (this.compareAndSet(null, var1)) {
         boolean var2 = !this.isDone();
         Object var3 = null;
         Throwable var4 = null;

         try {
            if (var2) {
               var3 = this.runInterruptibly();
            }
         } catch (Throwable var14) {
            var4 = var14;
         } finally {
            if (!this.compareAndSet(var1, field1)) {
               boolean var9 = false;
               int var10 = 0;

               for (Runnable var11 = this.get(); var11 == field2 || var11 == field3; var11 = this.get()) {
                  if (++var10 > 1000) {
                     if (var11 == field3 || this.compareAndSet(field2, field3)) {
                        var9 = Thread.interrupted() || var9;
                        LockSupport.park(this);
                     }
                  } else {
                     Thread.yield();
                  }
               }

               if (var9) {
                  var1.interrupt();
               }
            }

            if (var2) {
               this.afterRanInterruptibly((T)var3, var4);
            }
         }
      }
   }

   abstract boolean isDone();

   abstract T runInterruptibly();

   abstract void afterRanInterruptibly(@Nullable T var1, @Nullable Throwable var2);

   final void method1() {
      Runnable var1 = this.get();
      if (var1 instanceof Thread && this.compareAndSet(var1, field2)) {
         try {
            ((Thread)var1).interrupt();
         } finally {
            Runnable var4 = this.getAndSet(field1);
            if (var4 == field3) {
               LockSupport.unpark((Thread)var1);
            }
         }
      }
   }

   @Override
   public final String toString() {
      Runnable var1 = this.get();
      String var2;
      if (var1 == field1) {
         var2 = "running=[DONE]";
      } else if (var1 == field2) {
         var2 = "running=[INTERRUPTED]";
      } else if (var1 instanceof Thread) {
         var2 = "running=[RUNNING ON " + ((Thread)var1).getName() + "]";
      } else {
         var2 = "running=[NOT STARTED YET]";
      }

      return var2 + ", " + this.toPendingString();
   }

   abstract String toPendingString();

   static {
      Class<LockSupport> var0 = LockSupport.class;
   }

   private static final class Data implements Runnable {
      private Data() {
      }

      @Override
      public void run() {
      }
   }
}
