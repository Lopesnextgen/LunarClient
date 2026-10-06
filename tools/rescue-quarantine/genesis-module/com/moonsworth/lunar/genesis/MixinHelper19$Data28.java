package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;

@Annotation3
class MixinHelper19$Data28<K, V> extends MixinHelper47<K, V> {
   private final NavigableMap<K, V> field1;
   private final PredicateExtension<? super Entry<K, V>> field2;
   private final Map<K, V> field3;

   MixinHelper19$Data28(NavigableMap<K, V> var1, PredicateExtension<? super Entry<K, V>> var2) {
      this.field1 = Preconditions.checkNotNull(var1);
      this.field2 = var2;
      this.field3 = new MixinHelper19$Data10<>(var1, var2);
   }

   @Override
   public java.util.Comparator<? super K> comparator() {
      return this.field1.comparator();
   }

   @Override
   public NavigableSet<K> navigableKeySet() {
      return new MixinHelper19$Data37<K, V>(this) {
         @Override
         public boolean removeAll(Collection<?> var1) {
            return MixinHelper19$Data10.method1(MixinHelper19$Data28.this.field1, MixinHelper19$Data28.this.field2, var1);
         }

         @Override
         public boolean retainAll(Collection<?> var1) {
            return MixinHelper19$Data10.method2(MixinHelper19$Data28.this.field1, MixinHelper19$Data28.this.field2, var1);
         }
      };
   }

   @Override
   public Collection<V> values() {
      return new MixinHelper19$Data34<>(this, this.field1, this.field2);
   }

   @Override
   Iterator<Entry<K, V>> entryIterator() {
      return Iterators.method9(this.field1.entrySet().iterator(), this.field2);
   }

   @Override
   Iterator<Entry<K, V>> descendingEntryIterator() {
      return Iterators.method9(this.field1.descendingMap().entrySet().iterator(), this.field2);
   }

   @Override
   public int size() {
      return this.field3.size();
   }

   @Override
   public boolean isEmpty() {
      return !Iterables.method5(this.field1.entrySet(), this.field2);
   }

   @Override
   public @Nullable V get(@Nullable Object var1) {
      return this.field3.get(var1);
   }

   @Override
   public boolean containsKey(@Nullable Object var1) {
      return this.field3.containsKey(var1);
   }

   @Override
   public V put(K var1, V var2) {
      return this.field3.put((K)var1, (V)var2);
   }

   @Override
   public V remove(@Nullable Object var1) {
      return this.field3.remove(var1);
   }

   @Override
   public void putAll(Map<? extends K, ? extends V> var1) {
      this.field3.putAll(var1);
   }

   @Override
   public void clear() {
      this.field3.clear();
   }

   @Override
   public Set<Entry<K, V>> entrySet() {
      return this.field3.entrySet();
   }

   @Override
   public Entry<K, V> pollFirstEntry() {
      return Iterables.method3(this.field1.entrySet(), this.field2);
   }

   @Override
   public Entry<K, V> pollLastEntry() {
      return Iterables.method3(this.field1.descendingMap().entrySet(), this.field2);
   }

   @Override
   public NavigableMap<K, V> descendingMap() {
      return Maps.method44(this.field1.descendingMap(), this.field2);
   }

   @Override
   public NavigableMap<K, V> subMap(K var1, boolean var2, K var3, boolean var4) {
      return Maps.method44(this.field1.subMap((K)var1, var2, (K)var3, var4), this.field2);
   }

   @Override
   public NavigableMap<K, V> headMap(K var1, boolean var2) {
      return Maps.method44(this.field1.headMap((K)var1, var2), this.field2);
   }

   @Override
   public NavigableMap<K, V> tailMap(K var1, boolean var2) {
      return Maps.method44(this.field1.tailMap((K)var1, var2), this.field2);
   }
}
