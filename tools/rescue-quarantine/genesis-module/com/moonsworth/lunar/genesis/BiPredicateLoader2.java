package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import java.util.Iterator;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Equivalence;
import com.google.common.base.Preconditions;

@GwtCompatible(serializable = true)
final class BiPredicateLoader2<T> extends Equivalence<Iterable<T>> implements Serializable {
   final Equivalence<? super T> field1;
   private static final long field2 = 1L;

   BiPredicateLoader2(Equivalence<? super T> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   protected boolean doEquivalent(Iterable<T> var1, Iterable<T> var2) {
      Iterator var3 = var1.iterator();
      Iterator var4 = var2.iterator();

      while (var3.hasNext() && var4.hasNext()) {
         if (!this.field1.method1((T)var3.next(), (T)var4.next())) {
            return false;
         }
      }

      return !var3.hasNext() && !var4.hasNext();
   }

   protected int doHash(Iterable<T> var1) {
      int var2 = 78721;

      for (Object var4 : var1) {
         var2 = var2 * 24943 + this.field1.method2((T)var4);
      }

      return var2;
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof BiPredicateLoader2) {
         BiPredicateLoader2 var2 = (BiPredicateLoader2)var1;
         return this.field1.equals(var2.field1);
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
