package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Range;
import com.google.common.collect.ImmutableRangeSet;

final class ImmutableRangeMap$SerializedForm<C extends Comparable> implements Serializable {
   private final ImmutableList<Range<C>> field1;

   ImmutableRangeMap$SerializedForm(ImmutableList<Range<C>> abstractcollectioniterator31) {
      this.field1 = abstractcollectioniterator31;
   }

   Object readResolve() {
      if (this.field1.isEmpty()) {
         return ImmutableRangeSet.method1();
      } else {
         return this.field1.equals(ImmutableList.method2(Range.method16()))
            ? ImmutableRangeSet.method3()
            : new ImmutableRangeSet(this.field1);
      }
   }
}
