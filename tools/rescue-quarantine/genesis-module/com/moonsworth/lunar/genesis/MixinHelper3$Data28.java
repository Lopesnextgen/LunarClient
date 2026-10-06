package com.moonsworth.lunar.genesis;

import java.util.concurrent.atomic.AtomicLong;

final class MixinHelper3$Data28 extends AtomicLong implements MixinHelper9_4 {
   private MixinHelper3$Data28() {
   }

   @Override
   public void increment() {
      this.getAndIncrement();
   }

   @Override
   public void add(long var1) {
      this.getAndAdd(var1);
   }

   @Override
   public long sum() {
      return this.get();
   }
}
