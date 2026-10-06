package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableMultiset;

@GwtIncompatible
class ImmutableMultimap$KeysSerializedForm<E> implements Serializable {
   final ImmutableMultiset<E> field1;

   ImmutableMultimap$KeysSerializedForm(ImmutableMultiset<E> abstractcollectioniterator421) {
      this.field1 = abstractcollectioniterator421;
   }

   Object readResolve() {
      return this.field1.method16();
   }
}
