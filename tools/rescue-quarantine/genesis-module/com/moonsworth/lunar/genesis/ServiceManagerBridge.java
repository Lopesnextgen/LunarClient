package com.moonsworth.lunar.genesis;

import java.util.Map.Entry;
import com.google.common.collect.Multimap;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Predicate;

@GwtCompatible
interface ServiceManagerBridge<K, V> extends Multimap<K, V> {
   Multimap<K, V> method1();

   Predicate<? super Entry<K, V>> method4();
}
