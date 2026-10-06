package com.moonsworth.lunar.genesis;

import java.time.Duration;
import com.google.common.annotations.GwtIncompatible;

@GwtIncompatible
final class Internal {
   static long toNanosSaturated(Duration duration0) {
      try {
         return duration0.toNanos();
      } catch (ArithmeticException arithmeticexception2) {
         return duration0.isNegative() ? Long.MIN_VALUE : Long.MAX_VALUE;
      }
   }

   private Internal() {
   }
}
