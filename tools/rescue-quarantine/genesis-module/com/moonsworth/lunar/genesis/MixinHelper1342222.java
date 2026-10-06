package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
abstract class MixinHelper1342222<K, V> extends MixinHelper134222<K, V> {
   MixinHelper1342222(SortedMap<K, Collection<V>> var1) {
      super(var1);
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

   @Override
   Set<K> createKeySet() {
      return this.HICORCIICHIROCHIRCOCROOIOIRHCH();
   }
}
