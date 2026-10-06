package com.moonsworth.lunar.genesis;
import com.google.common.collect.SetMultimap;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
interface FilteredSetMultimap<K, V> extends SetMultimap<K, V>, ServiceManagerBridge<K, V> {
   SetMultimap<K, V> method5();
}
