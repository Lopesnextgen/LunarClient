package com.moonsworth.lunar.genesis;

import java.util.SortedSet;
import org.checkerframework.checker.nullness.qual.Nullable;

class MixinHelper$Data28<E> extends MixinHelper$Data37<E> implements SortedSet<E> {
   private static final long field6 = 0L;

   MixinHelper$Data28(SortedSet<E> var1, @Nullable Object var2) {
      super(var1, var2);
   }

   SortedSet<E> delegate() {
      return (SortedSet<E>)super.delegate();
   }

   @Override
   public java.util.Comparator<? super E> comparator() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().comparator();
      }
   }

   @Override
   public SortedSet<E> subSet(E var1, E var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.access$100(this.delegate().subSet((E)var1, (E)var2), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public SortedSet<E> headSet(E var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.access$100(this.delegate().headSet((E)var1), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public SortedSet<E> tailSet(E var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.access$100(this.delegate().tailSet((E)var1), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public E first() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().first();
      }
   }

   @Override
   public E last() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().last();
      }
   }
}
