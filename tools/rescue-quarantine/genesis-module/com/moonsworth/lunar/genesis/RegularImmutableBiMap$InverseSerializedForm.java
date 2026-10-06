package com.moonsworth.lunar.genesis;

import java.io.Serializable;

class RegularImmutableBiMap$InverseSerializedForm<K, V> implements Serializable {
   private final SerializableIterator52<K, V> field1;
   private static final long field2 = 1L;

   RegularImmutableBiMap$InverseSerializedForm(SerializableIterator52<K, V> serializableiterator521) {
      this.field1 = serializableiterator521;
   }

   Object readResolve() {
      return this.field1.method11();
   }
}
