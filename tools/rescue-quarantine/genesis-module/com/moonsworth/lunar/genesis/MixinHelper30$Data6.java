package com.moonsworth.lunar.genesis;

import java.util.Set;

final class MixinHelper30$Data6 extends MixinHelper30$Data5 {
   private MixinHelper30$Data6() {
   }

   @Override
   void method1(MixinHelper30_2 var1, Set<Throwable> var2, Set<Throwable> var3) {
      synchronized (var1) {
         if (MixinHelper30_2.method4(var1) == var2) {
            MixinHelper30_2.method5(var1, var3);
         }
      }
   }

   @Override
   int method2(MixinHelper30_2 var1) {
      synchronized (var1) {
         return MixinHelper30_2.method6(var1);
      }
   }
}
