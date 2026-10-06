package com.moonsworth.lunar.genesis;

final class MixinHelper9$Data22 {
   private long state;

   public MixinHelper9$Data22(long var1) {
      this.state = var1;
   }

   public double nextDouble() {
      this.state = 2862933555777941757L * this.state + 1L;
      return ((int)(this.state >>> 33) + 1) / 2.1474836E9F;
   }
}
