package com.moonsworth.lunar.genesis;
import com.google.common.hash.PrimitiveSink;
import com.google.common.hash.Funnel;

enum MixinHelper3$Type8 implements Funnel<CharSequence> {
   INSTANCE;

   MixinHelper3$Type8() {
   }

   public void funnel(CharSequence text1, PrimitiveSink mixinhelper4_32) {
      mixinhelper4_32.method12(text1);
   }

   @Override
   public String toString() {
      return "Funnels.unencodedCharsFunnel()";
   }
}
