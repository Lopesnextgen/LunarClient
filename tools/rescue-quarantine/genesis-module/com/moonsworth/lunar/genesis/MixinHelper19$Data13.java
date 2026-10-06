package com.moonsworth.lunar.genesis;

import java.util.SortedMap;
import com.google.common.collect.MapDifference;

class MixinHelper19$Data13<K, V> extends MixinHelper19$Data36<K, V> implements MixinHelper442<K, V> {
   MixinHelper19$Data13(SortedMap<K, V> var1, SortedMap<K, V> var2, SortedMap<K, V> var3, SortedMap<K, MapDifference.Extension<V>> var4) {
      super(var1, var2, var3, var4);
   }

   @Override
   public SortedMap<K, MapDifference.Extension<V>> entriesDiffering() {
      return (SortedMap<K, MapDifference.Extension<V>>)super.entriesDiffering();
   }

   @Override
   public SortedMap<K, V> entriesInCommon() {
      return (SortedMap<K, V>)super.entriesInCommon();
   }

   @Override
   public SortedMap<K, V> entriesOnlyOnLeft() {
      return (SortedMap<K, V>)super.entriesOnlyOnLeft();
   }

   @Override
   public SortedMap<K, V> entriesOnlyOnRight() {
      return (SortedMap<K, V>)super.entriesOnlyOnRight();
   }
}
