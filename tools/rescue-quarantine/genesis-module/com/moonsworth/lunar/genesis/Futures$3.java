package com.moonsworth.lunar.genesis;
import com.google.common.util.concurrent.ListenableFuture;

class Futures$3 implements Runnable {
   Futures$3(MixinHelper302 mixinhelper3021, ListenableFuture futureextension2, int number3) {
      this.field3 = mixinhelper3021;
      this.field1 = futureextension2;
      this.field2 = number3;
   }

   @Override
   public void run() {
      try {
         if (this.field1.isCancelled()) {
            MixinHelper302.method5(this.field3, null);
            this.field3.cancel(false);
         } else {
            MixinHelper302.method6(this.field3, this.field2, this.field1);
         }
      } finally {
         MixinHelper302.method7(this.field3, null);
      }
   }
}
