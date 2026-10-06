package com.moonsworth.lunar.genesis;

import sun.misc.Unsafe;

final class NumberBase$Data {
   volatile long p0;
   volatile long p1;
   volatile long p2;
   volatile long p3;
   volatile long p4;
   volatile long p5;
   volatile long p6;
   volatile long value;
   volatile long q0;
   volatile long q1;
   volatile long q2;
   volatile long q3;
   volatile long q4;
   volatile long q5;
   volatile long q6;
   private static final Unsafe field1;
   private static final long field2;

   NumberBase$Data(long var1) {
      this.value = var1;
   }

   final boolean method1(long var1, long var3) {
      return field1.compareAndSwapLong(this, field2, var1, var3);
   }

   static {
      try {
         field1 = NumberBase_2.access$000();
         Class<NumberBase$Data> var0 = NumberBase$Data.class;
         field2 = field1.objectFieldOffset(var0.getDeclaredField("value"));
      } catch (Exception var1) {
         throw new Error(var1);
      }
   }
}
