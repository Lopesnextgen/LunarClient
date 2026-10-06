package com.moonsworth.lunar.genesis;

import java.util.EnumMap;
import java.util.function.BinaryOperator;
import com.google.common.collect.ImmutableMap;

class Maps$Accumulator<K extends Enum<K>, V> {
   private final BinaryOperator<V> field1;
   private EnumMap<K, V> map = null;

   Maps$Accumulator(BinaryOperator<V> binaryoperator1) {
      this.field1 = binaryoperator1;
   }

   void put(K value1, V value2) {
      if (this.map == null) {
         this.map = new EnumMap<>(value1.getDeclaringClass());
      }

      this.map.merge((K)value1, (V)value2, this.field1);
   }

   Maps$Accumulator<K, V> method1(Maps$Accumulator<K, V> mixinhelper19$data141) {
      if (this.map == null) {
         return mixinhelper19$data141;
      }

      if (mixinhelper19$data141.map == null) {
         return this;
      }

      mixinhelper19$data141.map.forEach(this::put);
      return this;
   }

   ImmutableMap<K, V> method2() {
      return this.map == null ? ImmutableMap.method1() : MixinHelper46.method1(this.map);
   }
}
