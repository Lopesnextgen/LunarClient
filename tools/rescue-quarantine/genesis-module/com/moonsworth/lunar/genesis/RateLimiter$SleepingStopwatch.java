package com.moonsworth.lunar.genesis;

abstract class RateLimiter$SleepingStopwatch {
   protected RateLimiter$SleepingStopwatch() {
   }

   protected abstract long readMicros();

   protected abstract void sleepMicrosUninterruptibly(long number1);

   public static RateLimiter$SleepingStopwatch method1() {
      return new Data16$1();
   }
}
