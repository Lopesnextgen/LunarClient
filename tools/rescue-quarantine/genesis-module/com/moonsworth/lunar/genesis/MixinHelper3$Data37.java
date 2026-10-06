package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.hash.Funnel;
import com.google.common.base.Preconditions;

class MixinHelper3$Data37<E> implements Funnel<Iterable<? extends E>>, Serializable {
   private final Funnel<E> field1;

   MixinHelper3$Data37(Funnel<E> var1) {
      this.field1 = Preconditions.checkNotNull(var1);
   }

   public void method1(Iterable<? extends E> var1, MixinHelper4_3 var2) {
      for (Object var4 : var1) {
         this.field1.funnel((E)var4, var2);
      }
   }

   @Override
   public String toString() {
      return "Funnels.sequentialFunnel(" + this.field1 + ")";
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof MixinHelper3$Data37) {
         MixinHelper3$Data37 var2 = (MixinHelper3$Data37)var1;
         return this.field1.equals(var2.field1);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return MixinHelper3$Data37.class.hashCode() ^ this.field1.hashCode();
   }
}
