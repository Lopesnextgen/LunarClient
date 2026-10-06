package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.SetMultimap;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Maps;

@GwtCompatible
abstract class AbstractMultimap<K, V> implements Multimap<K, V> {
   private transient @Nullable Collection<Entry<K, V>> entries;
   private transient @Nullable Set<K> keySet;
   private transient @Nullable Multiset<K> field1;
   private transient @Nullable Collection<V> values;
   private transient @Nullable Map<K, Collection<V>> asMap;

   AbstractMultimap() {
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public boolean containsValue(@Nullable Object obj1) {
      for (Collection list3 : this.asMap().values()) {
         if (list3.contains(obj1)) {
            return true;
         }
      }

      return false;
   }

   public boolean containsEntry(@Nullable Object obj1, @Nullable Object obj2) {
      Collection list3 = this.asMap().get(obj1);
      return list3 != null && list3.contains(obj2);
   }

   @CanIgnoreReturnValue
   public boolean remove(@Nullable Object obj1, @Nullable Object obj2) {
      Collection list3 = this.asMap().get(obj1);
      return list3 != null && list3.remove(obj2);
   }

   @CanIgnoreReturnValue
   public boolean put(@Nullable K value1, @Nullable V value2) {
      return this.get(value1).add(value2);
   }

   @CanIgnoreReturnValue
   public boolean putAll(@Nullable K value1, Iterable<? extends V> list2) {
      Preconditions.checkNotNull(list2);
      if (list2 instanceof Collection) {
         Collection list4 = (Collection)list2;
         return !list4.isEmpty() && this.get(value1).addAll(list4);
      } else {
         Iterator iterator3 = list2.iterator();
         return iterator3.hasNext() && Iterators.addAll(this.get(value1), iterator3);
      }
   }

   @CanIgnoreReturnValue
   public boolean method1(Multimap<? extends K, ? extends V> mixinhelper131) {
      boolean flag2 = false;

      for (Entry entry4 : mixinhelper131.entries()) {
         flag2 |= this.put((K)entry4.getKey(), (V)entry4.getValue());
      }

      return flag2;
   }

   @CanIgnoreReturnValue
   public Collection<V> replaceValues(@Nullable K value1, Iterable<? extends V> list2) {
      Preconditions.checkNotNull(list2);
      Collection list3 = this.removeAll(value1);
      this.putAll((K)value1, list2);
      return list3;
   }

   public Collection<Entry<K, V>> entries() {
      Collection list1 = this.entries;
      return list1 == null ? (this.entries = this.createEntries()) : list1;
   }

   abstract Collection<Entry<K, V>> createEntries();

   abstract Iterator<Entry<K, V>> entryIterator();

   Spliterator<Entry<K, V>> entrySpliterator() {
      return Spliterators.spliterator(this.entryIterator(), this.size(), this instanceof SetMultimap ? 1 : 0);
   }

   public Set<K> keySet() {
      Set set1 = this.keySet;
      return set1 == null ? (this.keySet = this.createKeySet()) : set1;
   }

   abstract Set<K> createKeySet();

   public Multiset<K> method2() {
      Multiset collectionextension1 = this.field1;
      return collectionextension1 == null ? (this.field1 = this.method3()) : collectionextension1;
   }

   abstract Multiset<K> method3();

   public Collection<V> values() {
      Collection list1 = this.values;
      return list1 == null ? (this.values = this.createValues()) : list1;
   }

   abstract Collection<V> createValues();

   Iterator<V> valueIterator() {
      return Maps.valueIterator(this.entries().iterator());
   }

   Spliterator<V> valueSpliterator() {
      return Spliterators.spliterator(this.valueIterator(), this.size(), 0);
   }

   public Map<K, Collection<V>> asMap() {
      Map map1 = this.asMap;
      return map1 == null ? (this.asMap = this.createAsMap()) : map1;
   }

   abstract Map<K, Collection<V>> createAsMap();

   @Override
   public boolean equals(@Nullable Object obj1) {
      return MixinHelper37.method37(this, obj1);
   }

   @Override
   public int hashCode() {
      return this.asMap().hashCode();
   }

   @Override
   public String toString() {
      return this.asMap().toString();
   }
}
