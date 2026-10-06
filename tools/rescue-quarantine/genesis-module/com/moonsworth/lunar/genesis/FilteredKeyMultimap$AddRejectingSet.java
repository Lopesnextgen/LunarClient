package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import com.google.common.collect.ForwardingSet;
import com.google.common.base.Preconditions;

class FilteredKeyMultimap$AddRejectingSet<K, V> extends ForwardingSet<V> {
   final K field1;

   FilteredKeyMultimap$AddRejectingSet(K value1) {
      this.field1 = (K)value1;
   }

   public boolean add(V value1) {
      throw new IllegalArgumentException("Key does not satisfy predicate: " + this.field1);
   }

   public boolean addAll(Collection<? extends V> list1) {
      Preconditions.checkNotNull(list1);
      throw new IllegalArgumentException("Key does not satisfy predicate: " + this.field1);
   }

   protected Set<V> delegate() {
      return Collections.emptySet();
   }
}
