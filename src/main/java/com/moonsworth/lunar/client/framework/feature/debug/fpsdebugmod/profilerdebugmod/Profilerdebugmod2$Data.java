package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod;

import java.nio.ByteBuffer;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.LockSupport;

final class Profilerdebugmod2$Data implements Subscription {
   private final Subscriber<? super ByteBuffer> field1;
   private final byte[] field2;
   private final long field3;
   private final int field4;
   private final AtomicLong field5 = new AtomicLong();
   private final AtomicBoolean field6 = new AtomicBoolean();
   private volatile boolean cancelled;
   private int offset;

   Profilerdebugmod2$Data(Subscriber<? super ByteBuffer> var1, byte[] items, long var3, int var5) {
      this.field1 = var1;
      this.field2 = items;
      this.field3 = var3;
      this.field4 = var5;
   }

   @Override
   public void request(long var1) {
      if (var1 > 0L && !this.cancelled) {
         this.field5.addAndGet(var1);
         if (this.field6.compareAndSet(false, true)) {
            Thread var3 = new Thread(this::run, "ProfileUpload-Throttle");
            var3.setDaemon(true);
            var3.start();
         }
      }
   }

   @Override
   public void cancel() {
      this.cancelled = true;
   }

   private void run() {
      try {
         long var1 = Math.round(1.0E9 * this.field4 / this.field3);
         long var3 = System.nanoTime();

         while (!this.cancelled && this.offset < this.field2.length) {
            while (this.field5.get() == 0L) {
               if (this.cancelled) {
                  return;
               }

               LockSupport.parkNanos(1000000L);
            }

            int var5 = Math.min(this.field4, this.field2.length - this.offset);
            ByteBuffer var6 = ByteBuffer.wrap(this.field2, this.offset, var5);
            this.offset += var5;
            this.field5.decrementAndGet();
            this.field1.onNext(var6);
            var3 += var1;
            long var7 = var3 - System.nanoTime();
            if (var7 > 0L) {
               LockSupport.parkNanos(var7);
            }
         }

         if (!this.cancelled) {
            this.field1.onComplete();
         }
      } catch (Throwable var9) {
         if (!this.cancelled) {
            this.field1.onError(var9);
         }
      }
   }
}
