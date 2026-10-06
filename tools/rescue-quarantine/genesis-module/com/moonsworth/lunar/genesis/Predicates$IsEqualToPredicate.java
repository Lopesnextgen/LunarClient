package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Predicate;

class Predicates$IsEqualToPredicate<T> implements Predicate<T>, Serializable {
   private final T field1;
   private static final long field2 = 0L;

   private Predicates$IsEqualToPredicate(T value1) {
      this.field1 = (T)value1;
   }

   public boolean apply(T value1) {
      return this.field1.equals(value1);
   }

   @Override
   public int hashCode() {
      return this.field1.hashCode();
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof Predicates$IsEqualToPredicate) {
         Predicates$IsEqualToPredicate mixinhelper20$data92 = (Predicates$IsEqualToPredicate)obj1;
         return this.field1.equals(mixinhelper20$data92.field1);
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return "Predicates.equalTo(" + this.field1 + ")";
   }
}
