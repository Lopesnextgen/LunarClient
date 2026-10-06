package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Predicate;

class Predicates$OrPredicate<T> implements Predicate<T>, Serializable {
   private final List<? extends Predicate<? super T>> field1;
   private static final long field2 = 0L;

   private Predicates$OrPredicate(List<? extends Predicate<? super T>> list1) {
      this.field1 = list1;
   }

   public boolean apply(@Nullable T value1) {
      for (int index2 = 0; index2 < this.field1.size(); index2++) {
         if (this.field1.get(index2).apply(value1)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public int hashCode() {
      return this.field1.hashCode() + 87855567;
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof Predicates$OrPredicate) {
         Predicates$OrPredicate mixinhelper20$data22 = (Predicates$OrPredicate)obj1;
         return this.field1.equals(mixinhelper20$data22.field1);
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return MixinHelper20.access$800("or", this.field1);
   }
}
