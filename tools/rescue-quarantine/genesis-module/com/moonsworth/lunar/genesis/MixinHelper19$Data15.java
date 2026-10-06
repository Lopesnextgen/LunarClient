package com.moonsworth.lunar.genesis;

import java.util.SortedMap;
import java.util.SortedSet;

class MixinHelper19$Data15<K, V> extends MixinHelper19$Data25<K, V> implements SortedSet<K> {
   MixinHelper19$Data15(SortedMap<K, V> var1) {
      super(var1);
   }

   SortedMap<K, V> map() {
      return (SortedMap<K, V>)super.map();
   }

   @Override
   public java.util.Comparator<? super K> comparator() {
      return this.map().comparator();
   }

   @Override
   public SortedSet<K> subSet(K var1, K var2) {
      return new MixinHelper19$Data15<>(this.map().subMap((K)var1, (K)var2));
   }

   @Override
   public SortedSet<K> headSet(K var1) {
      return new MixinHelper19$Data15<>(this.map().headMap((K)var1));
   }

   @Override
   public SortedSet<K> tailSet(K var1) {
      return new MixinHelper19$Data15<>(this.map().tailMap((K)var1));
   }

   @Override
   public K first() {
      return this.map().firstKey();
   }

   @Override
   public K last() {
      return this.map().lastKey();
   }
}
