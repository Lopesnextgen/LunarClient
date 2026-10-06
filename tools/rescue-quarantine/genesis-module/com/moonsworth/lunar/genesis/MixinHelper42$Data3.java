package com.moonsworth.lunar.genesis;

import java.util.concurrent.TimeUnit;
import com.google.common.util.concurrent.RateLimiter;

final class MixinHelper42$Data3 extends MixinHelper42_3 {
   private final long field2;
   private double slope;
   private double thresholdPermits;
   private double coldFactor;

   MixinHelper42$Data3(RateLimiter.Data16 var1, long var2, TimeUnit var4, double var5) {
      super(var1);
      this.field2 = var4.toMicros(var2);
      this.coldFactor = var5;
   }

   @Override
   void doSetRate(double var1, double var3) {
      double var5 = this.maxPermits;
      double var7 = var3 * this.coldFactor;
      this.thresholdPermits = 0.5 * this.field2 / var3;
      this.maxPermits = this.thresholdPermits + 2.0 * this.field2 / (var3 + var7);
      this.slope = (var7 - var3) / (this.maxPermits - this.thresholdPermits);
      if (var5 == Double.POSITIVE_INFINITY) {
         this.storedPermits = 0.0;
      } else {
         this.storedPermits = var5 == 0.0 ? this.maxPermits : this.storedPermits * this.maxPermits / var5;
      }
   }

   @Override
   long storedPermitsToWaitTime(double var1, double var3) {
      double var5 = var1 - this.thresholdPermits;
      long var7 = 0L;
      if (var5 > 0.0) {
         double var9 = Math.min(var5, var3);
         double var11 = this.permitsToTime(var5) + this.permitsToTime(var5 - var9);
         var7 = (long)(var9 * var11 / 2.0);
         var3 -= var9;
      }

      return var7 + (long)(this.stableIntervalMicros * var3);
   }

   private double permitsToTime(double var1) {
      return this.stableIntervalMicros + var1 * this.slope;
   }

   @Override
   double coolDownIntervalMicros() {
      return this.field2 / this.maxPermits;
   }
}
