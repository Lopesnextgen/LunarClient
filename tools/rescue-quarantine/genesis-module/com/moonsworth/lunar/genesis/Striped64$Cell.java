package com.moonsworth.lunar.genesis;

import sun.misc.Unsafe;

final class Striped64$Cell {
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

   Striped64$Cell(long number1) {
      this.value = number1;
   }

   final boolean method1(long number1, long number3) {
      return field1.compareAndSwapLong(this, field2, number1, number3);
   }

   static {
      try {
         field1 = NumberBase_2.access$000();
         Class<Striped64$Cell> clazz0 = Striped64$Cell.class;
         field2 = field1.objectFieldOffset(clazz0.getDeclaredField("value"));
      } catch (Exception exception1) {
         throw new Error(exception1);
      }
   }
}
