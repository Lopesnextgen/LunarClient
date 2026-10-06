package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Predicate;
import com.google.common.base.Preconditions;

@GwtIncompatible
class Predicates$InstanceOfPredicate implements Predicate<Object>, Serializable {
   private final Class<?> field1;
   private static final long field2 = 0L;

   private Predicates$InstanceOfPredicate(Class<?> clazz1) {
      this.field1 = (Class<?>)Preconditions.checkNotNull(clazz1);
   }

   public boolean apply(@Nullable Object obj1) {
      return this.field1.isInstance(obj1);
   }

   @Override
   public int hashCode() {
      return this.field1.hashCode();
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof Predicates$InstanceOfPredicate) {
         Predicates$InstanceOfPredicate mixinhelper20$data82 = (Predicates$InstanceOfPredicate)obj1;
         return this.field1 == mixinhelper20$data82.field1;
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return "Predicates.instanceOf(" + this.field1.getName() + ")";
   }
}
