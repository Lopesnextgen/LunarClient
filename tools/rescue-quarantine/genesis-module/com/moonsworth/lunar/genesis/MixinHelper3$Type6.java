package com.moonsworth.lunar.genesis;
import com.google.common.hash.PrimitiveSink;
import com.google.common.hash.Funnel;

enum MixinHelper3$Type6 implements Funnel<byte[]> {
   INSTANCE;

   MixinHelper3$Type6() {
   }

   public void funnel(byte[] items1, PrimitiveSink mixinhelper4_32) {
      mixinhelper4_32.method2(items1);
   }

   @Override
   public String toString() {
      return "Funnels.byteArrayFunnel()";
   }
}
