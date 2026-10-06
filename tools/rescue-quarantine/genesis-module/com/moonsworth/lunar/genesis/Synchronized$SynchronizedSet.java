package com.moonsworth.lunar.genesis;

import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

class Synchronized$SynchronizedSet<E> extends MixinHelper$Data44<E> implements Set<E> {
   private static final long field5 = 0L;

   Synchronized$SynchronizedSet(Set<E> set1, @Nullable Object obj2) {
      super(set1, obj2, null);
   }

   Set<E> delegate() {
      return (Set<E>)super.delegate();
   }

   @Override
   public boolean equals(Object obj1) {
      if (obj1 == this) {
         return true;
      }

      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().equals(obj1);
      }
   }

   @Override
   public int hashCode() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().hashCode();
      }
   }
}
