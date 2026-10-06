package com.moonsworth.lunar.genesis;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Spliterator;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Function;

@GwtIncompatible
final class Maps$NavigableAsMapView<K, V> extends MixinHelper47<K, V> {
   private final NavigableSet<K> field1;
   private final Function<? super K, V> field2;

   Maps$NavigableAsMapView(NavigableSet<K> navigableset1, Function<? super K, V> mixinhelper24_22) {
      this.field1 = (NavigableSet<K>)Preconditions.checkNotNull(navigableset1);
      this.field2 = (Function<? super K, V>)Preconditions.checkNotNull(mixinhelper24_22);
   }

   public NavigableMap<K, V> subMap(K value1, boolean flag2, K value3, boolean flag4) {
      return MixinHelper19_3.method10(this.field1.subSet((K)value1, flag2, (K)value3, flag4), this.field2);
   }

   public NavigableMap<K, V> headMap(K value1, boolean flag2) {
      return MixinHelper19_3.method10(this.field1.headSet((K)value1, flag2), this.field2);
   }

   public NavigableMap<K, V> tailMap(K value1, boolean flag2) {
      return MixinHelper19_3.method10(this.field1.tailSet((K)value1, flag2), this.field2);
   }

   public Comparator<? super K> comparator() {
      return this.field1.comparator();
   }

   public @Nullable V get(@Nullable Object obj1) {
      return this.getOrDefault(obj1, null);
   }

   public @Nullable V getOrDefault(@Nullable Object obj1, @Nullable V value2) {
      if (MixinHelper39.safeContains(this.field1, obj1)) {
         Object obj3 = obj1;
         return (V)this.field2.apply(obj3);
      } else {
         return (V)value2;
      }
   }

   public void clear() {
      this.field1.clear();
   }

   Iterator<Entry<K, V>> entryIterator() {
      return MixinHelper19_3.method11(this.field1, this.field2);
   }

   Spliterator<Entry<K, V>> entrySpliterator() {
      return MixinHelper3_5.map(this.field1.spliterator(), arg1 -> MixinHelper19_3.immutableEntry(arg1, this.field2.apply(arg1)));
   }

   public void forEach(BiConsumer<? super K, ? super V> biconsumer1) {
      this.field1.forEach(arg2 -> biconsumer1.accept(arg2, this.field2.apply(arg2)));
   }

   Iterator<Entry<K, V>> descendingEntryIterator() {
      return this.descendingMap().entrySet().iterator();
   }

   public NavigableSet<K> navigableKeySet() {
      return MixinHelper19_3.access$400(this.field1);
   }

   public int size() {
      return this.field1.size();
   }

   public NavigableMap<K, V> descendingMap() {
      return MixinHelper19_3.method10(this.field1.descendingSet(), this.field2);
   }
}
