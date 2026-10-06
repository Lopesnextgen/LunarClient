package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;

@Annotation3
abstract class MixinHelper112_3<C extends Comparable> implements RangeSet<C> {
   @Override
   public boolean contains(C var1) {
      return this.method1((C)var1) != null;
   }

   @Override
   public abstract Range<C> method1(C var1);

   @Override
   public boolean isEmpty() {
      return this.asRanges().isEmpty();
   }

   @Override
   public void method8(Range<C> var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void method9(Range<C> var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void clear() {
      this.method9(Range.method16());
   }

   @Override
   public boolean method4(RangeSet<C> var1) {
      return this.enclosesAll(var1.asRanges());
   }

   @Override
   public void method10(RangeSet<C> var1) {
      this.addAll(var1.asRanges());
   }

   @Override
   public void method11(RangeSet<C> var1) {
      this.removeAll(var1.asRanges());
   }

   @Override
   public boolean method2(Range<C> var1) {
      return !this.method7(var1).isEmpty();
   }

   @Override
   public abstract boolean method3(Range<C> var1);

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 == this) {
         return true;
      } else if (var1 instanceof RangeSet) {
         RangeSet var2 = (RangeSet)var1;
         return this.asRanges().equals(var2.asRanges());
      } else {
         return false;
      }
   }

   @Override
   public final int hashCode() {
      return this.asRanges().hashCode();
   }

   @Override
   public final String toString() {
      return this.asRanges().toString();
   }
}
