package com.moonsworth.lunar.genesis;

import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

final class MixinHelper30$Data4 extends MixinHelper30$Data5 {
   final AtomicReferenceFieldUpdater<MixinHelper30_2, Set<Throwable>> field1;
   final AtomicIntegerFieldUpdater<MixinHelper30_2> field2;

   MixinHelper30$Data4(AtomicReferenceFieldUpdater var1, AtomicIntegerFieldUpdater var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @Override
   void method1(MixinHelper30_2 var1, Set<Throwable> var2, Set<Throwable> var3) {
      this.field1.compareAndSet(var1, var2, var3);
   }

   @Override
   int method2(MixinHelper30_2 var1) {
      return this.field2.decrementAndGet(var1);
   }
}
