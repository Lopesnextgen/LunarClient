package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Range;

class ImmutableRangeSet$AsSetSerializedForm<C extends Comparable> implements Serializable {
   private final ImmutableList<Range<C>> field1;
   private final MixinHelper40<C> field2;

   ImmutableRangeSet$AsSetSerializedForm(ImmutableList<Range<C>> abstractcollectioniterator31, MixinHelper40<C> mixinhelper402) {
      this.field1 = abstractcollectioniterator31;
      this.field2 = mixinhelper402;
   }

   Object readResolve() {
      return new MixinHelper1122(this.field1).method23(this.field2);
   }
}
