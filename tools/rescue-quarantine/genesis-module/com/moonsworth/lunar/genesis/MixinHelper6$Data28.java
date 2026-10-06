package com.moonsworth.lunar.genesis;

import java.util.concurrent.atomic.AtomicLong;

final class MixinHelper6$Data28 extends AtomicLong implements HashLongAddable {
   private MixinHelper6$Data28() {
   }

   public void increment() {
      this.getAndIncrement();
   }

   public void add(long number1) {
      this.getAndAdd(number1);
   }

   public long sum() {
      return this.get();
   }
}
