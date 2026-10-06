package com.moonsworth.lunar.genesis;
import com.google.common.collect.TreeRangeSet;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;

final class MixinHelper1123$Data extends TreeRangeSet<C> {
   MixinHelper1123$Data(TreeRangeSet var1) {
      super(new MixinHelper1123$Data3(var1.field1));
      this.field3 = var1;
   }

   @Override
   public void method8(Range<C> var1) {
      this.field3.method9(var1);
   }

   @Override
   public void method9(Range<C> var1) {
      this.field3.method8(var1);
   }

   @Override
   public boolean contains(Comparable var1) {
      return !this.field3.contains(var1);
   }

   @Override
   public RangeSet<C> method6() {
      return this.field3;
   }
}
