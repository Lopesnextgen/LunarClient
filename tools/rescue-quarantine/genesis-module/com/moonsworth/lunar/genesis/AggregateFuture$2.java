package com.moonsworth.lunar.genesis;
import com.google.common.collect.ImmutableCollection;

class AggregateFuture$2 implements Runnable {
   AggregateFuture$2(MixinHelper302 mixinhelper3021, ImmutableCollection abstractcollectioniterator2) {
      this.field2 = mixinhelper3021;
      this.field1 = abstractcollectioniterator2;
   }

   @Override
   public void run() {
      MixinHelper302.method7(this.field2, this.field1);
   }
}
