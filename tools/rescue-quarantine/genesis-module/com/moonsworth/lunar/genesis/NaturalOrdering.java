package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Ordering;
import com.google.common.base.Preconditions;

@GwtCompatible(serializable = true)
final class NaturalOrdering extends Ordering<Comparable> implements Serializable {
   static final NaturalOrdering field3 = new NaturalOrdering();
   private transient @Nullable Ordering<Comparable> field4;
   private transient @Nullable Ordering<Comparable> field5;
   private static final long field6 = 0L;

   public int compare(Comparable comparable1, Comparable comparable2) {
      Preconditions.checkNotNull(comparable1);
      Preconditions.checkNotNull(comparable2);
      return comparable1.compareTo(comparable2);
   }

   public <S extends Comparable> Ordering<S> method10() {
      Ordering comparator1 = this.field4;
      if (comparator1 == null) {
         comparator1 = this.field4 = super.method10();
      }

      return comparator1;
   }

   public <S extends Comparable> Ordering<S> method11() {
      Ordering comparator1 = this.field5;
      if (comparator1 == null) {
         comparator1 = this.field5 = super.method11();
      }

      return comparator1;
   }

   public <S extends Comparable> Ordering<S> method9() {
      return ReverseNaturalOrdering.field3;
   }

   private Object readResolve() {
      return field3;
   }

   @Override
   public String toString() {
      return "Ordering.natural()";
   }

   private NaturalOrdering() {
   }
}
