package com.moonsworth.lunar.genesis;

import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.collect.Multiset;

class MixinHelper$Data41<E> extends MixinHelper$Data44<E> implements Multiset<E> {
   transient @Nullable Set<E> elementSet;
   transient @Nullable Set<Multiset.Extension<E>> entrySet;
   private static final long field5 = 0L;

   MixinHelper$Data41(Multiset<E> var1, @Nullable Object var2) {
      super(var1, var2);
   }

   Multiset<E> method1() {
      return (Multiset<E>)super.delegate();
   }

   @Override
   public int count(Object var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().count(var1);
      }
   }

   @Override
   public int add(E var1, int var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().add((E)var1, var2);
      }
   }

   @Override
   public int remove(Object var1, int var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().remove(var1, var2);
      }
   }

   @Override
   public int setCount(E var1, int var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().setCount((E)var1, var2);
      }
   }

   @Override
   public boolean setCount(E var1, int var2, int var3) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().setCount((E)var1, var2, var3);
      }
   }

   @Override
   public Set<E> elementSet() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         if (this.elementSet == null) {
            this.elementSet = MixinHelper_8.access$300(this.method1().elementSet(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
         }

         return this.elementSet;
      }
   }

   @Override
   public Set<Multiset.Extension<E>> entrySet() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         if (this.entrySet == null) {
            this.entrySet = MixinHelper_8.access$300(this.method1().entrySet(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
         }

         return this.entrySet;
      }
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      }

      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().equals(var1);
      }
   }

   @Override
   public int hashCode() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.method1().hashCode();
      }
   }
}
