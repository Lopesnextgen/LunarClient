package com.moonsworth.lunar.genesis;

final class Hashing$LinearCongruentialGenerator {
   private long state;

   public Hashing$LinearCongruentialGenerator(long number1) {
      this.state = number1;
   }

   public double nextDouble() {
      this.state = 2862933555777941757L * this.state + 1L;
      return ((int)(this.state >>> 33) + 1) / 2.1474836E9F;
   }
}
