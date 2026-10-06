package com.moonsworth.lunar.genesis;
import com.google.common.util.concurrent.RateLimiter;

final class MixinHelper42$Data4 extends MixinHelper42_3 {
   final double field2;

   MixinHelper42$Data4(RateLimiter.Data16 var1, double var2) {
      super(var1);
      this.field2 = var2;
   }

   @Override
   void doSetRate(double var1, double var3) {
      double var5 = this.maxPermits;
      this.maxPermits = this.field2 * var1;
      if (var5 == Double.POSITIVE_INFINITY) {
         this.storedPermits = this.maxPermits;
      } else {
         this.storedPermits = var5 == 0.0 ? 0.0 : this.storedPermits * this.maxPermits / var5;
      }
   }

   @Override
   long storedPermitsToWaitTime(double var1, double var3) {
      return 0L;
   }

   @Override
   double coolDownIntervalMicros() {
      return this.stableIntervalMicros;
   }
}
