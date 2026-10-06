package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Equivalence;

@Annotation2
@GwtCompatible
final class BiPredicateLoader3<F, T> extends Equivalence<F> implements Serializable {
   private static final long field1 = 0L;
   private final MixinHelper24_2<F, ? extends T> field2;
   private final Equivalence<T> field3;

   BiPredicateLoader3(MixinHelper24_2<F, ? extends T> var1, Equivalence<T> var2) {
      this.field2 = Preconditions.checkNotNull(var1);
      this.field3 = Preconditions.checkNotNull(var2);
   }

   @Override
   protected boolean doEquivalent(F var1, F var2) {
      return this.field3.method1((T)this.field2.apply((F)var1), (T)this.field2.apply((F)var2));
   }

   @Override
   protected int doHash(F var1) {
      return this.field3.method2((T)this.field2.apply((F)var1));
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 == this) {
         return true;
      }

      if (!(var1 instanceof BiPredicateLoader3)) {
         return false;
      }

      BiPredicateLoader3 var2 = (BiPredicateLoader3)var1;
      return this.field2.equals(var2.field2) && this.field3.equals(var2.field3);
   }

   @Override
   public int hashCode() {
      return MixinHelper72.hashCode(this.field2, this.field3);
   }

   @Override
   public String toString() {
      return this.field3 + ".onResultOf(" + this.field2 + ")";
   }
}
