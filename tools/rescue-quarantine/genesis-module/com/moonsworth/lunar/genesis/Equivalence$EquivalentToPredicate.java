package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Predicate;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

final class Equivalence$EquivalentToPredicate<T> implements Predicate<T>, Serializable {
   private final BiPredicateLoader<T> field1;
   private final @Nullable T field2;
   private static final long field3 = 0L;

   Equivalence$EquivalentToPredicate(BiPredicateLoader<T> bipredicateloader1, @Nullable T value2) {
      this.field1 = (BiPredicateLoader<T>)Preconditions.checkNotNull(bipredicateloader1);
      this.field2 = (T)value2;
   }

   public boolean apply(@Nullable T value1) {
      return this.field1.method1(value1, this.field2);
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (this == obj1) {
         return true;
      }

      if (!(obj1 instanceof Equivalence$EquivalentToPredicate)) {
         return false;
      }

      Equivalence$EquivalentToPredicate bipredicateloader$data2 = (Equivalence$EquivalentToPredicate)obj1;
      return this.field1.equals(bipredicateloader$data2.field1) && Objects.equal(this.field2, bipredicateloader$data2.field2);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(new Object[]{this.field1, this.field2});
   }

   @Override
   public String toString() {
      return this.field1 + ".equivalentTo(" + this.field2 + ")";
   }
}
