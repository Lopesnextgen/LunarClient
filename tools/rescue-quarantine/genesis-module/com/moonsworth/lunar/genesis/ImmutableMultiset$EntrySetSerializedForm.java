package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableMultimap;

@GwtIncompatible
final class ImmutableMultiset$EntrySetSerializedForm implements Serializable {
   final ImmutableMultimap<?, ?> field1;

   ImmutableMultiset$EntrySetSerializedForm(ImmutableMultimap<?, ?> mixinhelper134521) {
      this.field1 = mixinhelper134521;
   }

   Object readResolve() {
      return this.field1.method20();
   }
}
