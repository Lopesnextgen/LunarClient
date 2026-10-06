package com.moonsworth.lunar.genesis;

import java.util.Spliterator;

abstract class Streams$MapWithIndexSpliterator<F extends Spliterator<?>, R, S extends Streams$MapWithIndexSpliterator<F, R, S>> implements Spliterator<R> {
   final F field1;
   long index;

   Streams$MapWithIndexSpliterator(F f1, long number2) {
      this.field1 = (F)f1;
      this.index = number2;
   }

   abstract S method1(F f1, long number2);

   public S method2() {
      Spliterator spliterator1 = this.field1.trySplit();
      if (spliterator1 == null) {
         return null;
      }

      Streams$MapWithIndexSpliterator mixinhelper23$data42 = this.method1((F)spliterator1, this.index);
      this.index = this.index + spliterator1.getExactSizeIfKnown();
      return (S)mixinhelper23$data42;
   }

   @Override
   public long estimateSize() {
      return this.field1.estimateSize();
   }

   @Override
   public int characteristics() {
      return this.field1.characteristics() & 16464;
   }
}
