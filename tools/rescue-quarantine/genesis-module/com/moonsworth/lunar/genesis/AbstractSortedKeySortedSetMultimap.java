package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
abstract class AbstractSortedKeySortedSetMultimap<K, V> extends MixinHelper134222<K, V> {
   AbstractSortedKeySortedSetMultimap(SortedMap<K, Collection<V>> sortedmap1) {
      super(sortedmap1);
   }

   public SortedMap<K, Collection<V>> asMap() {
      return (SortedMap<K, Collection<V>>)super.asMap();
   }

   SortedMap<K, Collection<V>> backingMap() {
      return (SortedMap<K, Collection<V>>)super.backingMap();
   }

   public SortedSet<K> keySet() {
      return (SortedSet<K>)super.keySet();
   }

   Set<K> createKeySet() {
      return this.HICORCIICHIROCHIRCOCROOIOIRHCH();
   }
}
