package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Equivalence;
import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.base.Function;

@Beta
@GwtCompatible
final class FunctionalEquivalence<F, T> extends Equivalence<F> implements Serializable {
   private static final long field1 = 0L;
   private final Function<F, ? extends T> field2;
   private final Equivalence<T> field3;

   FunctionalEquivalence(Function<F, ? extends T> mixinhelper24_21, Equivalence<T> bipredicateloader2) {
      this.field2 = (Function<F, ? extends T>)Preconditions.checkNotNull(mixinhelper24_21);
      this.field3 = (Equivalence<T>)Preconditions.checkNotNull(bipredicateloader2);
   }

   protected boolean doEquivalent(F f1, F f2) {
      return this.field3.method1(this.field2.apply(f1), this.field2.apply(f2));
   }

   protected int doHash(F f1) {
      return this.field3.method2(this.field2.apply(f1));
   }

   @Override
   public boolean equals(@Nullable Object obj1) {
      if (obj1 == this) {
         return true;
      }

      if (!(obj1 instanceof FunctionalEquivalence)) {
         return false;
      }

      FunctionalEquivalence bipredicateloader32 = (FunctionalEquivalence)obj1;
      return this.field2.equals(bipredicateloader32.field2) && this.field3.equals(bipredicateloader32.field3);
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(this.field2, this.field3);
   }

   @Override
   public String toString() {
      return this.field3 + ".onResultOf(" + this.field2 + ")";
   }
}
