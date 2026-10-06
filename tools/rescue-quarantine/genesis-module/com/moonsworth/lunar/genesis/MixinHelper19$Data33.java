package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Ordering;
import com.google.common.collect.ForwardingMap;

@Annotation3
abstract class MixinHelper19$Data33<K, V> extends ForwardingMap<K, V> implements NavigableMap<K, V> {
   private transient @Nullable Ordering<? super K> comparator;
   private transient @Nullable Set<Entry<K, V>> entrySet;
   private transient @Nullable NavigableSet<K> navigableKeySet;

   abstract NavigableMap<K, V> forward();

   @Override
   protected final Map<K, V> delegate() {
      return this.forward();
   }

   @Override
   public java.util.Comparator<? super K> comparator() {
      java.util.Comparator var1 = this.comparator;
      if (var1 == null) {
         java.util.Comparator var2 = this.forward().comparator();
         if (var2 == null) {
            var2 = Ordering.method1();
         }

         var1 = this.comparator = method1(var2);
      }

      return var1;
   }

   private static <T> Ordering<T> method1(java.util.Comparator<T> var0) {
      return Ordering.method2(var0).method9();
   }

   @Override
   public K firstKey() {
      return this.forward().lastKey();
   }

   @Override
   public K lastKey() {
      return this.forward().firstKey();
   }

   @Override
   public Entry<K, V> lowerEntry(K var1) {
      return this.forward().higherEntry((K)var1);
   }

   @Override
   public K lowerKey(K var1) {
      return this.forward().higherKey((K)var1);
   }

   @Override
   public Entry<K, V> floorEntry(K var1) {
      return this.forward().ceilingEntry((K)var1);
   }

   @Override
   public K floorKey(K var1) {
      return this.forward().ceilingKey((K)var1);
   }

   @Override
   public Entry<K, V> ceilingEntry(K var1) {
      return this.forward().floorEntry((K)var1);
   }

   @Override
   public K ceilingKey(K var1) {
      return this.forward().floorKey((K)var1);
   }

   @Override
   public Entry<K, V> higherEntry(K var1) {
      return this.forward().lowerEntry((K)var1);
   }

   @Override
   public K higherKey(K var1) {
      return this.forward().lowerKey((K)var1);
   }

   @Override
   public Entry<K, V> firstEntry() {
      return this.forward().lastEntry();
   }

   @Override
   public Entry<K, V> lastEntry() {
      return this.forward().firstEntry();
   }

   @Override
   public Entry<K, V> pollFirstEntry() {
      return this.forward().pollLastEntry();
   }

   @Override
   public Entry<K, V> pollLastEntry() {
      return this.forward().pollFirstEntry();
   }

   @Override
   public NavigableMap<K, V> descendingMap() {
      return this.forward();
   }

   @Override
   public Set<Entry<K, V>> entrySet() {
      Set var1 = this.entrySet;
      return var1 == null ? (this.entrySet = this.createEntrySet()) : var1;
   }

   abstract Iterator<Entry<K, V>> entryIterator();

   Set<Entry<K, V>> createEntrySet() {
      class Data extends MixinHelper19$Data32<K, V> {
         @Override
         Map<K, V> map() {
            return MixinHelper19$Data33.this;
         }

         @Override
         public Iterator<Entry<K, V>> iterator() {
            return MixinHelper19$Data33.this.entryIterator();
         }
      }

      return new Data();
   }

   @Override
   public Set<K> keySet() {
      return this.navigableKeySet();
   }

   @Override
   public NavigableSet<K> navigableKeySet() {
      NavigableSet var1 = this.navigableKeySet;
      return var1 == null ? (this.navigableKeySet = new MixinHelper19$Data37<>(this)) : var1;
   }

   @Override
   public NavigableSet<K> descendingKeySet() {
      return this.forward().navigableKeySet();
   }

   @Override
   public NavigableMap<K, V> subMap(K var1, boolean var2, K var3, boolean var4) {
      return this.forward().subMap((K)var3, var4, (K)var1, var2).descendingMap();
   }

   @Override
   public SortedMap<K, V> subMap(K var1, K var2) {
      return this.subMap((K)var1, true, (K)var2, false);
   }

   @Override
   public NavigableMap<K, V> headMap(K var1, boolean var2) {
      return this.forward().tailMap((K)var1, var2).descendingMap();
   }

   @Override
   public SortedMap<K, V> headMap(K var1) {
      return this.headMap((K)var1, false);
   }

   @Override
   public NavigableMap<K, V> tailMap(K var1, boolean var2) {
      return this.forward().headMap((K)var1, var2).descendingMap();
   }

   @Override
   public SortedMap<K, V> tailMap(K var1) {
      return this.tailMap((K)var1, true);
   }

   @Override
   public Collection<V> values() {
      return new MixinHelper19$Data35<>(this);
   }

   @Override
   public String toString() {
      return this.standardToString();
   }
}
