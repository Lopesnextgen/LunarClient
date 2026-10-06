package com.moonsworth.lunar.genesis;

import java.time.Duration;

@Annotation3
final class MixinHelper7_7 {
   static long toNanosSaturated(Duration var0) {
      try {
         return var0.toNanos();
      } catch (ArithmeticException var2) {
         return var0.isNegative() ? Long.MIN_VALUE : Long.MAX_VALUE;
      }
   }

   private MixinHelper7_7() {
   }
}
