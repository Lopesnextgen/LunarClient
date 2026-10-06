package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.collect.ImmutableMap;
import com.google.common.annotations.GwtIncompatible;

@GwtIncompatible
class ImmutableMapEntrySet$EntrySetSerializedForm<K> implements Serializable {
   final ImmutableMap<K, ?> field1;
   private static final long field2 = 0L;

   ImmutableMapEntrySet$EntrySetSerializedForm(ImmutableMap<K, ?> serializableiterator1) {
      this.field1 = serializableiterator1;
   }

   Object readResolve() {
      return this.field1.method14();
   }
}
