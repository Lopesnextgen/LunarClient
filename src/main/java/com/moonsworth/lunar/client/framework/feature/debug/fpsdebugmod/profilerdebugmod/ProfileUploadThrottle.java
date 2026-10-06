package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod;

import java.nio.ByteBuffer;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.LockSupport;

final class ProfileUploadThrottle implements Subscription {
   private final Subscriber<? super ByteBuffer> field1;
   private final byte[] field2;
   private final long field3;
   private final int field4;
   private final AtomicLong field5 = new AtomicLong();
   private final AtomicBoolean field6 = new AtomicBoolean();
   private volatile boolean cancelled;
   private int offset;

   ProfileUploadThrottle(Subscriber<? super ByteBuffer> subscriber1, byte[] items2, long number3, int number5) {
      this.field1 = subscriber1;
      this.field2 = items2;
      this.field3 = number3;
      this.field4 = number5;
   }

   @Override
   public void request(long number1) {
      if (number1 > 0L && !this.cancelled) {
         this.field5.addAndGet(number1);
         if (this.field6.compareAndSet(false, true)) {
            Thread thread3 = new Thread(this::run, "ProfileUpload-Throttle");
            thread3.setDaemon(true);
            thread3.start();
         }
      }
   }

   @Override
   public void cancel() {
      this.cancelled = true;
   }

   private void run() {
      try {
         long number1 = Math.round(1.0E9 * this.field4 / this.field3);
         long number3 = System.nanoTime();

         while (!this.cancelled && this.offset < this.field2.length) {
            while (this.field5.get() == 0L) {
               if (this.cancelled) {
                  return;
               }

               LockSupport.parkNanos(1000000L);
            }

            int number5 = Math.min(this.field4, this.field2.length - this.offset);
            ByteBuffer buffer6 = ByteBuffer.wrap(this.field2, this.offset, number5);
            this.offset += number5;
            this.field5.decrementAndGet();
            this.field1.onNext(buffer6);
            number3 += number1;
            long number7 = number3 - System.nanoTime();
            if (number7 > 0L) {
               LockSupport.parkNanos(number7);
            }
         }

         if (!this.cancelled) {
            this.field1.onComplete();
         }
      } catch (Throwable exception9) {
         if (!this.cancelled) {
            this.field1.onError(exception9);
         }
      }
   }
}
