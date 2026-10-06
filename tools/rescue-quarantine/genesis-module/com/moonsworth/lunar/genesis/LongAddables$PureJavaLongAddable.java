package com.moonsworth.lunar.genesis;

import java.util.concurrent.atomic.AtomicLong;

final class LongAddables$PureJavaLongAddable extends AtomicLong implements LongAddable {
   private LongAddables$PureJavaLongAddable() {
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
