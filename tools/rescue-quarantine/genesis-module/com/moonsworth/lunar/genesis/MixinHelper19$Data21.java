package com.moonsworth.lunar.genesis;

import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import com.google.common.collect.Maps;

class MixinHelper19$Data21<K, V> extends MixinHelper19$Data19<K, V> implements SortedMap<K, V> {
   MixinHelper19$Data21(SortedSet<K> var1, MixinHelper24_2<? super K, V> var2) {
      super(var1, var2);
   }

   SortedSet<K> backingSet() {
      return (SortedSet<K>)super.backingSet();
   }

   @Override
   public java.util.Comparator<? super K> comparator() {
      return this.backingSet().comparator();
   }

   @Override
   public Set<K> keySet() {
      return Maps.access$300(this.backingSet());
   }

   @Override
   public SortedMap<K, V> subMap(K var1, K var2) {
      return Maps.method9(this.backingSet().subSet((K)var1, (K)var2), this.field2);
   }

   @Override
   public SortedMap<K, V> headMap(K var1) {
      return Maps.method9(this.backingSet().headSet((K)var1), this.field2);
   }

   @Override
   public SortedMap<K, V> tailMap(K var1) {
      return Maps.method9(this.backingSet().tailSet((K)var1), this.field2);
   }

   @Override
   public K firstKey() {
      return this.backingSet().first();
   }

   @Override
   public K lastKey() {
      return this.backingSet().last();
   }
}
