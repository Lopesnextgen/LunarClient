package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Collection;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Predicate;
import com.google.common.base.Preconditions;

class Predicates$InPredicate<T> implements Predicate<T>, Serializable {
   private final Collection<?> field1;
   private static final long field2 = 0L;

   private Predicates$InPredicate(Collection<?> list1) {
      this.field1 = (Collection<?>)Preconditions.checkNotNull(list1);
   }

   public boolean apply(@Nullable T value1) {
      try {
         return this.field1.contains(value1);
      } catch (NullPointerException | ClassCastException nullpointerexception3) {
         return false;
      }
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof Predicates$InPredicate) {
         Predicates$InPredicate mixinhelper20$data112 = (Predicates$InPredicate)obj1;
         return this.field1.equals(mixinhelper20$data112.field1);
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
      return "Predicates.in(" + this.field1 + ")";
   }
}
