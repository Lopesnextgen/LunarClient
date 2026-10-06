package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Iterator;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Equivalence;
import com.google.common.base.Preconditions;

@GwtCompatible(serializable = true)
final class PairwiseEquivalence<T> extends Equivalence<Iterable<T>> implements Serializable {
   final Equivalence<? super T> field1;
   private static final long field2 = 1L;

   PairwiseEquivalence(Equivalence<? super T> bipredicateloader1) {
      this.field1 = (Equivalence<? super T>)Preconditions.checkNotNull(bipredicateloader1);
   }

   protected boolean doEquivalent(Iterable<T> list1, Iterable<T> list2) {
      Iterator iterator3 = list1.iterator();
      Iterator iterator4 = list2.iterator();

      while (iterator3.hasNext() && iterator4.hasNext()) {
         if (!this.field1.method1((T)iterator3.next(), (T)iterator4.next())) {
            return false;
         }
      }

      return !iterator3.hasNext() && !iterator4.hasNext();
   }

   protected int doHash(Iterable<T> list1) {
      int number2 = 78721;

      for (Object obj4 : list1) {
         number2 = number2 * 24943 + this.field1.method2((T)obj4);
      }

      return number2;
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof PairwiseEquivalence) {
         PairwiseEquivalence bipredicateloader22 = (PairwiseEquivalence)obj1;
         return this.field1.equals(bipredicateloader22.field1);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return this.field1.hashCode() ^ 1185147655;
   }

   @Override
   public String toString() {
      return this.field1 + ".pairwise()";
   }
}
