package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.collect.ImmutableMap;
import com.google.common.annotations.GwtIncompatible;

@GwtIncompatible
class ImmutableMapKeySet$KeySetSerializedForm<K, V> implements Serializable {
   final ImmutableMap<K, V> field1;
   private static final long field2 = 0L;

   ImmutableMapKeySet$KeySetSerializedForm(ImmutableMap<K, V> serializableiterator1) {
      this.field1 = serializableiterator1;
   }

   Object readResolve() {
      return this.field1.method12();
   }
}
