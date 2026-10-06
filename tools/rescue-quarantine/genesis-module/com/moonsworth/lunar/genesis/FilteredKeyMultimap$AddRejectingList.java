package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import com.google.common.collect.ForwardingList;
import com.google.common.base.Preconditions;

class FilteredKeyMultimap$AddRejectingList<K, V> extends ForwardingList<V> {
   final K field1;

   FilteredKeyMultimap$AddRejectingList(K value1) {
      this.field1 = (K)value1;
   }

   public boolean add(V value1) {
      this.add(0, (V)value1);
      return true;
   }

   public void add(int number1, V value2) {
      Preconditions.checkPositionIndex(number1, 0);
      throw new IllegalArgumentException("Key does not satisfy predicate: " + this.field1);
   }

   public boolean addAll(Collection<? extends V> list1) {
      this.addAll(0, list1);
      return true;
   }

   @CanIgnoreReturnValue
   public boolean addAll(int number1, Collection<? extends V> list2) {
      Preconditions.checkNotNull(list2);
      Preconditions.checkPositionIndex(number1, 0);
      throw new IllegalArgumentException("Key does not satisfy predicate: " + this.field1);
   }

   protected List<V> delegate() {
      return Collections.emptyList();
   }
}
