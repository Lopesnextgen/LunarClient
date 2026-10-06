package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableCollection;

@GwtIncompatible
class ImmutableAsList$SerializedForm implements Serializable {
   final ImmutableCollection<?> field1;
   private static final long field2 = 0L;

   ImmutableAsList$SerializedForm(ImmutableCollection<?> abstractcollectioniterator1) {
      this.field1 = abstractcollectioniterator1;
   }

   Object readResolve() {
      return this.field1.method2();
   }
}
