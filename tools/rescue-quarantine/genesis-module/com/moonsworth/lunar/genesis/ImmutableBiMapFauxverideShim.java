package com.moonsworth.lunar.genesis;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import com.google.common.collect.ImmutableMap;
import com.google.common.annotations.GwtIncompatible;

@GwtIncompatible
abstract class ImmutableBiMapFauxverideShim<K, V> extends ImmutableMap<K, V> {
   ImmutableBiMapFauxverideShim() {
   }

   @Deprecated
   public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(
      Function<? super T, ? extends K> function0, Function<? super T, ? extends V> function1
   ) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(
      Function<? super T, ? extends K> function0, Function<? super T, ? extends V> function1, BinaryOperator<V> binaryoperator2
   ) {
      throw new UnsupportedOperationException();
   }
}
