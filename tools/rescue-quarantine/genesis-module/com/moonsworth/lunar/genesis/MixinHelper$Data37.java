package com.moonsworth.lunar.genesis;

import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

class MixinHelper$Data37<E> extends MixinHelper$Data44<E> implements Set<E> {
   private static final long field5 = 0L;

   MixinHelper$Data37(Set<E> var1, @Nullable Object var2) {
      super(var1, var2);
   }

   Set<E> delegate() {
      return (Set<E>)super.delegate();
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      }

      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().equals(var1);
      }
   }

   @Override
   public int hashCode() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().hashCode();
      }
   }
}
