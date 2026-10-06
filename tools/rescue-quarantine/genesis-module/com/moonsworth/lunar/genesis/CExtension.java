package com.moonsworth.lunar.genesis;

import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import com.google.common.collect.Table;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public interface CExtension<R, C, V> extends Table<R, C, V> {
   SortedSet<R> rowKeySet();

   SortedMap<R, Map<C, V>> rowMap();
}
