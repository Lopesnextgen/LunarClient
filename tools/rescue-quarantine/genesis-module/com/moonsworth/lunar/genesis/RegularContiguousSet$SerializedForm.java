package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Range;

@GwtIncompatible
final class RegularContiguousSet$SerializedForm<C extends Comparable> implements Serializable {
   final Range<C> field1;
   final MixinHelper40<C> field2;

   private RegularContiguousSet$SerializedForm(Range<C> serializablebase21, MixinHelper40<C> mixinhelper402) {
      this.field1 = serializablebase21;
      this.field2 = mixinhelper402;
   }

   private Object readResolve() {
      return new AbstractCollectionIterator56222(this.field1, this.field2);
   }
}
