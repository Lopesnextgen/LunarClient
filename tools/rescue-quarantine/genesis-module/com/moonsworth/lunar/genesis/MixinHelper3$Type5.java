package com.moonsworth.lunar.genesis;
import com.google.common.hash.PrimitiveSink;
import com.google.common.hash.Funnel;

enum MixinHelper3$Type5 implements Funnel<Integer> {
   INSTANCE;

   MixinHelper3$Type5() {
   }

   public void funnel(Integer number1, PrimitiveSink mixinhelper4_32) {
      mixinhelper4_32.method6(number1);
   }

   @Override
   public String toString() {
      return "Funnels.integerFunnel()";
   }
}
