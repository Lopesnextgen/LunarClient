package com.moonsworth.lunar.genesis;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;

@Annotation3
abstract class SerializableIterator3<K, V> extends ImmutableMap<K, V> {
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

   @Deprecated
   public static <K, V> ImmutableSortedMap.Data<K, V> method2() {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   public static <K, V> ImmutableSortedMap.Data<K, V> method2(int var0) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   public static <K, V> ImmutableSortedMap<K, V> method3(K var0, V var1) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   public static <K, V> ImmutableSortedMap<K, V> method4(K var0, V var1, K var2, V var3) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   public static <K, V> ImmutableSortedMap<K, V> method5(K var0, V var1, K var2, V var3, K var4, V var5) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   public static <K, V> ImmutableSortedMap<K, V> method6(K var0, V var1, K var2, V var3, K var4, V var5, K var6, V var7) {
      throw new UnsupportedOperationException();
   }

   @Deprecated
   public static <K, V> ImmutableSortedMap<K, V> method7(K var0, V var1, K var2, V var3, K var4, V var5, K var6, V var7, K var8, V var9) {
      throw new UnsupportedOperationException();
   }
}
