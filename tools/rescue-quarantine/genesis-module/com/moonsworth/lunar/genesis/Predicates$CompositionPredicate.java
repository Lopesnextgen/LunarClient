package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Predicate;
import com.google.common.base.Preconditions;
import com.google.common.base.Function;

class Predicates$CompositionPredicate<A, B> implements Predicate<A>, Serializable {
   final Predicate<B> field1;
   final Function<A, ? extends B> field2;
   private static final long field3 = 0L;

   private Predicates$CompositionPredicate(Predicate<B> predicateextension1, Function<A, ? extends B> mixinhelper24_22) {
      this.field1 = (Predicate<B>)Preconditions.checkNotNull(predicateextension1);
      this.field2 = (Function<A, ? extends B>)Preconditions.checkNotNull(mixinhelper24_22);
   }

   public boolean apply(@Nullable A value1) {
      return this.field1.apply(this.field2.apply(value1));
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (!(obj1 instanceof Predicates$CompositionPredicate)) {
         return false;
      }

      Predicates$CompositionPredicate mixinhelper20$data32 = (Predicates$CompositionPredicate)obj1;
      return this.field2.equals(mixinhelper20$data32.field2) && this.field1.equals(mixinhelper20$data32.field1);
   }

   @Override
   public int hashCode() {
      return this.field2.hashCode() ^ this.field1.hashCode();
   }

   @Override
   public String toString() {
      return this.field1 + "(" + this.field2 + ")";
   }
}
