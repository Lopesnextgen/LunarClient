package com.moonsworth.lunar.genesis;

import java.util.EnumSet;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableSet;

final class Sets$Accumulator<E extends Enum<E>> {
   static final Collector<Enum<?>, ?, ImmutableSet<? extends Enum<?>>> field1 = Collector.of(
      Sets$Accumulator::new, Sets$Accumulator::add, Sets$Accumulator::method1, Sets$Accumulator::method2, Characteristics.UNORDERED
   );
   private @Nullable EnumSet<E> set;

   private Sets$Accumulator() {
   }

   void add(E value1) {
      if (this.set == null) {
         this.set = EnumSet.of((E)value1);
      } else {
         this.set.add((E)value1);
      }
   }

   Sets$Accumulator<E> method1(Sets$Accumulator<E> mixinhelper10$data81) {
      if (this.set == null) {
         return mixinhelper10$data81;
      }

      if (mixinhelper10$data81.set == null) {
         return this;
      }

      this.set.addAll(mixinhelper10$data81.set);
      return this;
   }

   ImmutableSet<E> method2() {
      return this.set == null ? ImmutableSet.method3() : AbstractCollectionIterator54.method1(this.set);
   }
}
