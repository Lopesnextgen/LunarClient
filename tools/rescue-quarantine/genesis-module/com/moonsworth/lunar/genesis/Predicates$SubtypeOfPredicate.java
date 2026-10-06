package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Predicate;
import com.google.common.base.Preconditions;

@GwtIncompatible
class Predicates$SubtypeOfPredicate implements Predicate<Class<?>>, Serializable {
   private final Class<?> field1;
   private static final long field2 = 0L;

   private Predicates$SubtypeOfPredicate(Class<?> clazz1) {
      this.field1 = (Class<?>)Preconditions.checkNotNull(clazz1);
   }

   public boolean apply(Class<?> clazz1) {
      return this.field1.isAssignableFrom(clazz1);
   }

   @Override
   public int hashCode() {
      return this.field1.hashCode();
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof Predicates$SubtypeOfPredicate) {
         Predicates$SubtypeOfPredicate mixinhelper20$data72 = (Predicates$SubtypeOfPredicate)obj1;
         return this.field1 == mixinhelper20$data72.field1;
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return "Predicates.subtypeOf(" + this.field1.getName() + ")";
   }
}
