package com.moonsworth.lunar.genesis;

import java.util.EnumSet;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ImmutableSet;

final class MixinHelper10$Data8<E extends Enum<E>> {
   static final Collector<Enum<?>, ?, ImmutableSet<? extends Enum<?>>> field1 = Collector.of(
      MixinHelper10$Data8::new, MixinHelper10$Data8::add, MixinHelper10$Data8::method1, MixinHelper10$Data8::method2, Characteristics.UNORDERED
   );
   private @Nullable EnumSet<E> set;

   private MixinHelper10$Data8() {
   }

   void add(E var1) {
      if (this.set == null) {
         this.set = EnumSet.of((E)var1);
      } else {
         this.set.add((E)var1);
      }
   }

   MixinHelper10$Data8<E> method1(MixinHelper10$Data8<E> var1) {
      if (this.set == null) {
         return var1;
      }

      if (var1.set == null) {
         return this;
      }

      this.set.addAll(var1.set);
      return this;
   }

   ImmutableSet<E> method2() {
      return this.set == null ? ImmutableSet.method3() : AbstractCollectionIterator54.method1(this.set);
   }
}
