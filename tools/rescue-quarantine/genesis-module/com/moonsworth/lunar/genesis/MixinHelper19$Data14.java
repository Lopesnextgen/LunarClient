package com.moonsworth.lunar.genesis;

import java.util.EnumMap;
import java.util.function.BinaryOperator;
import com.google.common.collect.ImmutableMap;

class MixinHelper19$Data14<K extends Enum<K>, V> {
   private final BinaryOperator<V> field1;
   private EnumMap<K, V> map = null;

   MixinHelper19$Data14(BinaryOperator<V> var1) {
      this.field1 = var1;
   }

   void put(K var1, V var2) {
      if (this.map == null) {
         this.map = new EnumMap<>(var1.getDeclaringClass());
      }

      this.map.merge((K)var1, (V)var2, this.field1);
   }

   MixinHelper19$Data14<K, V> method1(MixinHelper19$Data14<K, V> var1) {
      if (this.map == null) {
         return var1;
      }

      if (var1.map == null) {
         return this;
      }

      var1.map.forEach(this::put);
      return this;
   }

   ImmutableMap<K, V> method2() {
      return this.map == null ? ImmutableMap.method1() : MixinHelper46.method1(this.map);
   }
}
