package com.moonsworth.lunar.genesis;

import java.util.SortedMap;
import org.checkerframework.checker.nullness.qual.Nullable;

class MixinHelper$Data33<K, V> extends MixinHelper$Data25<K, V> implements SortedMap<K, V> {
   private static final long field5 = 0L;

   MixinHelper$Data33(SortedMap<K, V> var1, @Nullable Object var2) {
      super(var1, var2);
   }

   SortedMap<K, V> delegate() {
      return (SortedMap<K, V>)super.delegate();
   }

   @Override
   public java.util.Comparator<? super K> comparator() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().comparator();
      }
   }

   @Override
   public K firstKey() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().firstKey();
      }
   }

   @Override
   public SortedMap<K, V> headMap(K var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.sortedMap(this.delegate().headMap((K)var1), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public K lastKey() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().lastKey();
      }
   }

   @Override
   public SortedMap<K, V> subMap(K var1, K var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.sortedMap(this.delegate().subMap((K)var1, (K)var2), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public SortedMap<K, V> tailMap(K var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.sortedMap(this.delegate().tailMap((K)var1), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }
}
