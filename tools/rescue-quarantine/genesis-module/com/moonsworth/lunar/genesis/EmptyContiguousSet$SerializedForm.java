package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.annotations.GwtIncompatible;

@GwtIncompatible
final class EmptyContiguousSet$SerializedForm<C extends Comparable> implements Serializable {
   private final MixinHelper40<C> field1;
   private static final long field2 = 0L;

   private EmptyContiguousSet$SerializedForm(MixinHelper40<C> mixinhelper401) {
      this.field1 = mixinhelper401;
   }

   private Object readResolve() {
      return new AbstractCollectionIterator56223(this.field1);
   }
}
