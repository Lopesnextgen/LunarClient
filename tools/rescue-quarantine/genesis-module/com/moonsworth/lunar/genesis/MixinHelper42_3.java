package com.moonsworth.lunar.genesis;

import java.util.concurrent.TimeUnit;
import com.google.common.util.concurrent.RateLimiter;
import com.google.common.math.LongMath;

@Annotation3
abstract class MixinHelper42_3 extends RateLimiter {
   double storedPermits;
   double maxPermits;
   double stableIntervalMicros;
   private long nextFreeTicketMicros = 0L;

   private MixinHelper42_3(RateLimiter.Data16 var1) {
      super(var1);
   }

   @Override
   final void doSetRate(double var1, long var3) {
      this.resync(var3);
      double var5 = TimeUnit.SECONDS.toMicros(1L) / var1;
      this.stableIntervalMicros = var5;
      this.doSetRate(var1, var5);
   }

   abstract void doSetRate(double var1, double var3);

   @Override
   final double doGetRate() {
      return TimeUnit.SECONDS.toMicros(1L) / this.stableIntervalMicros;
   }

   @Override
   final long queryEarliestAvailable(long var1) {
      return this.nextFreeTicketMicros;
   }

   @Override
   final long reserveEarliestAvailable(int var1, long var2) {
      this.resync(var2);
      long var4 = this.nextFreeTicketMicros;
      double var6 = Math.min(var1, this.storedPermits);
      double var8 = var1 - var6;
      long var10 = this.storedPermitsToWaitTime(this.storedPermits, var6) + (long)(var8 * this.stableIntervalMicros);
      this.nextFreeTicketMicros = LongMath.saturatedAdd(this.nextFreeTicketMicros, var10);
      this.storedPermits -= var6;
      return var4;
   }

   abstract long storedPermitsToWaitTime(double var1, double var3);

   abstract double coolDownIntervalMicros();

   void resync(long var1) {
      if (var1 > this.nextFreeTicketMicros) {
         double var3 = (var1 - this.nextFreeTicketMicros) / this.coolDownIntervalMicros();
         this.storedPermits = Math.min(this.maxPermits, this.storedPermits + var3);
         this.nextFreeTicketMicros = var1;
      }
   }
}
