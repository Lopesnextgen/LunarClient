package com.moonsworth.lunar.genesis;

import java.util.Collection;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.TreeRangeSet;
import com.google.common.collect.ForwardingCollection;
import com.google.common.collect.Range;
import com.google.common.collect.Sets;

final class MixinHelper1123$Data2 extends ForwardingCollection<Range<C>> implements Set<Range<C>> {
   final Collection<Range<C>> field1;

   MixinHelper1123$Data2(TreeRangeSet var1, Collection var2) {
      this.field2 = var1;
      this.field1 = var2;
   }

   @Override
   protected Collection<Range<C>> delegate() {
      return this.field1;
   }

   @Override
   public int hashCode() {
      return Sets.hashCodeImpl(this);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      return Sets.equalsImpl(this, var1);
   }
}
