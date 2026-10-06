package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.collect.HashBiMap;

final class ImmutableRangeSet$SerializedForm<K, V> implements Serializable {
   private final HashBiMap<K, V> field1;

   ImmutableRangeSet$SerializedForm(HashBiMap<K, V> mixinhelper451) {
      this.field1 = mixinhelper451;
   }

   Object readResolve() {
      return this.field1.method2();
   }
}
