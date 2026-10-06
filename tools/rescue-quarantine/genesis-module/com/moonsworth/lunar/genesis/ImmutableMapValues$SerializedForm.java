package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.collect.ImmutableMap;
import com.google.common.annotations.GwtIncompatible;

@GwtIncompatible
class ImmutableMapValues$SerializedForm<V> implements Serializable {
   final ImmutableMap<?, V> field1;
   private static final long field2 = 0L;

   ImmutableMapValues$SerializedForm(ImmutableMap<?, V> serializableiterator1) {
      this.field1 = serializableiterator1;
   }

   Object readResolve() {
      return this.field1.method17();
   }
}
