package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Collections;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.ForwardingSortedMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

@Annotation3
class MixinHelper19$Data22<K, V> extends ForwardingSortedMap<K, V> implements Serializable, NavigableMap<K, V> {
   private final NavigableMap<K, ? extends V> field1;
   private transient @Nullable MixinHelper19.MixinHelper19$Data22<K, V> field2;

   MixinHelper19$Data22(NavigableMap<K, ? extends V> var1) {
      this.field1 = var1;
   }

   MixinHelper19$Data22(NavigableMap<K, ? extends V> var1, MixinHelper19$Data22<K, V> var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @Override
   protected SortedMap<K, V> delegate() {
      return Collections.unmodifiableSortedMap(this.field1);
   }

   @Override
   public Entry<K, V> lowerEntry(K var1) {
      return Maps.access$800(this.field1.lowerEntry((K)var1));
   }

   @Override
   public K lowerKey(K var1) {
      return this.field1.lowerKey((K)var1);
   }

   @Override
   public Entry<K, V> floorEntry(K var1) {
      return Maps.access$800(this.field1.floorEntry((K)var1));
   }

   @Override
   public K floorKey(K var1) {
      return this.field1.floorKey((K)var1);
   }

   @Override
   public Entry<K, V> ceilingEntry(K var1) {
      return Maps.access$800(this.field1.ceilingEntry((K)var1));
   }

   @Override
   public K ceilingKey(K var1) {
      return this.field1.ceilingKey((K)var1);
   }

   @Override
   public Entry<K, V> higherEntry(K var1) {
      return Maps.access$800(this.field1.higherEntry((K)var1));
   }

   @Override
   public K higherKey(K var1) {
      return this.field1.higherKey((K)var1);
   }

   @Override
   public Entry<K, V> firstEntry() {
      return Maps.access$800(this.field1.firstEntry());
   }

   @Override
   public Entry<K, V> lastEntry() {
      return Maps.access$800(this.field1.lastEntry());
   }

   @Override
   public final Entry<K, V> pollFirstEntry() {
      throw new UnsupportedOperationException();
   }

   @Override
   public final Entry<K, V> pollLastEntry() {
      throw new UnsupportedOperationException();
   }

   @Override
   public NavigableMap<K, V> descendingMap() {
      MixinHelper19$Data22 var1 = this.field2;
      return var1 == null ? (this.field2 = new MixinHelper19$Data22<>(this.field1.descendingMap(), this)) : var1;
   }

   @Override
   public Set<K> keySet() {
      return this.navigableKeySet();
   }

   @Override
   public NavigableSet<K> navigableKeySet() {
      return Sets.unmodifiableNavigableSet(this.field1.navigableKeySet());
   }

   @Override
   public NavigableSet<K> descendingKeySet() {
      return Sets.unmodifiableNavigableSet(this.field1.descendingKeySet());
   }

   @Override
   public SortedMap<K, V> subMap(K var1, K var2) {
      return this.subMap((K)var1, true, (K)var2, false);
   }

   @Override
   public NavigableMap<K, V> subMap(K var1, boolean var2, K var3, boolean var4) {
      return Maps.unmodifiableNavigableMap(this.field1.subMap((K)var1, var2, (K)var3, var4));
   }

   @Override
   public SortedMap<K, V> headMap(K var1) {
      return this.headMap((K)var1, false);
   }

   @Override
   public NavigableMap<K, V> headMap(K var1, boolean var2) {
      return Maps.unmodifiableNavigableMap(this.field1.headMap((K)var1, var2));
   }

   @Override
   public SortedMap<K, V> tailMap(K var1) {
      return this.tailMap((K)var1, true);
   }

   @Override
   public NavigableMap<K, V> tailMap(K var1, boolean var2) {
      return Maps.unmodifiableNavigableMap(this.field1.tailMap((K)var1, var2));
   }
}
