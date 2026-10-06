package com.moonsworth.lunar.genesis;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.qual.Nullable;

@Annotation3
@Annotation4
class MixinHelper$Data45<E> extends MixinHelper$Data28<E> implements NavigableSet<E> {
   transient @Nullable NavigableSet<E> descendingSet;
   private static final long field7 = 0L;

   MixinHelper$Data45(NavigableSet<E> var1, @Nullable Object var2) {
      super(var1, var2);
   }

   NavigableSet<E> delegate() {
      return (NavigableSet<E>)super.delegate();
   }

   @Override
   public E ceiling(E var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().ceiling((E)var1);
      }
   }

   @Override
   public Iterator<E> descendingIterator() {
      return this.delegate().descendingIterator();
   }

   @Override
   public NavigableSet<E> descendingSet() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         if (this.descendingSet == null) {
            NavigableSet var2 = MixinHelper_8.navigableSet(this.delegate().descendingSet(), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
            this.descendingSet = var2;
            return var2;
         } else {
            return this.descendingSet;
         }
      }
   }

   @Override
   public E floor(E var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().floor((E)var1);
      }
   }

   @Override
   public NavigableSet<E> headSet(E var1, boolean var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.navigableSet(this.delegate().headSet((E)var1, var2), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public SortedSet<E> headSet(E var1) {
      return this.headSet((E)var1, false);
   }

   @Override
   public E higher(E var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().higher((E)var1);
      }
   }

   @Override
   public E lower(E var1) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().lower((E)var1);
      }
   }

   @Override
   public E pollFirst() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().pollFirst();
      }
   }

   @Override
   public E pollLast() {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return this.delegate().pollLast();
      }
   }

   @Override
   public NavigableSet<E> subSet(E var1, boolean var2, E var3, boolean var4) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.navigableSet(this.delegate().subSet((E)var1, var2, (E)var3, var4), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public SortedSet<E> subSet(E var1, E var2) {
      return this.subSet((E)var1, true, (E)var2, false);
   }

   @Override
   public NavigableSet<E> tailSet(E var1, boolean var2) {
      synchronized (this.RRCCHORICIIHRICRICOROHRHOCHRIC) {
         return MixinHelper_8.navigableSet(this.delegate().tailSet((E)var1, var2), this.RRCCHORICIIHRICRICOROHRHOCHRIC);
      }
   }

   @Override
   public SortedSet<E> tailSet(E var1) {
      return this.tailSet((E)var1, true);
   }
}
