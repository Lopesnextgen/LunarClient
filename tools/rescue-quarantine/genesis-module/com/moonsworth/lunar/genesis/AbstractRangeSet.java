package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;

@GwtIncompatible
abstract class AbstractRangeSet<C extends Comparable> implements RangeSet<C> {
   AbstractRangeSet() {
   }

   @Override
   public boolean contains(C value1) {
      return this.method1((C)value1) != null;
   }

   @Override
   public abstract Range<C> method1(C value1);

   @Override
   public boolean isEmpty() {
      return this.asRanges().isEmpty();
   }

   @Override
   public void method8(Range<C> serializablebase21) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void method9(Range<C> serializablebase21) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void clear() {
      this.method9(Range.method16());
   }

   @Override
   public boolean method4(RangeSet<C> mixinhelper11_31) {
      return this.enclosesAll(mixinhelper11_31.asRanges());
   }

   @Override
   public void method10(RangeSet<C> mixinhelper11_31) {
      this.addAll(mixinhelper11_31.asRanges());
   }

   @Override
   public void method11(RangeSet<C> mixinhelper11_31) {
      this.removeAll(mixinhelper11_31.asRanges());
   }

   @Override
   public boolean method2(Range<C> serializablebase21) {
      return !this.OHOOORICRHIIIIRHCICICOCHROICRC(serializablebase21).isEmpty();
   }

   @Override
   public abstract boolean method3(Range<C> serializablebase21);

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 == this) {
         return true;
      } else if (obj1 instanceof RangeSet) {
         RangeSet mixinhelper11_32 = (RangeSet)obj1;
         return this.asRanges().equals(mixinhelper11_32.asRanges());
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
