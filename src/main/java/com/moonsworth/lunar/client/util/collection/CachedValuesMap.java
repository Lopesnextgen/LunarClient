package com.moonsworth.lunar.client.util.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CachedValuesMap<K, V> implements Map<K, V> {
   private final Map<K, V> field1 = new HashMap<>();
   private Collection<V> values;

   public CachedValuesMap() {
   }

   @Override
   public int size() {
      return this.values == null ? this.field1.size() : this.values.size();
   }

   @Override
   public boolean isEmpty() {
      return this.values == null ? this.field1.isEmpty() : this.values.isEmpty();
   }

   @Override
   public boolean containsKey(Object obj1) {
      return this.field1.containsKey(obj1);
   }

   @Override
   public boolean containsValue(Object obj1) {
      return this.values == null ? this.field1.containsValue(obj1) : this.values.contains(obj1);
   }

   @Override
   public V get(Object obj1) {
      return this.field1.get(obj1);
   }

   @Nullable
   @Override
   public V put(K k, V v) {
      this.values = null;
      return this.field1.put((K)k, (V)v);
   }

   @Override
   public V remove(Object obj1) {
      this.values = null;
      return this.field1.remove(obj1);
   }

   @Override
   public void putAll(@NotNull Map<? extends K, ? extends V> map1) {
      this.values = null;
      this.field1.putAll(map1);
   }

   @Override
   public void clear() {
      this.values = null;
      this.field1.clear();
   }

   @NotNull
   @Override
   public Set<K> keySet() {
      return this.field1.keySet();
   }

   @NotNull
   @Override
   public Collection<V> values() {
      if (this.values == null) {
         this.values = Collections.unmodifiableList(new ArrayList<>(this.field1.values()));
      }

      return this.values;
   }

   @NotNull
   @Override
   public Set<Entry<K, V>> entrySet() {
      return this.field1.entrySet();
   }
}
