package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.SortedSet;
import com.google.common.collect.Maps;

@Annotation3
class MixinHelper19$Data37<K, V> extends MixinHelper19$Data15<K, V> implements NavigableSet<K> {
   MixinHelper19$Data37(NavigableMap<K, V> var1) {
      super(var1);
   }

   NavigableMap<K, V> map() {
      return (NavigableMap<K, V>)this.field1;
   }

   @Override
   public K lower(K var1) {
      return this.map().lowerKey((K)var1);
   }

   @Override
   public K floor(K var1) {
      return this.map().floorKey((K)var1);
   }

   @Override
   public K ceiling(K var1) {
      return this.map().ceilingKey((K)var1);
   }

   @Override
   public K higher(K var1) {
      return this.map().higherKey((K)var1);
   }

   @Override
   public K pollFirst() {
      return Maps.keyOrNull(this.map().pollFirstEntry());
   }

   @Override
   public K pollLast() {
      return Maps.keyOrNull(this.map().pollLastEntry());
   }

   @Override
   public NavigableSet<K> descendingSet() {
      return this.map().descendingKeySet();
   }

   @Override
   public Iterator<K> descendingIterator() {
      return this.descendingSet().iterator();
   }

   @Override
   public NavigableSet<K> subSet(K var1, boolean var2, K var3, boolean var4) {
      return this.map().subMap((K)var1, var2, (K)var3, var4).navigableKeySet();
   }

   @Override
   public SortedSet<K> subSet(K var1, K var2) {
      return this.subSet((K)var1, true, (K)var2, false);
   }

   @Override
   public NavigableSet<K> headSet(K var1, boolean var2) {
      return this.map().headMap((K)var1, var2).navigableKeySet();
   }

   @Override
   public SortedSet<K> headSet(K var1) {
      return this.headSet((K)var1, false);
   }

   @Override
   public NavigableSet<K> tailSet(K var1, boolean var2) {
      return this.map().tailMap((K)var1, var2).navigableKeySet();
   }

   @Override
   public SortedSet<K> tailSet(K var1) {
      return this.tailSet((K)var1, true);
   }
}
