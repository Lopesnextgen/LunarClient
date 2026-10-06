package com.moonsworth.lunar.genesis;

import java.util.Map.Entry;
import com.google.common.collect.Multimap;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
interface MixinHelper135<K, V> extends Multimap<K, V> {
   Multimap<K, V> method1();

   PredicateExtension<? super Entry<K, V>> method4();
}
