package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.TreeRangeSet;
import com.google.common.base.Preconditions;
import com.google.common.collect.Range;
import com.google.common.collect.ImmutableRangeSet;
import com.google.common.collect.RangeSet;

final class MixinHelper1123$Data6 extends TreeRangeSet<C> {
   private final Range<C> field3;

   MixinHelper1123$Data6(TreeRangeSet var1, Range var2) {
      super(new MixinHelper1123$Data5(Range.method16(), var2, var1.field1));
      this.field4 = var1;
      this.field3 = var2;
   }

   @Override
   public boolean method3(Range<C> var1) {
      if (!this.field3.isEmpty() && this.field3.method21(var1)) {
         Range var2 = TreeRangeSet.method17(this.field4, var1);
         return var2 != null && !var2.method23(this.field3).isEmpty();
      } else {
         return false;
      }
   }

   @Override
   public @Nullable Range<C> method1(Comparable var1) {
      if (!this.field3.contains(var1)) {
         return null;
      }

      Range var2 = this.field4.method1(var1);
      return var2 == null ? null : var2.method23(this.field3);
   }

   @Override
   public void method8(Range<C> var1) {
      Preconditions.checkArgument(this.field3.method21(var1), "Cannot add range %s to subRangeSet(%s)", var1, this.field3);
      super.method8(var1);
   }

   @Override
   public void method9(Range<C> var1) {
      if (var1.method22(this.field3)) {
         this.field4.method9(var1.method23(this.field3));
      }
   }

   @Override
   public boolean contains(Comparable var1) {
      return this.field3.contains(var1) && this.field4.contains(var1);
   }

   @Override
   public void clear() {
      this.field4.method9(this.field3);
   }

   @Override
   public RangeSet<C> method7(Range<C> var1) {
      if (var1.method21(this.field3)) {
         return this;
      } else {
         return var1.method22(this.field3) ? new MixinHelper1123$Data6(this, this.field3.method23(var1)) : ImmutableRangeSet.method1();
      }
   }
}
