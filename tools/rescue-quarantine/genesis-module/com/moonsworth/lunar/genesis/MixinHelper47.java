package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Maps;
import com.google.common.collect.Iterators;

@Annotation3
abstract class MixinHelper47<K, V> extends MixinHelper19$Data24<K, V> implements NavigableMap<K, V> {
   @Override
   public abstract @Nullable V get(@Nullable Object var1);

   @Override
   public @Nullable Entry<K, V> firstEntry() {
      return Iterators.getNext(this.entryIterator(), null);
   }

   @Override
   public @Nullable Entry<K, V> lastEntry() {
      return Iterators.getNext(this.descendingEntryIterator(), null);
   }

   @Override
   public @Nullable Entry<K, V> pollFirstEntry() {
      return Iterators.pollNext(this.entryIterator());
   }

   @Override
   public @Nullable Entry<K, V> pollLastEntry() {
      return Iterators.pollNext(this.descendingEntryIterator());
   }

   @Override
   public K firstKey() {
      Entry var1 = this.firstEntry();
      if (var1 == null) {
         throw new NoSuchElementException();
      } else {
         return (K)var1.getKey();
      }
   }

   @Override
   public K lastKey() {
      Entry var1 = this.lastEntry();
      if (var1 == null) {
         throw new NoSuchElementException();
      } else {
         return (K)var1.getKey();
      }
   }

   @Override
   public @Nullable Entry<K, V> lowerEntry(K var1) {
      return this.headMap((K)var1, false).lastEntry();
   }

   @Override
   public @Nullable Entry<K, V> floorEntry(K var1) {
      return this.headMap((K)var1, true).lastEntry();
   }

   @Override
   public @Nullable Entry<K, V> ceilingEntry(K var1) {
      return this.tailMap((K)var1, true).firstEntry();
   }

   @Override
   public @Nullable Entry<K, V> higherEntry(K var1) {
      return this.tailMap((K)var1, false).firstEntry();
   }

   @Override
   public K lowerKey(K var1) {
      return Maps.keyOrNull(this.lowerEntry((K)var1));
   }

   @Override
   public K floorKey(K var1) {
      return Maps.keyOrNull(this.floorEntry((K)var1));
   }

   @Override
   public K ceilingKey(K var1) {
      return Maps.keyOrNull(this.ceilingEntry((K)var1));
   }

   @Override
   public K higherKey(K var1) {
      return Maps.keyOrNull(this.higherEntry((K)var1));
   }

   abstract Iterator<Entry<K, V>> descendingEntryIterator();

   @Override
   public SortedMap<K, V> subMap(K var1, K var2) {
      return this.subMap((K)var1, true, (K)var2, false);
   }

   @Override
   public SortedMap<K, V> headMap(K var1) {
      return this.headMap((K)var1, false);
   }

   @Override
   public SortedMap<K, V> tailMap(K var1) {
      return this.tailMap((K)var1, true);
   }

   @Override
   public NavigableSet<K> navigableKeySet() {
      return new MixinHelper19$Data37<>(this);
   }

   @Override
   public Set<K> keySet() {
      return this.navigableKeySet();
   }

   @Override
   public NavigableSet<K> descendingKeySet() {
      return this.descendingMap().navigableKeySet();
   }

   @Override
   public NavigableMap<K, V> descendingMap() {
      return new MixinHelper47.Data();
   }

   private final class Data extends MixinHelper19$Data33<K, V> {
      private Data() {
      }

      @Override
      NavigableMap<K, V> forward() {
         return MixinHelper47.this;
      }

      @Override
      Iterator<Entry<K, V>> entryIterator() {
         return MixinHelper47.this.descendingEntryIterator();
      }
   }
}
