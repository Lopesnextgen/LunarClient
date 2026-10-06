package com.moonsworth.lunar.genesis;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import com.google.common.collect.ImmutableMap;

@Annotation3
abstract class SerializableIterator5<K, V> extends ImmutableMap<K, V> {
   @Deprecated
   public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(
      Function<? super T, ? extends K> var0, Function<? super T, ? extends V> var1
   ) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   public static <T, K, V> Collector<T, ?, ImmutableMap<K, V>> toImmutableMap(
      Function<? super T, ? extends K> var0, Function<? super T, ? extends V> var1, BinaryOperator<V> var2
   ) {
      throw new UnsupportedOperationException();
   }
}
