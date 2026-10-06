package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Map;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible(emulated = true)
abstract class HashMultimapGwtSerializationDependencies<K, V> extends AbstractSetMultimap<K, V> {
   HashMultimapGwtSerializationDependencies(Map<K, Collection<V>> map1) {
      super(map1);
   }
}
