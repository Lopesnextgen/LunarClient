package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import com.google.common.collect.Iterators;
import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingMapEntry;

final class MutableTypeToInstanceMap$UnmodifiableEntry<K, V> extends ForwardingMapEntry<K, V> {
   private final Entry<K, V> field1;

   static <K, V> Set<Entry<K, V>> transformEntries(Set<Entry<K, V>> set0) {
      return new Data5$1(set0);
   }

   private static <K, V> Iterator<Entry<K, V>> transformEntries(Iterator<Entry<K, V>> iterator0) {
      return Iterators.method17(iterator0, new Data5$2());
   }

   private MutableTypeToInstanceMap$UnmodifiableEntry(Entry<K, V> entry1) {
      this.field1 = (Entry<K, V>)Preconditions.checkNotNull(entry1);
   }

   protected Entry<K, V> delegate() {
      return this.field1;
   }

   public V setValue(V value1) {
      throw new UnsupportedOperationException();
   }
}
