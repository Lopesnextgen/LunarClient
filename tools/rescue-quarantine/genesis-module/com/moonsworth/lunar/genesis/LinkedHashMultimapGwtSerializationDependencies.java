package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Map;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible(emulated = true)
abstract class LinkedHashMultimapGwtSerializationDependencies<K, V> extends AbstractListMultimap<K, V> {
   LinkedHashMultimapGwtSerializationDependencies(Map<K, Collection<V>> map1) {
      super(map1);
   }
}
