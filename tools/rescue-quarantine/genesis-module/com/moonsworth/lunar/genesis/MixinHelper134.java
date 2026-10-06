package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multimap;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;

@GwtCompatible
abstract class MixinHelper134<K, V> implements Multimap<K, V> {
   private transient @Nullable Collection<Entry<K, V>> entries;
   private transient @Nullable Set<K> keySet;
   private transient @Nullable Multiset<K> field1;
   private transient @Nullable Collection<V> values;
   private transient @Nullable Map<K, Collection<V>> asMap;

   @Override
   public boolean isEmpty() {
      return this.size() == 0;
   }

   @Override
   public boolean containsValue(@Nullable Object var1) {
      for (Collection var3 : this.asMap().values()) {
         if (var3.contains(var1)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean containsEntry(@Nullable Object var1, @Nullable Object var2) {
      Collection var3 = this.asMap().get(var1);
      return var3 != null && var3.contains(var2);
   }

   @CanIgnoreReturnValue
   @Override
   public boolean remove(@Nullable Object var1, @Nullable Object var2) {
      Collection var3 = this.asMap().get(var1);
      return var3 != null && var3.remove(var2);
   }

   @CanIgnoreReturnValue
   @Override
   public boolean put(@Nullable K var1, @Nullable V var2) {
      return this.get((K)var1).add((V)var2);
   }

   @CanIgnoreReturnValue
   @Override
   public boolean putAll(@Nullable K var1, Iterable<? extends V> var2) {
      Preconditions.checkNotNull(var2);
      if (var2 instanceof Collection) {
         Collection var4 = (Collection)var2;
         return !var4.isEmpty() && this.get((K)var1).addAll(var4);
      } else {
         Iterator var3 = var2.iterator();
         return var3.hasNext() && Iterators.addAll(this.get((K)var1), var3);
      }
   }

   @CanIgnoreReturnValue
   @Override
   public boolean method1(Multimap<? extends K, ? extends V> var1) {
      boolean var2 = false;

      for (Entry var4 : var1.entries()) {
         var2 |= this.put((K)var4.getKey(), (V)var4.getValue());
      }

      return var2;
   }

   @CanIgnoreReturnValue
   @Override
   public Collection<V> replaceValues(@Nullable K var1, Iterable<? extends V> var2) {
      Preconditions.checkNotNull(var2);
      Collection var3 = this.removeAll(var1);
      this.putAll((K)var1, var2);
      return var3;
   }

   @Override
   public Collection<Entry<K, V>> entries() {
      Collection var1 = this.entries;
      return var1 == null ? (this.entries = this.createEntries()) : var1;
   }

   abstract Collection<Entry<K, V>> createEntries();

   abstract Iterator<Entry<K, V>> entryIterator();

   Spliterator<Entry<K, V>> entrySpliterator() {
      return Spliterators.spliterator(this.entryIterator(), this.size(), this instanceof MixinHelper132_2 ? 1 : 0);
   }

   @Override
   public Set<K> keySet() {
      Set var1 = this.keySet;
      return var1 == null ? (this.keySet = this.createKeySet()) : var1;
   }

   abstract Set<K> createKeySet();

   @Override
   public Multiset<K> method2() {
      Multiset var1 = this.field1;
      return var1 == null ? (this.field1 = this.method3()) : var1;
   }

   abstract Multiset<K> method3();

   @Override
   public Collection<V> values() {
      Collection var1 = this.values;
      return var1 == null ? (this.values = this.createValues()) : var1;
   }

   abstract Collection<V> createValues();

   Iterator<V> valueIterator() {
      return Maps.valueIterator(this.entries().iterator());
   }

   Spliterator<V> valueSpliterator() {
      return Spliterators.spliterator(this.valueIterator(), this.size(), 0);
   }

   @Override
   public Map<K, Collection<V>> asMap() {
      Map var1 = this.asMap;
      return var1 == null ? (this.asMap = this.createAsMap()) : var1;
   }

   abstract Map<K, Collection<V>> createAsMap();

   @Override
   public boolean equals(@Nullable Object var1) {
      return MixinHelper37.method37(this, var1);
   }

   @Override
   public int hashCode() {
      return this.asMap().hashCode();
   }

   @Override
   public String toString() {
      return this.asMap().toString();
   }

   class Data2 extends MixinHelper134<K, V>.Data3 implements Set<Entry<K, V>> {
      @Override
      public int hashCode() {
         return Sets.hashCodeImpl(this);
      }

      @Override
      public boolean equals(@Nullable Object var1) {
         return Sets.equalsImpl(this, var1);
      }
   }

   class Data3 extends MixinHelper37.Data9<K, V> {
      @Override
      Multimap<K, V> method1() {
         return MixinHelper134.this;
      }

      @Override
      public Iterator<Entry<K, V>> iterator() {
         return MixinHelper134.this.entryIterator();
      }

      @Override
      public Spliterator<Entry<K, V>> spliterator() {
         return MixinHelper134.this.entrySpliterator();
      }
   }

   class Data4 extends AbstractCollection<V> {
      @Override
      public Iterator<V> iterator() {
         return MixinHelper134.this.valueIterator();
      }

      @Override
      public Spliterator<V> spliterator() {
         return MixinHelper134.this.valueSpliterator();
      }

      @Override
      public int size() {
         return MixinHelper134.this.size();
      }

      @Override
      public boolean contains(@Nullable Object var1) {
         return MixinHelper134.this.containsValue(var1);
      }

      @Override
      public void clear() {
         MixinHelper134.this.clear();
      }
   }
}
