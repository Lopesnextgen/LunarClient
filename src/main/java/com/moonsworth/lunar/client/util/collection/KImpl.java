package com.moonsworth.lunar.client.util.collection;

import java.util.Map;
import java.util.function.Function;

public class KImpl<M extends Map<K, V>, K, V> {
   private final M map;

   public KImpl(M m1) {
      this.map = (M)m1;
   }

   public KImpl<M, K, V> method1(K value1, V value2) {
      this.map.put((K)value1, (V)value2);
      return this;
   }

   public KImpl<M, K, V> method2(Map<? extends K, ? extends V> map2) {
      this.map.putAll(map2);
      return this;
   }

   public KImpl<M, K, V> method3(K value1, V value2) {
      this.map.putIfAbsent((K)value1, (V)value2);
      return this;
   }

   public KImpl<M, K, V> method4(K value1, Function<? super K, ? extends V> function2) {
      this.map.computeIfAbsent((K)value1, function2);
      return this;
   }

   public M getMap() {
      return this.map;
   }
}
