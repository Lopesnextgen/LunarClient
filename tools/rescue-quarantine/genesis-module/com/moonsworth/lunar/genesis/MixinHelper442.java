package com.moonsworth.lunar.genesis;

import java.util.SortedMap;
import com.google.common.collect.MapDifference;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public interface MixinHelper442<K, V> extends MapDifference<K, V> {
   SortedMap<K, V> entriesOnlyOnLeft();

   SortedMap<K, V> entriesOnlyOnRight();

   SortedMap<K, V> entriesInCommon();

   SortedMap<K, MapDifference.Extension<V>> entriesDiffering();
}
