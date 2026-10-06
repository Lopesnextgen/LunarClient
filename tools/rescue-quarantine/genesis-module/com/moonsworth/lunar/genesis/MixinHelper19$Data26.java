package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Spliterator;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Maps;
import com.google.common.base.Preconditions;

@Annotation3
final class MixinHelper19$Data26<K, V> extends MixinHelper47<K, V> {
   private final NavigableSet<K> field1;
   private final MixinHelper24_2<? super K, V> field2;

   MixinHelper19$Data26(NavigableSet<K> var1, MixinHelper24_2<? super K, V> var2) {
      this.field1 = Preconditions.checkNotNull(var1);
      this.field2 = Preconditions.checkNotNull(var2);
   }

   @Override
   public NavigableMap<K, V> subMap(K var1, boolean var2, K var3, boolean var4) {
      return Maps.method10(this.field1.subSet((K)var1, var2, (K)var3, var4), this.field2);
   }

   @Override
   public NavigableMap<K, V> headMap(K var1, boolean var2) {
      return Maps.method10(this.field1.headSet((K)var1, var2), this.field2);
   }

   @Override
   public NavigableMap<K, V> tailMap(K var1, boolean var2) {
      return Maps.method10(this.field1.tailSet((K)var1, var2), this.field2);
   }

   @Override
   public java.util.Comparator<? super K> comparator() {
      return this.field1.comparator();
   }

   @Override
   public @Nullable V get(@Nullable Object var1) {
      return this.getOrDefault(var1, null);
   }

   @Override
   public @Nullable V getOrDefault(@Nullable Object var1, @Nullable V var2) {
      if (MixinHelper39.safeContains(this.field1, var1)) {
         Object var3 = var1;
         return this.field2.apply((K)var3);
      } else {
         return (V)var2;
      }
   }

   @Override
   public void clear() {
      this.field1.clear();
   }

   @Override
   Iterator<Entry<K, V>> entryIterator() {
      return Maps.method11(this.field1, this.field2);
   }

   @Override
   Spliterator<Entry<K, V>> entrySpliterator() {
      return MixinHelper3_5.map(this.field1.spliterator(), var1 -> Maps.immutableEntry((K)var1, this.field2.apply(var1)));
   }

   @Override
   public void forEach(BiConsumer<? super K, ? super V> var1) {
      this.field1.forEach(var2 -> var1.accept(var2, this.field2.apply(var2)));
   }

   @Override
   Iterator<Entry<K, V>> descendingEntryIterator() {
      return this.descendingMap().entrySet().iterator();
   }

   @Override
   public NavigableSet<K> navigableKeySet() {
      return Maps.access$400(this.field1);
   }

   @Override
   public int size() {
      return this.field1.size();
   }

   @Override
   public NavigableMap<K, V> descendingMap() {
      return Maps.method10(this.field1.descendingSet(), this.field2);
   }
}
