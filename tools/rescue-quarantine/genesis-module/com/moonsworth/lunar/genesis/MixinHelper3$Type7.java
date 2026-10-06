package com.moonsworth.lunar.genesis;
import com.google.common.hash.PrimitiveSink;
import com.google.common.hash.Funnel;

enum MixinHelper3$Type7 implements Funnel<Long> {
   INSTANCE;

   MixinHelper3$Type7() {
   }

   public void funnel(Long number1, PrimitiveSink mixinhelper4_32) {
      mixinhelper4_32.method7(number1);
   }

   @Override
   public String toString() {
      return "Funnels.longFunnel()";
   }
}
