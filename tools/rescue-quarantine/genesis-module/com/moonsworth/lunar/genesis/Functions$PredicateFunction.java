package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Predicate;
import com.google.common.base.Preconditions;
import com.google.common.base.Function;

class Functions$PredicateFunction<T> implements Function<T, Boolean>, Serializable {
   private final Predicate<T> field1;
   private static final long field2 = 0L;

   private Functions$PredicateFunction(Predicate<T> predicateextension1) {
      this.field1 = Preconditions.checkNotNull(predicateextension1);
   }

   public Boolean apply(@Nullable T value1) {
      return this.field1.apply(value1);
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof Functions$PredicateFunction) {
         Functions$PredicateFunction mixinhelper6$data242 = (Functions$PredicateFunction)obj1;
         return this.field1.equals(mixinhelper6$data242.field1);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return this.field1.hashCode();
   }

   @Override
   public String toString() {
      return "Functions.forPredicate(" + this.field1 + ")";
   }
}
