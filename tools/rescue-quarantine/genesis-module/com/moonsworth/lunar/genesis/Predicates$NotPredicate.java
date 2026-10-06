package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Predicate;
import com.google.common.base.Preconditions;

class Predicates$NotPredicate<T> implements Predicate<T>, Serializable {
   final Predicate<T> field1;
   private static final long field2 = 0L;

   Predicates$NotPredicate(Predicate<T> predicateextension1) {
      this.field1 = (Predicate<T>)Preconditions.checkNotNull(predicateextension1);
   }

   public boolean apply(@Nullable T value1) {
      return !this.field1.apply(value1);
   }

   @Override
   public int hashCode() {
      return ~this.field1.hashCode();
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof Predicates$NotPredicate) {
         Predicates$NotPredicate mixinhelper20$data102 = (Predicates$NotPredicate)obj1;
         return this.field1.equals(mixinhelper20$data102.field1);
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return "Predicates.not(" + this.field1 + ")";
   }
}
